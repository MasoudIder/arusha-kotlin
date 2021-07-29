package com.example.kotlin.services

import com.example.kotlin.entities.Person
import java.util.*

interface PersonService {

    fun findByID(id: Int):Optional<Person>;

    fun findAll(): Set<Person>

    fun save(person: Person): Person

    fun delete(id: Int)
}
