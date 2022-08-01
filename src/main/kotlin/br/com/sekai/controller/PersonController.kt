package br.com.sekai.controller

import br.com.sekai.Math.SimpleMath
import br.com.sekai.converter.NumberConverter.convertToDouble
import br.com.sekai.converter.NumberConverter.isNumeric
import br.com.sekai.exceptions.UnsupportedMAthOperationException
import br.com.sekai.model.Person
import br.com.sekai.services.PersonServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@RestController
@RequestMapping("/person")
class PersonController {

        @Autowired
        private lateinit var service: PersonServices

        @RequestMapping("/{id}", method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
        fun findByid(@PathVariable(value = "id")
        id: Long
        ): Person{
            return service.findById(id)
        }
        @RequestMapping("/all", method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
        fun findAll(): List<Person> {
            return service.findAll()
        }

}
