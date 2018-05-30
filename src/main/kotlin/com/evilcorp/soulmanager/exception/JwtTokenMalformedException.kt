package com.evilcorp.soulmanager.exception

import javax.naming.AuthenticationException

class JwtTokenMalformedException(message: String) : AuthenticationException(message)