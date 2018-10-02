package io.search.indexer.index

import io.search.indexer.index.dto.ArticleDto
import io.search.indexer.index.model.Article
import io.search.indexer.index.service.IndexService
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/index")
class IndexController(
        val indexService: IndexService
) {

    @PostMapping("/articles")
    fun indexDocsList(@RequestBody articles: List<ArticleDto>): ResponseEntity<List<Pair<String?, String?>>> {
        val indexedArticles = indexService.indexArticles(articles.toArticleList())
        return ResponseEntity(indexedArticles, OK)
    }

    @PostMapping("/articles/local")
    fun indexDocsFromPath(@RequestParam("path", required = true) path: String): ResponseEntity<List<Pair<String?, String?>>> {
        val indexedArticles = indexService.indexArticlesFromPath(path)
        return ResponseEntity(indexedArticles, OK)
    }

    private fun List<ArticleDto>.toArticleList(): List<Article> {
        return map {
            Article(
                    title = it.title,
                    content = it.content,
                    tags = it.tags
            )
        }
    }
}
