package io.search.indexer.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType.Text

@Document(
    indexName = "desks",
    type = "article"
)
data class ArticleEntity(
    @Id
    val id: String? = null,

    @Field(
        type = Text,
        store = true,
        analyzer = "russian",
        searchAnalyzer = "russian"
    )
    val title: String? = null,

    @Field(
        type = Text,
        analyzer = "russian",
        searchAnalyzer = "russian"
    )
    val place: String? = null,

    @Field(
        type = Text
    )
    val year: Int? = null,

    @Field(
        type = Text,
        analyzer = "russian",
        searchAnalyzer = "russian"
    )
    val material: String? = null,

    @Field(
        type = Text,
        analyzer = "russian",
        searchAnalyzer = "russian"
    )
    val creator: String? = null,

    @Field(
        type = Text,
        analyzer = "russian",
        searchAnalyzer = "russian"
    )
    val address: String? = null,

    @Field(
        type = Text,
        analyzer = "russian",
        searchAnalyzer = "russian"
    )
    val description: String? = null,

    @Field(
        type = Text,
        analyzer = "russian",
        searchAnalyzer = "russian"
    )
    val massMediaName: String? = null,

    @Field(
        type = Text,
        analyzer = "russian",
        searchAnalyzer = "russian"
    )
    val massMediaTexts: List<String> = emptyList()
)
