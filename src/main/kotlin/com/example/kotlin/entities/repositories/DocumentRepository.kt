package com.example.kotlin.entities.repositories

import com.example.kotlin.entities.Document
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository:CrudRepository<Document,Int> {
}
