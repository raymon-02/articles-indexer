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
        val content: String? = null,

        @Field(
                type = Text,
                analyzer = "russian",
                searchAnalyzer = "russian"
        )
        val tags: List<String> = emptyList()
)
