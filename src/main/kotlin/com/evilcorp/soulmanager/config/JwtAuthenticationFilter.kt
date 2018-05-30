package com.evilcorp.soulmanager.config

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(val jwtUtil: JwtUtil) : OncePerRequestFilter() {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authorizationHeader: String = request.getHeader("Authorization") ?: ""
        if (authorizationHeader.startsWith("Bearer ")) {
            try {
                val user = jwtUtil.parseToken(authorizationHeader.slice(7 until authorizationHeader.length))
                        ?: throw Exception("cannot parse jwt token: '$authorizationHeader'")
                val authResult = UsernamePasswordAuthenticationToken(user.username, null, user.roles.map { role -> SimpleGrantedAuthority(role.name) }.toMutableList())
                authResult.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authResult
            } catch (e: Exception) {
                SecurityContextHolder.clearContext()
            }
        }
        filterChain.doFilter(request, response)
    }
}