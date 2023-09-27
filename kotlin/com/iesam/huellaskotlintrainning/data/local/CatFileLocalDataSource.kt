package com.iesam.huellaskotlintrainning.data.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iesam.huellaskotlintrainning.domain.Cat
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException
import java.util.*

class CatFileLocalDataSource private constructor() {
    private val nameFile = "cats.txt"
    private val gson = Gson()
    private val typeList = object : TypeToken<ArrayList<Cat>>() {}.type

    fun save(cat: Cat) {
        val cats: MutableList<Cat> = findAll()
        cats.add(cat)
        saveToFile(cats)
    }

    fun saveList(cats: List<Cat>) {
        saveToFile(cats)
    }

    private fun saveToFile(cats: List<Cat>) {
        try {
            val myWriter = FileWriter(nameFile)
            myWriter.write(gson.toJson(cats))
            myWriter.close()
            println("Datos guardados correctamente")
        } catch (e: IOException) {
            println("Ha ocurrido un error al guardar la información.")
            e.printStackTrace()
        }
    }

    fun findById(catId: Int): Cat? {
        val cats: List<Cat> = findAll()
        return cats.find { it.id == catId }
    }

    fun findAll(): MutableList<Cat> {
        try {
            val myObj = File(nameFile)
            if (!myObj.exists()) {
                myObj.createNewFile()
            }
            val myReader: Scanner = Scanner(myObj)
            while (myReader.hasNextLine()) {
                val data = myReader.nextLine()
                myReader.close()
                return gson.fromJson<MutableList<Cat>>(data, typeList)
            }
            myReader.close()
        } catch (e: FileNotFoundException) {
            println("Ha ocurrido un error al obtener el listado.")
            e.printStackTrace()
        } catch (e: IOException) {
            println("Ha ocurrido un error al crear el fichero.")
            throw RuntimeException(e)
        }
        return ArrayList<Cat>()
    }

    fun delete(catId: Int) {
        val newCatsList: MutableList<Cat> = ArrayList<Cat>()
        val cats: List<Cat> = findAll()
        for (cat in cats) {
            if (cat.id != catId) {
                newCatsList.add(cat)
            }
        }
        saveList(newCatsList)
    }

    companion object {
        var instance: CatFileLocalDataSource? = null
            get() {
                if (field == null) {
                    field = CatFileLocalDataSource()
                }
                return field
            }
            private set
    }
}