package io.search.indexer.dto

data class ArticleDto(
    val id: String? = null,
    val title: String,
    val place: String? = null,
    val year: Int? = null,
    val material: String? = null,
    val creator: String? = null,
    val address: String? = null,
    val description: String = "",
    val massMediaName: String? = null,
    val massMediaTexts: List<String> = emptyList()
)
