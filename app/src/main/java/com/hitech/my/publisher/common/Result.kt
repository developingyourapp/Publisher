package com.hitech.my.publisher.common

sealed class Result<T> (val data: T? = null, val msg: String? = null) {

    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(msg: String, data: T? = null) : Result<T>(data, msg)
    class Loading<T>(data: T? = null) : Result<T>(data)
}
