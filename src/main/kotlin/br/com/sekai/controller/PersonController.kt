package br.com.sekai.controller

import br.com.sekai.data.vo.v1.PersonVO
import br.com.sekai.services.PersonServices
import br.com.sekai.util.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/person/v1")
class PersonController {

    @Autowired
    private lateinit var service: PersonServices


    @GetMapping("/all", produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun findByid(
        @PathVariable(value = "id")
        id: Long
    ): PersonVO {
        return service.findById(id)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML]
    )
    fun insertPeople(@RequestBody person: PersonVO): PersonVO {
        return service.insertPeople(person)
    }

    @PutMapping(
        consumes = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML],
        produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML]
    )
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun delete(
        @PathVariable(value = "id")
        id: Long
    ): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

}
