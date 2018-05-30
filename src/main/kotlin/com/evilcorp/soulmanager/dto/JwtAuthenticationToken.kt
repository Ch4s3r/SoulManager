package com.evilcorp.soulmanager.dto

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils

class JwtAuthenticationToken(val token: String) : AbstractAuthenticationToken(AuthorityUtils.NO_AUTHORITIES) {

    override fun getCredentials(): Any {
        return "N/A"
    }

    override fun getPrincipal(): Any {
        return name
    }
}