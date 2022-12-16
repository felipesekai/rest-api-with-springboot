package br.com.sekai.services

import br.com.sekai.controller.PersonController
import br.com.sekai.data.vo.v1.PersonVO
import br.com.sekai.exceptions.RequiredObjectIsNullException
import br.com.sekai.exceptions.ResourceNotFoundException
import br.com.sekai.mapper.DozerMapper
import br.com.sekai.model.Person
import br.com.sekai.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonServices {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all People!!")
        val vos = DozerMapper.parseListObjects(repository.findAll(), PersonVO::class.java)
        for (person in vos){
            val withSelfRel = linkTo(PersonController::class.java).slash(person.key).withSelfRel()
            person.add(withSelfRel)
        }
        return vos
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding on People!!")
        var person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        person.gender

        val  personVO : PersonVO = DozerMapper.parseObject(person, PersonVO::class.java);
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO

    }

    fun insertPeople(person: PersonVO?): PersonVO {
        if (person == null) throw  RequiredObjectIsNullException()
        logger.info("Creating one People with name ${person.firstName}!!")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        val personVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO


    }

    fun update(person: PersonVO?): PersonVO {
        if (person == null) throw  RequiredObjectIsNullException()

        logger.info("Updating one People with ID ${person.key}!!")
        val entity = repository.findById(person.key)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        val personVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun delete(id: Long) {
        logger.info("Deleting one People with ID ${id}!!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)


    }


}
