package com.evilcorp.soulmanager.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.evilcorp.soulmanager.config.JwtUtil
import com.evilcorp.soulmanager.dto.SigninPayload
import com.evilcorp.soulmanager.entity.User
import com.evilcorp.soulmanager.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class LoginResolver(val userRepository: UserRepository, val jwtUtil: JwtUtil) : GraphQLQueryResolver, GraphQLMutationResolver {

    fun signup(user: User): SigninPayload {
        userRepository.findByEmail(user.email)?.let {
            throw Exception("email is already existing")
        }
        val user = userRepository.save(user.copy(password = BCrypt.hashpw(user.password, BCrypt.gensalt())))
        return SigninPayload(user = user, token = jwtUtil.generateToken(user))
    }

    fun signin(email: String, password: String): SigninPayload? {
        val user = userRepository.findByEmail(email)
                ?: throw UsernameNotFoundException("cant find user with email: $email")
        if (BCrypt.checkpw(password, user.password)) {
            return SigninPayload(user = user, token = jwtUtil.generateToken(user))
        }
        return null
    }
}