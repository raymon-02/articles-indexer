package io.search.indexer.index.service

import io.search.indexer.index.model.SingleDoc
import io.search.indexer.index.model.SingleDocField
import io.search.indexer.index.model.SingleDocField.BODY
import io.search.indexer.index.model.SingleDocField.ID
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.io.File

@Lazy
@Component
class FileConverterService {

    //TODO: fields can be written not only in one line
    fun convertFileToDocs(path: String): List<SingleDoc> {
        return File(path).walkTopDown().asSequence()
                .filter {
                    it.isFile && it.extension == "index"
                }
                .map {
                    it.readLines()
                }
                .map {
                    it.toSingleDoc()
                }
                .toList()
    }

    private fun List<String>.toSingleDoc(): SingleDoc {
        return map { it.trim().split(":", limit = 2) }
                .asSequence()
                .filter {
                    it.size == 2
                }
                .map {
                    (it[0].trim() to it[1].trim()).toSingleDocFieldPair()
                }
                .filterNotNull()
                .toMap()
                .toSingleDoc()
    }

    private fun Map<SingleDocField, String>.toSingleDoc(): SingleDoc {
        return SingleDoc(
                get(ID),
                get(BODY)
        )
    }

    private fun Pair<String, String>.toSingleDocFieldPair(): Pair<SingleDocField, String>? {
        try {
            return SingleDocField.valueOf(first) to second
        } catch (ex: IllegalArgumentException) {
            return null
        }
    }
}


