package br.com.sekai.controller

import br.com.sekai.data.vo.v1.PersonVO
import br.com.sekai.data.vo.v2.PersonVO as PersonVOV2
import br.com.sekai.services.PersonServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonServices


    @GetMapping("/all", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByid(
        @PathVariable(value = "id")
        id: Long
    ): PersonVO {
        return service.findById(id)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun insert(@RequestBody person: PersonVO): PersonVO {
        return service.insert(person)
    }
    @PostMapping( value= ["/v2"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createV2(@RequestBody person: PersonVOV2): PersonVOV2 {
        return service.insertV2(person)
    }

    @PutMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(
        @PathVariable(value = "id")
        id: Long
    ): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

}
