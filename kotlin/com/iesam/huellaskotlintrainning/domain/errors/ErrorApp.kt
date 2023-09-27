package com.iesam.huellaskotlintrainning.domain.errors

sealed class ErrorApp {
    object InternetErrorApp : ErrorApp()
    object DatabaseErrorApp : ErrorApp()
    object UnknowErrorApp : ErrorApp()
}