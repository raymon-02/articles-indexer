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
    fun indexArticles(articles: List<Article>): List<Article> {
        return saveArticles(articles)
    }

    fun indexArticlesFromPath(path: String): List<Article> {
        val articles = fileConverterService.convertFileToDocs(path)
        return saveArticles(articles)
    }

    private fun saveArticles(articles: List<Article>): List<Article> {
        val savedArticles = articleRepository.saveAll(articles.toArticleEntityList())
        return savedArticles.filterNotNull()
                .map {
                    Article(it.id, it.title, it.content, it.tags)
                }
    }

    private fun List<Article>.toArticleEntityList(): List<ArticleEntity> {
        return map {
            ArticleEntity(it.id, it.title, it.content, it.tags)
        }
    }

}
