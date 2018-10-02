package io.search.indexer.index.service

import io.search.indexer.index.model.Article
import io.search.indexer.index.model.ArticleField
import io.search.indexer.index.model.ArticleField.*
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
                    it.toSingleDoc()
                }
                .toList()
    }

    private fun List<String>.toSingleDoc(): Article {
        return map { it.trim().split(":", limit = 2) }
                .asSequence()
                .filter {
                    it.size == 2
                }
                .map {
                    (it[0].trim() to it[1].trim()).toSingleDocFieldPair()
                }
                .filterNotNull()
                .toMap()
                .toSingleDoc()
    }

    private fun Map<ArticleField, String>.toSingleDoc(): Article {
        return Article(
                get(ID),
                get(TITLE),
                get(CONTENT),
                get(TAGS).toList()
        )
    }

    private fun Pair<String, String>.toSingleDocFieldPair(): Pair<ArticleField, String>? {
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


