package com.evilcorp.soulmanager.config

import com.evilcorp.soulmanager.entity.User
import com.evilcorp.soulmanager.exception.JwtTokenMalformedException
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
        val authorizationHeader: String = request.getHeader("Authorization")
                ?: "Bearer ${jwtUtil.generateToken(User(username = "test", password = "pass"))}"
        if (!authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }
        try {
            val user = jwtUtil.parseToken(authorizationHeader.slice(7 until authorizationHeader.length))
                    ?: throw JwtTokenMalformedException("")
            val authResult = UsernamePasswordAuthenticationToken(user.username, null, user.roles.map { role -> SimpleGrantedAuthority(role.name) }.toMutableList())
            authResult.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authResult
        } catch (e: Exception) {
            SecurityContextHolder.clearContext()
        }
        filterChain.doFilter(request, response)
    }

//    override fun requiresAuthentication(request: HttpServletRequest, response: HttpServletResponse): Boolean {
//        return true
//    }
//
//    @Throws(AuthenticationException::class)
//    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
//        val authorizationHeader = request.getHeader("Authorization")
//        val (bearer, token) = authorizationHeader.split(" ")
//        if (bearer != "Bearer") {
//            throw JwtTokenMissingException("No JWT token found in request headers")
//        }
//        return authenticationManager.authenticate(JwtAuthenticationToken(token))
//    }
//
//    @Throws(IOException::class, ServletException::class)
//    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
//        super.successfulAuthentication(request, response, chain, authResult)
//        chain.doFilter(request, response)
//    }
}