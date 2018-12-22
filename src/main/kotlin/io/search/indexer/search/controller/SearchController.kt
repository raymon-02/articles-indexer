package io.search.indexer.search.controller

import io.search.indexer.dto.ArticleIdTitleDto
import io.search.indexer.model.Article
import io.search.indexer.search.service.SearchService
import org.springframework.web.bind.annotation.*


//TODO: support async calls

const val TILDA = "https://tilda.cc"

@RestController
@RequestMapping("/api/search")
class SearchController(
    private val searchService: SearchService
) {

    @GetMapping
    @CrossOrigin(TILDA)
    fun search(@RequestParam("q", required = true) text: String): List<ArticleIdTitleDto> {
        return searchService.searchByText(text)
            .toArticleIdTitleDtoList()
    }

    private fun List<Article>.toArticleIdTitleDtoList(): List<ArticleIdTitleDto> {
        return map {
            ArticleIdTitleDto(it.id, it.title)
        }
    }

}
