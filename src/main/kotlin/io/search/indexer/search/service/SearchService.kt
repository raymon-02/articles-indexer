package io.search.indexer.search.service

import io.search.indexer.model.Article
import io.search.indexer.repository.ArticleRepository
import org.springframework.stereotype.Service


//TODO: support tags query
//TODO: support suggestions

@Service
class SearchService(
        private val articleRepository: ArticleRepository
) {

    fun searchByString(searchString: String): List<Article> {
        TODO()
    }
}
