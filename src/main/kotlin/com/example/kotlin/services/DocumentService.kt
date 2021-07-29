package com.example.kotlin.services

import com.example.kotlin.entities.Document
import java.util.*

interface DocumentService {

    fun findByID(id: Int): Optional<Document>

    fun findAll(): Set<Document>

    fun save(document: Document): Document

    fun delete(id: Int)
}
