package io.search.indexer.index.controller

import io.search.indexer.dto.ArticleDto
import io.search.indexer.dto.ArticleIdTitleDto
import io.search.indexer.index.service.IndexService
import io.search.indexer.model.Article
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/index")
class IndexController(
    val indexService: IndexService
) {

    @PostMapping("/articles")
    fun indexDocsList(
        @RequestBody articles: List<ArticleDto>
    ): ResponseEntity<List<ArticleIdTitleDto>> {
        val indexedArticles = indexService.indexArticles(articles.toArticleList())
            .toArticleIdTitleDtoList()
        return ResponseEntity(indexedArticles, OK)
    }

    @PostMapping("/articles/local")
    fun indexDocsFromPath(
        @RequestParam("path", required = true) path: String
    ): ResponseEntity<List<ArticleIdTitleDto>> {
        val indexedArticles = indexService.indexArticlesFromPath(path)
            .toArticleIdTitleDtoList()
        return ResponseEntity(indexedArticles, OK)
    }

    private fun List<ArticleDto>.toArticleList(): List<Article> {
        return map {
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

    private fun List<Article>.toArticleIdTitleDtoList(): List<ArticleIdTitleDto> {
        return map {
            ArticleIdTitleDto(it.id, it.title)
        }
    }
}
