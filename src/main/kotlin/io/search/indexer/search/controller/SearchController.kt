package io.search.indexer.search.controller

import io.search.indexer.dto.ArticleDto
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
            @RequestParam("q", required = true) query: String
    ): List<ArticleDto> {

//        TODO()
        return emptyList()
    }

}
