package io.search.indexer.index.service

import io.search.indexer.model.Article
import io.search.indexer.model.ArticleField
import io.search.indexer.model.ArticleField.*
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.io.File

@Lazy
@Component
class FileConverterService {

    //TODO: fields can be written not only in one line
    fun convertFileToDocs(path: String): List<Article> {
        return File(path).walkTopDown().asSequence()
                .filter {
                    it.isFile && it.extension == "index"
                }
                .map {
                    it.readLines()
                }
                .map {
                    it.toArticle()
                }
                .toList()
    }

    private fun List<String>.toArticle(): Article {
        return map { it.trim().split(":", limit = 2) }
                .asSequence()
                .filter {
                    it.size == 2
                }
                .map {
                    (it[0].trim() to it[1].trim()).toArticleFieldPair()
                }
                .filterNotNull()
                .toMap()
                .toArticle()
    }

    private fun Map<ArticleField, String>.toArticle(): Article {
        return Article(
                get(ID),
                get(TITLE),
                get(PLACE),
                get(YEAR)?.toInt(),
                get(MATERIAL),
                get(CREATOR),
                get(ADDRESS),
                get(DESCRIPTION),
                get(MM_NAME),
                get(MM_TEXTS).toList()
        )
    }

    private fun Pair<String, String>.toArticleFieldPair(): Pair<ArticleField, String>? {
        try {
            return ArticleField.valueOf(first) to second
        } catch (ex: IllegalArgumentException) {
            return null
        }
    }

    private fun String?.toList(): List<String> {
        return this?.trim()
                ?.split(",")
                ?.map { it.trim() }
                ?: emptyList()
    }
}


