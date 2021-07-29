package com.example.kotlin.responeModel

import com.example.kotlin.entities.Document
import com.example.kotlin.entities.Person
import java.util.*

class PersonResponseModel {
    var id: Int? = null
    var firstName: String? = "name"
    var lastName: String? = "lastName"
    var documentSet = HashSet<String>()

    constructor(person: Person) {
        this.id = person.id
        this.firstName = person.firstName
        this.lastName = person.lastName
        person.documentSet.forEach {
            this.documentSet.add(it.name.toString())
        }
    }
}
