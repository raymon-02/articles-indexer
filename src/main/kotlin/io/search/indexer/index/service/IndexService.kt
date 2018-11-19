package io.search.indexer.index.service

import io.search.indexer.entity.ArticleEntity
import io.search.indexer.model.Article
import io.search.indexer.repository.ArticleRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class IndexService(
    private val articleRepository: ArticleRepository,
    private val fileConverterService: FileConverterService
) {
    val log = LoggerFactory.getLogger(IndexService::class.java)

    fun indexArticles(articles: List<Article>): List<Article> {
        log.info("Indexing articles with ids: [${articles.toIds()}]")
        return saveArticles(articles)
    }

    fun indexArticlesFromPath(path: String): List<Article> {
        log.info("Indexing articles form local path: $path")
        val articles = fileConverterService.convertFileToDocs(path)
        return saveArticles(articles)
    }

    private fun saveArticles(articles: List<Article>): List<Article> {
        log.info("Saving articles with ids: ${articles.toIds()}")
        val savedArticles = articleRepository.saveAll(articles.toArticleEntityList())
        log.info("Saved articles with ids: [${savedArticles.entitiesToIds()}]")
        return savedArticles.filterNotNull()
            .map {
                Article(
                    it.id,
                    it.title,
                    it.place,
                    it.year,
                    it.material,
                    it.creator,
                    it.address,
                    it.description,
                    it.massMediaName,
                    it.massMediaTexts
                )
            }
    }

    private fun List<Article>.toArticleEntityList(): List<ArticleEntity> {
        return map {
            ArticleEntity(
                it.id,
                it.title,
                it.place,
                it.year,
                it.material,
                it.creator,
                it.address,
                it.description,
                it.massMediaName,
                it.massMediaTexts
            )
        }
    }

    private fun Iterable<Article>.toIds(): String {
        return map { it.id }.joinToString()
    }

    private fun Iterable<ArticleEntity>.entitiesToIds(): String {
        return map { it.id }.joinToString()
    }

}
