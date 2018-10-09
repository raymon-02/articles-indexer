package io.search.indexer.search.service

import io.search.indexer.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class SearchService(
        private val articleRepository: ArticleRepository
) {

}
