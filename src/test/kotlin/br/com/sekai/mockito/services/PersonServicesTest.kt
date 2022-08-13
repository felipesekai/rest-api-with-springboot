package br.com.sekai.mockito.services

import br.com.sekai.data.vo.v1.PersonVO
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

    private fun asserts(result: PersonVO) {
        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("</api/person/v1/1>;rel=\"self\""))
        assertEquals("Address Test${1}", result.address)
        assertEquals("First Name Test${1}", result.firstName)
        assertEquals("Last Name Test${1}", result.lastName)
        assertEquals("Female", result.gender)

    }
}
