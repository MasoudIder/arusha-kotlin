package com.example.kotlin.services.implementations

import com.example.kotlin.entities.Document
import com.example.kotlin.entities.repositories.DocumentRepository
import com.example.kotlin.services.DocumentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DocumentServiceImp(@Autowired val repository: DocumentRepository) : DocumentService {
    override fun findByID(id: Int): Optional<Document> {
        return repository.findById(id)
    }

    override fun findAll(): Set<Document> {
        var documents = HashSet<Document>()

        repository.findAll().forEach {
            documents.add(it)
        }

        return documents

    }

    override fun save(document: Document): Document {
        return repository.save(document)
    }

    override fun delete(id: Int) {
        return repository.deleteById(id)
    }
}
