package io.search.indexer.index

import io.search.indexer.index.entity.SingleDocEntity
import io.search.indexer.index.service.FileConverterService
import io.search.indexer.index.service.IndexService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/index")
class IndexController(
        @Autowired val indexService: IndexService,
        @Autowired val fileConverterService: FileConverterService
) {

    @PostMapping("/docs")
    fun indexDocsList(@RequestBody docs: List<SingleDocEntity>): ResponseEntity<String> {


        return ResponseEntity("", HttpStatus.CREATED)
    }

    @PostMapping("/docs/local")
    fun indexDocsFromPath(@RequestParam("path", required = true) path: String): ResponseEntity<String> {

        val docs = fileConverterService.convertFileToDocs(path)

        return ResponseEntity("", HttpStatus.CREATED)
    }

}
