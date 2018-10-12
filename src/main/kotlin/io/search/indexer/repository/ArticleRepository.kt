package io.search.indexer.repository

import io.search.indexer.entity.ArticleEntity
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository


interface ArticleRepository : ElasticsearchRepository<ArticleEntity, String>
