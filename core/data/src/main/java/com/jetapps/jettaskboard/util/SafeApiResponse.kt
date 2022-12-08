package com.jetapps.jettaskboard.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCallResponse(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>,
): NetworkResponse<T> {
    return withContext(dispatcher) {
        try {
            val result = apiCall.invoke()
            val response = result as Response<*>
            if (response.isSuccessful && result.body() != null) {
                result.body()?.let { safeResponseBody ->
                    NetworkResponse.Success(safeResponseBody)
                } ?: NetworkResponse.Failure(Throwable("Empty Response body"))
            } else {
                // TODO : Return appropriate error msg from API
                NetworkResponse.Failure(Throwable(message = response.errorBody().toString()))
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> NetworkResponse.Unauthorized(Throwable("No connection found"))
                is HttpException -> NetworkResponse.Failure(
                    throwable = Throwable(throwable.message)
                )
                else -> NetworkResponse.Failure(Exception(throwable.message.orEmpty()))
            }
        }
    }
}