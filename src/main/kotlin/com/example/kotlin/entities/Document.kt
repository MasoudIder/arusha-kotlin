package com.example.kotlin.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Document")
class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null


    var name: String? = "Name"

    @ManyToMany(mappedBy = "documentSet")
    var personSet:Set<Person> = HashSet<Person>()


    constructor() {}

    constructor(name: String) {
        this.name = name
    }

    constructor(name: String, personSet: HashSet<Person>) {
        this.name = name
        this.personSet = personSet
    }
}
