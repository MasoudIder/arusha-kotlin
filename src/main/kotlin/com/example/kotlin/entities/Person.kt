package com.example.kotlin.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var firstName: String? = "firstName"
    var lastName: String? = "lastName"

    @ManyToMany
    @JoinTable(
            name = "Person_Document",
            joinColumns = [JoinColumn(
                    name = "person_id"
            )],
            inverseJoinColumns = [JoinColumn(
                    name = "document_id"
            )]
    )
    var documentSet: Set<Document> = HashSet<Document>()

    constructor() {}

    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }


    constructor(firstName: String, lastName: String, documentSet: HashSet<Document>) {
        this.firstName = firstName
        this.lastName = lastName
        this.documentSet = documentSet
    }
}
