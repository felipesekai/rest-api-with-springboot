package br.com.sekai.mapper.custom

import br.com.sekai.data.vo.v2.PersonVO as PersonVOV2
import br.com.sekai.model.Person
import org.springframework.stereotype.Service

@Service
class PersonMapper {

    fun mapEntityToVo(person: Person): PersonVOV2{
        val vo = PersonVOV2()

        vo.id = person.id
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.address = person.address
        vo.gender = person.gender
        vo.brithday = person.birthday
        return vo
    }
    fun mapVoToEntity(person: PersonVOV2): Person {
        val entity = Person()
        entity.id = person.id
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        entity.birthday = person.brithday
        return entity
    }

    fun mapListEntityToVO(person: List<Person>): ArrayList<PersonVOV2> {
        val entity: ArrayList<PersonVOV2> = ArrayList()

            for (e in person){
                entity.add(mapEntityToVo(e))
            }

        return entity
    }
}
