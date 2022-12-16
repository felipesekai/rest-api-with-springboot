package br.com.sekai.mockito.services

import br.com.sekai.repository.BooksRepository
import br.com.sekai.services.BooksServices
import br.com.sekai.unittest.mapper.mocks.MockBook
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class BooksServicesTest {

    private lateinit var books : MockBook

    @InjectMocks
    lateinit var services: BooksServices

    @Mock
    lateinit var  repository: BooksRepository

    @BeforeEach
    fun setUp()
    {
        books = MockBook()
        MockitoAnnotations.openMocks(this)
    }


    @Test
    fun findAll() {
        val _books = books.mockEntityList()
        `when`(repository.findAll()).thenReturn(_books)

        val result = services.findAll()

        assertNotNull(result)
        assertEquals(14,result.size)

        val bookResult = result[7]
        assertEquals( "title test${7}", bookResult.title)
        assertEquals( "author test${7}", bookResult.author)
        assertEquals( 7, bookResult.key)
        assertEquals( 7.toDouble(), bookResult.price)
        assertEquals( Date("10/10/10"), bookResult.launchDate)
        assertNotNull(bookResult.links)
        assertTrue(bookResult.links.toString().contains("</api/books/v1/${7}>;rel=\"self\""))


    }

    @Test
    fun findById() {
        val _books = books.mockEntity(1)
        `when`(repository.findById(1)).thenReturn(
            Optional.of(_books)
        )
        val bookResult = services.findById(1)
        assertEquals( "title test${1}", bookResult.title)
        assertEquals( "author test${1}", bookResult.author)
        assertEquals( 1, bookResult.key)
        assertEquals( 1.toDouble(), bookResult.price)
        assertEquals( Date("10/10/10"), bookResult.launchDate)
        assertNotNull(bookResult.links)
        assertTrue(bookResult.links.toString().contains("</api/books/v1/${1}>;rel=\"self\""))
    }

    @Test
    fun create() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }
}
