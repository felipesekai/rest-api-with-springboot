package br.com.sekai.services

import br.com.sekai.controller.BooksController
import br.com.sekai.data.vo.v1.BooksVO
import br.com.sekai.exceptions.ResourceNotFoundException
import br.com.sekai.mapper.DozerMapper
import br.com.sekai.mapper.toEntity
import br.com.sekai.model.Books
import br.com.sekai.repository.BooksRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BooksServices {

    @Autowired
    private lateinit var repository: BooksRepository

    private val logger = Logger.getLogger(BooksServices::class.java.name)

    fun findAll() : List<BooksVO>{
        logger.info("Find All Books!!")
       val listBooks = DozerMapper.parseListObjects(repository.findAll(), BooksVO::class.java)
            for(book in listBooks){
                val withSeftRel = linkTo(BooksController::class.java).slash(book.key).withSelfRel()
                book.add(withSeftRel)
            }

        return listBooks
    }

    fun findById(id: Long): BooksVO {
        logger.info("Finding Book for ID !!!")
        val book = repository.findById(id)
            .orElseThrow{ ResourceNotFoundException("Id Not Found")}

        val bookVO : BooksVO = DozerMapper.parseObject(book, BooksVO::class.java)
        val withSeftRel = linkTo(BooksController::class.java).slash(bookVO.key).withSelfRel()
        bookVO.add(withSeftRel)
        return bookVO
    }

    fun create(booksVO: BooksVO): BooksVO{
        logger.info("Insert new Book!!!")
//        val book: Books = DozerMapper.parseObject(booksVO, Books::class.java)
        val book: Books = booksVO.toEntity()

        val vo : BooksVO = DozerMapper.parseObject(repository.save(book), BooksVO::class.java)
        val withSelfRel = linkTo(BooksController::class.java).slash(vo.key).withSelfRel()
        vo.add(withSelfRel)
        return vo

    }
    fun update(booksVO: BooksVO): BooksVO{
        logger.info("Update a Book!!!")
        val book = repository.findById(booksVO.key)
            .orElseThrow { ResourceNotFoundException("Book with is Id is not found!!") }
            book.author = booksVO.author
            book.launchDate = booksVO.launchDate
            book.price = booksVO.price
            book.title = booksVO.title

        val vo : BooksVO = DozerMapper.parseObject(repository.save(book), BooksVO::class.java)
        val withSelfRel = linkTo(BooksController::class.java).slash(vo.key).withSelfRel()
        vo.add(withSelfRel)
        return vo

    }

    fun delete(id: Long){
        logger.info("Deleting Book with Id: $id")
            val book = repository.findById(id)
                .orElseThrow{ ResourceNotFoundException("Book with is Id is not found!!")}

         repository.delete(book)
    }


}