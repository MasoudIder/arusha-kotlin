package com.example.kotlin.responeModel

import com.example.kotlin.entities.Document
import java.util.*

class DocumentResponseModel {

    var id: Int? = null
    var name: String? = null
    var authors = HashSet<String>()


    constructor(document: Document) {
        this.id = document.id
        this.name = document.name
        document.personSet.forEach {
            this.authors.add(it.firstName + "_" + it.lastName)
        }
    }
}
