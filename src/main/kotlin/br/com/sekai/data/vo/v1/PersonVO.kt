package br.com.sekai.data.vo.v1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id","name", "lastName" ,"gender"  ,"address")
data class PersonVO(
    var id: Long = 0,
    @JsonProperty("name")
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",

    @JsonIgnore //nao envia esse campo
    var gender: String = "",
)
