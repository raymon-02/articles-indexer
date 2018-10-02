package io.search.indexer.index.repository

import io.search.indexer.index.entity.ArticleEntity
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository


interface ArticleRepository : ElasticsearchRepository<ArticleEntity, String>
