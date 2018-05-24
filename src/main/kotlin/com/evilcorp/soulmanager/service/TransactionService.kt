package com.evilcorp.soulmanager.service

import com.evilcorp.soulmanager.entity.items.ValueAbleGood


interface TransactionService {
    fun send(from: ValueAbleGood, to: ValueAbleGood, amount: Long)
    fun withdraw(from: ValueAbleGood, amount: Long): Boolean
    fun deposit(to: ValueAbleGood, amount: Long)
}