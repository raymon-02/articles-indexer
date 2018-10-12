package io.search.indexer.model

data class Article(
        val id: String? = null,
        val title: String? = null,
        val content: String? = null,
        val tags: List<String> = emptyList()
)
