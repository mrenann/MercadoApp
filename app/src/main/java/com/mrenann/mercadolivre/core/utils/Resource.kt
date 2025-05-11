package com.mrenann.mercadolivre.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}

fun <T> Flow<T>.asResource(): Flow<Resource<T>> = this
    .map { Resource.Success(it) as Resource<T> }
    .onStart { emit(Resource.Loading) }
    .catch { emit(Resource.Error(it.message ?: "Erro desconhecido")) }
