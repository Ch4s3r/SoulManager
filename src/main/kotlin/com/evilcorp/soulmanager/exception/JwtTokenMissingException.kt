package com.evilcorp.soulmanager.exception

import javax.naming.AuthenticationException

class JwtTokenMissingException(message: String) : AuthenticationException(message)