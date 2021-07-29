package com.example.kotlin.services.implementations

import com.example.kotlin.entities.Person
import com.example.kotlin.entities.repositories.PersonRepository
import com.example.kotlin.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonServiceImp(@Autowired val repository: PersonRepository) : PersonService {

    override fun findByID(id: Int): Optional<Person> {
        return repository.findById(id)
    }

    override fun findAll(): Set<Person> {
        var persons = HashSet<Person>()
        repository.findAll().forEach {
            persons.add(it)
        }
        return persons
    }

    override fun save(person: Person): Person {
        return repository.save(person)
    }

    override fun delete(id: Int) {
        return repository.deleteById(id)
    }
}
