package com.evilcorp.soulmanager.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.evilcorp.soulmanager.entity.User
import com.evilcorp.soulmanager.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserResolver(val userRepository: UserRepository) : GraphQLQueryResolver, GraphQLMutationResolver {

    fun listUsers(): List<User> {
        return userRepository.findAll()
    }

    fun listUser(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun createUser(user: User): User {
        return userRepository.save(user)
    }
}