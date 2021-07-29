package com.example.kotlin.entities.repositories

import com.example.kotlin.entities.Person
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CrudRepository<Person, Int> {
}
