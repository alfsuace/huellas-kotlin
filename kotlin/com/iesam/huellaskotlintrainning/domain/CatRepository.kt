package com.iesam.huellaskotlintrainning.domain

interface CatRepository {
    interface CatRepository {
        fun save(cat: Cat)
        fun getAll(): List<Cat>
        fun getById(id: Int): Cat?
        fun delete(id: Int)
        fun update(cat: Cat)
    }


}