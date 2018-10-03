package io.search.indexer.dto

data class ArticleDto(
        val title: String,
        val content: String,
        val tags: List<String>
)
