package br.com.sekai.controller

import br.com.sekai.data.vo.v1.BooksVO
import br.com.sekai.services.BooksServices
import br.com.sekai.util.MediaType
import jakarta.websocket.server.PathParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books/v1")
class BooksController {

    @Autowired
    private lateinit var service: BooksServices

    @GetMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun findAll(): List<BooksVO> {
        return service.findAll()
    }

    @GetMapping("/{id}" , produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML, MediaType.APPLICATION_XML] )
    fun findById(@PathVariable(name = "id") id : Long): BooksVO {
        return service.findById(id)
    }

    @PostMapping(produces =[MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML, MediaType.APPLICATION_XML],
        consumes =[MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML, MediaType.APPLICATION_XML])
    fun create(@RequestBody booksVO: BooksVO): BooksVO {
        return service.create(booksVO)
    }
    @PutMapping(produces =[MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML, MediaType.APPLICATION_XML],
        consumes =[MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML, MediaType.APPLICATION_XML])
    fun update(@RequestBody booksVO: BooksVO): BooksVO {
        return service.update(booksVO)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(name = "id") id:Long): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

}