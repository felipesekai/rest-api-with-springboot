package br.com.sekai.mapper.custom

import br.com.sekai.data.vo.v2.PersonVO
import br.com.sekai.model.Person

fun Person.toVO() = PersonVO(id = this.id,  firstName = this.firstName,
        lastName = this.lastName,
        address = this.address,
        gender = this.gender,
        brithday = this.birthday)

fun PersonVO.toEntity() = Person(id = this.id,
    firstName = this.firstName,
        lastName = this.lastName,
        address = this.address,
        gender = this.gender,
        )