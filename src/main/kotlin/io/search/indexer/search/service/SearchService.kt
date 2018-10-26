package io.search.indexer.search.service

import io.search.indexer.entity.ArticleEntity
import io.search.indexer.model.Article
import io.search.indexer.model.ArticleField.*
import io.search.indexer.repository.ArticleRepository
import org.elasticsearch.index.query.MultiMatchQueryBuilder
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type.BEST_FIELDS
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.stereotype.Service


//TODO: support tags query
//TODO: support suggestions

@Service
class SearchService(
        private val articleRepository: ArticleRepository
) {

    fun searchByText(text: String): List<Article> {

        val queryBuilder = multiMatchQueryAllFields(text)
                .minimumShouldMatch("50%")
                .type(BEST_FIELDS)


        return articleRepository.search(queryBuilder)
                .map { it.toArticle() }
                .toList()
    }

    private fun ArticleEntity.toArticle(): Article {
        return Article(
                id,
                title,
                place,
                year,
                material,
                creator,
                address,
                description,
                massMediaName,
                massMediaTexts
        )
    }

    private fun multiMatchQueryAllFields(text: String): MultiMatchQueryBuilder {
        return QueryBuilders.multiMatchQuery(
                text,
                TITLE.fieldName,
                PLACE.fieldName,
                YEAR.fieldName,
                MATERIAL.fieldName,
                CREATOR.fieldName,
                ADDRESS.fieldName,
                DESCRIPTION.fieldName,
                MM_NAME.fieldName,
                MM_TEXTS.fieldName
        )
    }

}
