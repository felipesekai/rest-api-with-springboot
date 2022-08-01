package br.com.sekai.exceptions.services

import br.com.sekai.exceptions.ResourceNotFoundException
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

    fun findAll(): List<Person> {
        logger.info("Finding all People!!")

        return  repository.findAll()
    }
    fun findById(id:Long): Person {
        logger.info("Finding on People!!")
        return  repository.findById(id)
            .orElseThrow{ ResourceNotFoundException("No records found for this ID!") }
    }

    fun insertPeople(person: Person): Person {
        logger.info("Creating one People with name ${person.firstName}!!")
        return repository.save(person)
    }

    fun update(person: Person): Person {
        logger.info("Updating one People with ID ${person.id}!!")
        val entity = repository.findById(person.id)
          .orElseThrow{ ResourceNotFoundException("No records found for this ID!") }
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return repository.save(entity)
    }

    fun delete(id: Long) {
        logger.info("Deleting one People with ID ${id}!!")
        val entity = repository.findById(id)
            .orElseThrow{ ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)


    }


}
