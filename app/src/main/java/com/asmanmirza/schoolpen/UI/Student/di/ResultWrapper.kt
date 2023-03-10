package com.asmanmirza.schoolpen.UI.Student.di

import androidx.annotation.Keep

/**
 * Network response wrapper for handling Success and Failure response types
 */
sealed class ResultWrapper<out T> {

    @Keep
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    @Keep
    data class GenericError(val code: Int? = null, val error: NetworkHelper.ErrorResponse? = null) : ResultWrapper<Nothing>()
}