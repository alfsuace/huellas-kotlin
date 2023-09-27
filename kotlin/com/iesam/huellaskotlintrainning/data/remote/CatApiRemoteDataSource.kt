package com.iesam.huellaskotlintrainning.data.remote

import com.iesam.huellaskotlintrainning.app.Either
import com.iesam.huellaskotlintrainning.app.api.ApiClient
import com.iesam.huellaskotlintrainning.app.left
import com.iesam.huellaskotlintrainning.app.right
import com.iesam.huellaskotlintrainning.domain.Cat
import com.iesam.huellaskotlintrainning.domain.errors.ErrorApp
import retrofit2.Response
import java.io.IOException

class CatApiRemoteDataSource {

    private val apiClient: ApiClient = ApiClient()

    fun getCats(): Either<ErrorApp, List<Cat>> {
        try {
            val responseCats: Response<List<Cat>> = apiClient.apiService.getCats().execute()
            if (responseCats.isSuccessful) {
                return responseCats.body()!!.right()
            } else {
                throw RuntimeException()
            }
        } catch (e: IOException) {
            return ErrorApp.InternetErrorApp.left()
        } catch (e: Exception) {
            return ErrorApp.UnknowErrorApp.left()
        }
    }

    fun getCatsWithError(): Either<ErrorApp, List<Cat>> {
        return when ((0..2).random()) {
            0 -> ErrorApp.UnknowErrorApp.left()
            1 -> ErrorApp.InternetErrorApp.left()
            2 -> ErrorApp.DatabaseErrorApp.left()
            else -> ErrorApp.UnknowErrorApp.left()
        }
    }
}