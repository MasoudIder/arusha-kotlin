package com.example.kotlin

import com.example.kotlin.entities.Document
import com.example.kotlin.entities.Person
import com.example.kotlin.entities.repositories.DocumentRepository
import com.example.kotlin.entities.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class StartUp(
        @Autowired val personRepository: PersonRepository,
        @Autowired val documentRepository: DocumentRepository
) : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(p0: ContextRefreshedEvent) {

        var document1 = Document("Cloud_Computing")
        var document2 = Document("Data_Base")
        var document3 = Document("Machine_Learning")
        var document4 = Document("Algorithm")

        var person1 = Person("ali", "ahmadi")
        var person2 = Person("reza", "rahmani")
        var person3 = Person("hassan", "rostami")
        var person4 = Person("mohammad", "akbari")

        var p1_documents = HashSet<Document>()
        var p2_documents = HashSet<Document>()
        var p3_documents = HashSet<Document>()
        var p4_documents = HashSet<Document>()

        p1_documents.add(document1)
        p1_documents.add(document2)

        p2_documents.add(document3)
        p2_documents.add(document4)

        p3_documents.add(document1)
        p3_documents.add(document4)

        p3_documents.add(document2)
        p3_documents.add(document4)

        person1.documentSet = p1_documents
        person2.documentSet = p2_documents
        person3.documentSet = p3_documents
        person4.documentSet = p4_documents

        documentRepository.save(document1)
        documentRepository.save(document2)
        documentRepository.save(document3)
        documentRepository.save(document4)

        personRepository.save(person1)
        personRepository.save(person2)
        personRepository.save(person3)
        personRepository.save(person4)

    }
}

