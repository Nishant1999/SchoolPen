package com.asmanmirza.schoolpen.common

/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 01-10-2022
 * @Time: 05:19
 */
sealed class NetworkResponse<T>(
    val _data: T? = null,
    val _message: String? = null) {
    class Success<T>(data: T) : NetworkResponse<T>(data)
    class Error<T>(message: String?) : NetworkResponse<T>(_message = message)
    class Loading<T> : NetworkResponse<T>()
    class Idle<T> : NetworkResponse<T>()
}