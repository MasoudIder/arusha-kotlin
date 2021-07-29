package com.example.kotlin.controllers

import com.example.kotlin.entities.Document
import com.example.kotlin.responeModel.DocumentResponseModel
import com.example.kotlin.services.DocumentService
import com.example.kotlin.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/document")
class DocumentController(
        @Autowired val personService: PersonService,
        @Autowired val documentService: DocumentService
) {

    @GetMapping
    fun getAll(): ResponseEntity<Set<DocumentResponseModel>> {

        var responseModels = HashSet<DocumentResponseModel>()

        documentService.findAll().forEach {
            responseModels.add(DocumentResponseModel(it))
        }

        return ResponseEntity(responseModels, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Any> {

        var document = documentService.findByID(Integer.valueOf(id))
        return if (document.isPresent) {
            ResponseEntity(DocumentResponseModel(document.get()), HttpStatus.OK)
        } else {
            ResponseEntity("Not Found", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun save(@RequestBody document: Document): ResponseEntity<Any> {
        return ResponseEntity(documentService.save(document), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody document: Document): ResponseEntity<Any> {
        val documentOptional = documentService.findByID(Integer.valueOf(id))
        return if (documentOptional.isPresent) {

            val foundDocument = documentOptional.get()
            foundDocument.name = document.name
            foundDocument.personSet = document.personSet
            ResponseEntity(documentService.save(foundDocument), HttpStatus.CREATED)

        } else {
            ResponseEntity("Not Found ", HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping
    fun delete(@RequestBody id: String): ResponseEntity<Any> {

        val optionalDocument = documentService.findByID(Integer.valueOf(id))
        return if (optionalDocument.isPresent) {

            val document = optionalDocument.get()
            document.personSet.forEach {

                var documentSet= HashSet<Document>(it.documentSet)
                documentSet.remove(document)
                it.documentSet = documentSet
                personService.save(it)

            }
            val documentId=document.id
            if (documentId != null) {
                documentService.delete(documentId)
            }

            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}
