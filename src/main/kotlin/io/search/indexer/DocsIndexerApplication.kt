package io.search.indexer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DocsIndexerApplication

fun main(args: Array<String>) {
    SpringApplication.run(DocsIndexerApplication::class.java, *args)
}

