package br.com.sekai.controller

import br.com.sekai.data.vo.v1.PersonVO
import br.com.sekai.services.PersonServices
import br.com.sekai.util.MediaType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
class PersonController {

    @Autowired
    private lateinit var service: PersonServices


    @GetMapping("/all", produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    @Operation(
        summary = "Find all People", description = "Find all People", tags = ["People"], responses = [
//            apiResponse(200, true),
//            apiResponse(204, false),
//            apiResponse(400, false),
//            apiResponse(401, false),
//            apiResponse(404, false),
//            apiResponse(500, false),

            ApiResponse(
                description = "success",
                responseCode = "200",
                content = [Content(array = ArraySchema(schema = Schema(implementation = PersonVO::class)))]
            ),
            ApiResponse(
                description = "No content",
                responseCode = "204",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not found",
                responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal error",
                responseCode = "500",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
        ]
    )
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }


    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    @Operation(
        summary = "Find People by Id",
        description = "Find a People",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "success",
                responseCode = "200",
                content = [Content(schema = Schema(implementation = PersonVO::class))]
            ),
            ApiResponse(
                description = "No content",
                responseCode = "204",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not found",
                responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal error",
                responseCode = "500",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
        ]
    )
    fun findByid(
        @PathVariable(value = "id") id: Long
    ): PersonVO {
        return service.findById(id)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML]
    )
    @Operation(
        summary = "Insert New People",
        description = "Add a Person on database",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "success",
                responseCode = "200",
                content = [Content(schema = Schema(implementation = PersonVO::class))]
            ),
            ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal error",
                responseCode = "500",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
        ]
    )
    fun insertPeople(@RequestBody person: PersonVO): PersonVO {
        return service.insertPeople(person)
    }

    @PutMapping(
        consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML]
    )
    @Operation(
        summary = "Update",
        description = "Update a People",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "success",
                responseCode = "200",
                content = [Content(schema = Schema(implementation = PersonVO::class))]
            ),
            ApiResponse(
                description = "No content",
                responseCode = "204",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not found",
                responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal error",
                responseCode = "500",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
        ]
    )
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @DeleteMapping(
        "/{id}", produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML]
    )
    @Operation(
        summary = "Delete a Person",
        description = "Delete a Person",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "No content",
                responseCode = "204",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not found",
                responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal error",
                responseCode = "500",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
        ]
    )
    fun delete(
        @PathVariable(value = "id") id: Long
    ): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

}
