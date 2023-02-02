package com.asmanmirza.schoolpen.domain.model.auth

data class Data(
    val roles: List<Role>,
    val token: String
)