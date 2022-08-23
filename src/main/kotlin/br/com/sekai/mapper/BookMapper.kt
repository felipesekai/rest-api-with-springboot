package br.com.sekai.mapper

import br.com.sekai.data.vo.v1.BooksVO
import br.com.sekai.model.Books


fun BooksVO.toEntity() = Books(id = this.key, author = this.author, launchDate = this.launchDate, price = this.price, title = this.title )
fun Books.toVO() = BooksVO(key = this.id, author = this.author, launchDate = this.launchDate, price = this.price, title = this.title )