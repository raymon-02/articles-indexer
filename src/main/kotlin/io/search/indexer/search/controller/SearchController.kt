package io.search.indexer.search.controller

import io.search.indexer.dto.ArticleIdTitleDto
import io.search.indexer.model.Article
import io.search.indexer.search.service.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/search")
class SearchController(
        private val searchService: SearchService
) {

    @GetMapping
    fun search(
            @RequestParam("q", required = true) searchString: String
    ): List<ArticleIdTitleDto> {
        return searchService.searchByString(searchString)
                .toArticleIdTitleDtoList()
    }

    private fun List<Article>.toArticleIdTitleDtoList(): List<ArticleIdTitleDto> {
        return map {
            ArticleIdTitleDto(it.id, it.title)
        }
    }

}
