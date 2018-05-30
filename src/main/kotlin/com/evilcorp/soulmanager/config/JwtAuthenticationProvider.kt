package com.evilcorp.soulmanager.config

//@Component
//class JwtAuthenticationProvider(val jwtUtil: JwtUtil) : AbstractUserDetailsAuthenticationProvider() {
//
//    override fun supports(authentication: Class<*>): Boolean {
//        return JwtAuthenticationToken::class.java.isAssignableFrom(authentication)
//    }
//
//    @Throws(AuthenticationException::class)
//    override fun additionalAuthenticationChecks(userDetails: UserDetails, authentication: UsernamePasswordAuthenticationToken) {
//    }
//
//    @Throws(AuthenticationException::class)
//    override fun retrieveUser(username: String, authentication: UsernamePasswordAuthenticationToken): UserDetails {
//        val jwtAuthenticationToken = authentication as JwtAuthenticationToken
//        val token = jwtAuthenticationToken.token
//        val user = jwtUtil.parseToken(token) ?: throw JwtTokenMalformedException("JWT token is not valid")
//        val authorities: MutableCollection<GrantedAuthority> = user.roles.map { SimpleGrantedAuthority(it.name) }.toMutableList()
//        return AuthenticatedUser(user.id, user.username, token, authorities)
//    }
//
//}