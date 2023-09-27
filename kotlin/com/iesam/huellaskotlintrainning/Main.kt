package com.iesam.huellaskotlintrainning

import com.iesam.huellaskotlintrainning.data.CatDataRepository
import com.iesam.huellaskotlintrainning.data.local.CatFileLocalDataSource
import com.iesam.huellaskotlintrainning.data.remote.CatApiRemoteDataSource


fun main(){
    //Crea un caso de uso para cada uno de los métodos que existen en el repositorio.
    var catFileLocalDataSourceInstance = CatFileLocalDataSource.instance
    var catApiRemoteDataSourceInstance = CatApiRemoteDataSource()
    var catDataRepositoryInstance = CatDataRepository(catFileLocalDataSourceInstance, catApiRemoteDataSourceInstance)

    fun example01(){
        println(catDataRepositoryInstance.getAllOk())
    }

    fun example02(){
        println(catDataRepositoryInstance.getAllError())
    }

    fun example03(){
        println(catDataRepositoryInstance.getAllError2())
    }
    example01()
    example02()
    example03()
}
