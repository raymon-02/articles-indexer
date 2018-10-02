package io.search.indexer.index.dto

data class ArticleDto(
        val title: String,
        val content: String,
        val tags: List<String>
)
