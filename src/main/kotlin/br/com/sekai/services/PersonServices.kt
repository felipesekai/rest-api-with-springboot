package br.com.sekai.services

import br.com.sekai.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonServices {

    private val counter : AtomicLong = AtomicLong()

    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findById(id:Long): Person {
        logger.info("Finding on People!!")

        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Sekai"
        person.lastName = "Felipe"
        person.address = "rua tal tal tal na canto tal"
        person.gender = "male"

        return  person

    }
    fun findAll(): List<Person> {
        logger.info("Finding all People!!")

        val peoples : MutableList<Person> = ArrayList()

        for (i in 0 .. 7){
            val person = mockPerson(i)
            peoples.add(person)
        }



        return  peoples

    }

    private fun mockPerson(i: Int): Person {
        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "fisrt name $i"
        person.lastName = "last name $i"
        person.address = "brasilian address $i"
        person.gender = "male"

        return person
    }
}
