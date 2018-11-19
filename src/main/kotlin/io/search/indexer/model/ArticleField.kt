package io.search.indexer.model

enum class ArticleField(
    val fieldName: String
) {
    ID("id"),
    TITLE("title"),
    PLACE("place"),
    YEAR("year"),
    MATERIAL("material"),
    CREATOR("creator"),
    ADDRESS("address"),
    DESCRIPTION("description"),
    MM_NAME("massMediaName"),
    MM_TEXTS("massMediaTexts")
}
