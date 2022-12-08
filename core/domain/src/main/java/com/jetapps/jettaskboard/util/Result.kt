package com.jetapps.jettaskboard.util

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Failure<T>(val message: String = "Network Error") : Result<T>()
}