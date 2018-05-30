package com.evilcorp.soulmanager.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.evilcorp.soulmanager.entity.User
import com.evilcorp.soulmanager.entity.valueablegood.ValueAbleGood
import com.evilcorp.soulmanager.repository.UserRepository
import com.evilcorp.soulmanager.repository.valueablegood.ValueAbleGoodRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserResolver(val userRepository: UserRepository,
                   val valueAbleGoodRepository: ValueAbleGoodRepository) : GraphQLQueryResolver, GraphQLMutationResolver {

    @PreAuthorize("hasRole('USER')")
    fun listUsers(): List<User> {
        return userRepository.findAll()
    }

    @PreAuthorize("hasRole('ADMIN')")
    fun listUser(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    @PreAuthorize("hasRole('USER')")
    fun listMyAccount(valueAbleGoodID: Long): ValueAbleGood {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = userRepository.findByUsername(authentication.principal.toString())
                ?: throw UsernameNotFoundException("")
        val valueAbleGood = valueAbleGoodRepository.findById(valueAbleGoodID).get()
//            throw Exception("valueAbleGood not found: $valueAbleGoodID")
        if (user.id == valueAbleGood.owner.id)
            return valueAbleGood
        else
            throw Exception("Not the owner of: $valueAbleGoodID")
    }

    @PreAuthorize("hasRole('USER')")
    fun listMyAccounts(): List<ValueAbleGood> {
        val authentication = SecurityContextHolder.getContext().authentication
        return valueAbleGoodRepository.findByOwnerUsername(authentication.principal.toString())
    }
}