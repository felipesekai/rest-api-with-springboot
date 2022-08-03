package br.com.sekai.services

import br.com.sekai.data.vo.v1.PersonVO
import br.com.sekai.data.vo.v2.PersonVO as PersonVOV2
import br.com.sekai.exceptions.ResourceNotFoundException
import br.com.sekai.mapper.DozerMapper
import br.com.sekai.mapper.custom.PersonMapper
import br.com.sekai.model.Person
import br.com.sekai.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonServices {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var mapper: PersonMapper

    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all People!!")
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO::class.java)
    }
    fun findAllV2(): List<PersonVOV2> {
        logger.info("Finding all People!! v2")
        val list = mapper.mapListEntityToVO(repository.findAll())
        return DozerMapper.parseListObjects(list, PersonVOV2::class.java)

    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding on People!!")
        var person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        return DozerMapper.parseObject(person, PersonVO::class.java)

    }

    fun insert(person: PersonVO): PersonVO {
        logger.info("Creating one People with name ${person.firstName}!!")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)


    }
    fun insertV2(person: PersonVOV2): PersonVOV2 {
        logger.info("Creating one People v2 with name ${person.firstName}!!")
        val entity: Person = mapper.mapVoToEntity(person)
        return mapper.mapEntityToVo(repository.save(entity))


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
