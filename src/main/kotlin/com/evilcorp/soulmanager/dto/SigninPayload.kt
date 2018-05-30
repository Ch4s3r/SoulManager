package com.evilcorp.soulmanager.dto

import com.evilcorp.soulmanager.entity.User

data class SigninPayload(val token: String, val user: User)