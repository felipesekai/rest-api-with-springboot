package br.com.sekai.services

import br.com.sekai.data.vo.v1.PersonVO
import br.com.sekai.exceptions.ResourceNotFoundException
import br.com.sekai.mapper.DozerMapper
import br.com.sekai.model.Person
import br.com.sekai.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonServices {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all People!!")
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO::class.java)
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding on People!!")
        var person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        return DozerMapper.parseObject(person, PersonVO::class.java)

    }

    fun insertPeople(person: PersonVO): PersonVO {
        logger.info("Creating one People with name ${person.firstName}!!")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)


    }

    fun update(person: PersonVO): PersonVO {
        logger.info("Updating one People with ID ${person.id}!!")
        val entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Deleting one People with ID ${id}!!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)


    }


}
