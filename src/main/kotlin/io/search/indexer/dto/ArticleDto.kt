package io.search.indexer.dto

data class ArticleDto(
        val id: String? = null,
        val title: String,
        val content: String = "",
        val tags: List<String> = emptyList()
)
