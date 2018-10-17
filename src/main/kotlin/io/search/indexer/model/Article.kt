package io.search.indexer.model

data class Article(
        val id: String? = null,
        val title: String? = null,
        val place: String? = null,
        val year: Int? = null,
        val material: String? = null,
        val creator: String? = null,
        val address: String? = null,
        val description: String? = null,
        val massMediaName: String? = null,
        val massMediaTexts: List<String> = emptyList()
)
