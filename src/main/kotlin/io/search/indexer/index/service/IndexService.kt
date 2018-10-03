package io.search.indexer.index.service

import io.search.indexer.entity.ArticleEntity
import io.search.indexer.model.Article
import io.search.indexer.repository.ArticleRepository
import org.springframework.stereotype.Component

@Component
class IndexService(
        private val articleRepository: ArticleRepository,
        private val fileConverterService: FileConverterService
) {
    fun indexArticles(articles: List<Article>): List<Pair<String?, String?>> {
        return saveArticles(articles)
    }

    fun indexArticlesFromPath(path: String): List<Pair<String?, String?>> {
        val articles = fileConverterService.convertFileToDocs(path)
        return saveArticles(articles)
    }

    private fun saveArticles(articles: List<Article>): List<Pair<String?, String?>> {
        val savedArticles = articleRepository.saveAll(articles.toArticleEntityList())
        return savedArticles.filterNotNull()
                .map {
                    Pair(it.id, it.title)
                }
    }

    private fun List<Article>.toArticleEntityList(): List<ArticleEntity> {
        return map {
            ArticleEntity(
                    id = it.id,
                    title = it.title,
                    content = it.content,
                    tags = it.tags
            )
        }
    }

}
