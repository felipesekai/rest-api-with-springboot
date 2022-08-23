package br.com.sekai.repository

import br.com.sekai.model.Books
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BooksRepository: JpaRepository<Books, Long?> {
}