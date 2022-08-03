package br.com.sekai.mapper.custom

import br.com.sekai.data.vo.v2.PersonVO as PersonVOV2
import br.com.sekai.model.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonMapper {

    fun mapEntityToVo(person: Person): PersonVOV2{
        val vo = PersonVOV2()

        vo.id = person.id
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.address = person.address
        vo.gender = person.gender
        vo.brithday = Date()

        return vo
    }
    fun mapVoToEntity(person: PersonVOV2): Person {
        val Entity = Person()

        Entity.id = person.id
        Entity.firstName = person.firstName
        Entity.lastName = person.lastName
        Entity.address = person.address
        Entity.gender = person.gender


        return Entity
    }
}
