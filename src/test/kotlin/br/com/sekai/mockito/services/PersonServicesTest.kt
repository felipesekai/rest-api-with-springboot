package br.com.sekai.mockito.services

import br.com.sekai.data.vo.v1.PersonVO
import br.com.sekai.exceptions.RequiredObjectIsNullException
import br.com.sekai.repository.PersonRepository
import br.com.sekai.services.PersonServices
import br.com.sekai.unittest.mapper.mocks.MockPerson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class PersonServicesTest {

    private lateinit var inputObject: MockPerson

    @InjectMocks
    lateinit var services: PersonServices

    @Mock
    lateinit var repository: PersonRepository

    @BeforeEach
    fun setUp() {
        inputObject = MockPerson()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findAll() {
        val people = inputObject.mockEntityList()
        `when`(repository.findAll()).thenReturn(people)

        val result = services.findAll()
        assertsLists(result, 1)
        assertsLists(result, 4)
        assertsLists(result, 7)

    }

    @Test
    fun findById() {
        val person = inputObject.mockEntity(1)
        `when`(repository.findById(1)).thenReturn(Optional.of(person))

        val result = services.findById(1)
        asserts(result)

    }

    @Test
    fun insertPeople() {

        val entity = inputObject.mockEntity(1)
        val persisted = entity.copy()
        `when`(repository.save(entity)).thenReturn(persisted)

        val vo = inputObject.mockVO(1)
        val result = services.insertPeople(vo)
        asserts(result)

    }

    @Test
    fun update() {
        val entity = inputObject.mockEntity(1)
        val persisted = entity.copy()
        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persisted)
        val vo = inputObject.mockVO(1)

        val result = services.update(vo)

        asserts(result)


    }

    @Test
    fun delete() {
        val person = inputObject.mockEntity(1)
        `when`(repository.findById(1)).thenReturn(Optional.of(person))

        services.delete(1)

        val result = services.findById(1)
        println(result)

            }



    @Test
    fun insertWithNullPeople() {
        val exception : Exception = assertThrows(
            RequiredObjectIsNullException::class.java
        ) { services.insertPeople(null) }

        val expectedMessage = "It is not allowed to persist a null object"
        val actualMessage = exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    @Test
    fun updateWithNull() {
        val exception : Exception = assertThrows(
            RequiredObjectIsNullException::class.java
        ) { services.update(null) }

        val expectedMessage = "It is not allowed to persist a null object"
        val actualMessage = exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))


    }

    private fun asserts(result: PersonVO) {
        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("</api/person/v1/1>;rel=\"self\""))
        assertEquals("Address Test${1}", result.address)
        assertEquals("First Name Test${1}", result.firstName)
        assertEquals("Last Name Test${1}", result.lastName)
        assertEquals("Female", result.gender)

    }   private fun assertsLists(people: List<PersonVO>, number: Int) {
        assertNotNull(people)
        assertEquals(14, people.size)

        val result = people[number]
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("</api/person/v1/${number}>;rel=\"self\""))
        assertEquals("Address Test${number}", result.address)
        assertEquals("First Name Test${number}", result.firstName)
        assertEquals("Last Name Test${number}", result.lastName)


    }
}
