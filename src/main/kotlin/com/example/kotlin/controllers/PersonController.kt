package com.example.kotlin.controllers

import com.example.kotlin.entities.Person
import com.example.kotlin.responeModel.PersonResponseModel
import com.example.kotlin.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/person")
class PersonController(
        @Autowired val service: PersonService
) {

    @GetMapping
    fun getAll(): ResponseEntity<Set<PersonResponseModel>> {

        var responseModels = HashSet<PersonResponseModel>()

        service.findAll().forEach {
            responseModels.add(PersonResponseModel(it))
        }

        return ResponseEntity(responseModels, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Any> {

        var person = service.findByID(Integer.valueOf(id))
        return if (person.isPresent) {
            ResponseEntity(PersonResponseModel(person.get()), HttpStatus.OK)
        } else {
            ResponseEntity("Not Found", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun save(@RequestBody person: Person): ResponseEntity<Any> {
        return ResponseEntity(service.save(person), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody person: Person): ResponseEntity<Any> {
        val personOptional = service.findByID(Integer.valueOf(id))
        return if (personOptional.isPresent) {

            val foundPerson = personOptional.get()
            foundPerson.firstName = person.firstName
            foundPerson.lastName = person.lastName
            foundPerson.documentSet = person.documentSet

            ResponseEntity(service.save(foundPerson), HttpStatus.CREATED)

        } else {
            ResponseEntity("Not Found ", HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping
    fun delete(@RequestBody id: String): ResponseEntity<Any> {

        val optionalPerson = service.findByID(Integer.valueOf(id))

        return if (optionalPerson.isPresent) {
            val personId = optionalPerson.get().id
            if (personId != null) {
                service.delete(personId)
            }
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
