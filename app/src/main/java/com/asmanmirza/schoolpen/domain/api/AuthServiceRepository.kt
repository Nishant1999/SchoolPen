package com.asmanmirza.schoolpen.domain.api

import com.asmanmirza.schoolpen.common.NetworkResponse
import com.asmanmirza.schoolpen.domain.model.auth.AuthResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 30-09-2022
 * @Time: 12:09
 */
interface AuthServiceRepository {
    suspend fun login(json: JsonObject): Flow<NetworkResponse<AuthResponse>>
    suspend fun register(json: JsonObject): Flow<NetworkResponse<AuthResponse>>
    suspend fun generateOtp(json: JsonObject): Flow<NetworkResponse<AuthResponse>>
    suspend fun verifyOtp(json: JsonObject): Flow<NetworkResponse<AuthResponse>>
}