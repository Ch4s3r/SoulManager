package com.evilcorp.soulmanager.resolver

import com.coxautodev.graphql.tools.GraphQLResolver
import com.evilcorp.soulmanager.dto.SigninPayload
import org.springframework.stereotype.Component

@Component
class SigninResolver : GraphQLResolver<SigninPayload> {
    fun user(signinPayload: SigninPayload) = signinPayload.user
}