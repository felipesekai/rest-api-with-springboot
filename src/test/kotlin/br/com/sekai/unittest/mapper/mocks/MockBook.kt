package br.com.sekai.unittest.mapper.mocks


import br.com.sekai.data.vo.v1.BooksVO
import br.com.sekai.model.Books
import java.util.*
import kotlin.collections.ArrayList


class MockBook {
    fun mockEntity(): Books {
        return mockEntity(0)
    }

    fun mockVO(): BooksVO {
        return mockVO(0)
    }

    fun mockEntityList(): ArrayList<Books> {
        val books: ArrayList<Books> = ArrayList<Books>()
        for (i in 0..13) {
            books.add(mockEntity(i))
        }
        return books
    }

    fun mockVOList(): ArrayList<BooksVO> {
        val books: ArrayList<BooksVO> = ArrayList()
        for (i in 0..13) {
            books.add(mockVO(i))
        }
        return books
    }

    fun mockEntity(number: Int): Books {
        val book = Books()
        book.title = "title test$number"
        book.author = "author test$number"
        book.launchDate = Date("10/10/10")
        book.id = number.toLong()
        book.price = number.toDouble()
        return book
    }

    private fun mockVO(number: Int): BooksVO {
        val book = BooksVO()
        book.title = "title test$number"
        book.author = "author test$number"
        book.launchDate =Date("10/10/10")
        book.key = number.toLong()
        book.price = number.toDouble()
        return book
    }
}
