package com.evilcorp.soulmanager.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.evilcorp.soulmanager.repository.UserRepository
import com.evilcorp.soulmanager.repository.valueablegood.ValueAbleGoodRepository
import com.evilcorp.soulmanager.service.TransactionService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class TransactionResolver(val transactionService: TransactionService,
                          val valueAbleGoodRepository: ValueAbleGoodRepository,
                          val userRepository: UserRepository) : GraphQLQueryResolver, GraphQLMutationResolver {

    @PreAuthorize("hasRole('USER')")
    fun withdraw(valueAbleGoodID: Long, amount: Long): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = userRepository.findByUsername(authentication.principal.toString())
                ?: throw UsernameNotFoundException("")
        val valueAbleGood = valueAbleGoodRepository.findById(valueAbleGoodID).get()
        if (user.id == valueAbleGood.owner.id) {
            return transactionService.withdraw(valueAbleGood, amount)
        }
        return false
    }

    @PreAuthorize("hasRole('USER')")
    fun deposit(valueAbleGoodID: Long, amount: Long): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = userRepository.findByUsername(authentication.principal.toString())
                ?: throw UsernameNotFoundException("")
        val valueAbleGood = valueAbleGoodRepository.findById(valueAbleGoodID).get()
        if (user.id == valueAbleGood.owner.id) {
            transactionService.deposit(valueAbleGood, amount)
        }
        return true
    }

    @PreAuthorize("hasRole('USER')")
    fun send(fromValueAbleGoodID: Long, toValueAbleGoodID: Long, amount: Long): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = userRepository.findByUsername(authentication.principal.toString())
                ?: throw UsernameNotFoundException("")
        val fromValueAbleGood = valueAbleGoodRepository.findById(fromValueAbleGoodID).get()
        val toValueAbleGood = valueAbleGoodRepository.findById(toValueAbleGoodID).get()
        if (user.id == fromValueAbleGood.owner.id) {
            return transactionService.send(fromValueAbleGood, toValueAbleGood, amount)
        }
        return false
    }

}