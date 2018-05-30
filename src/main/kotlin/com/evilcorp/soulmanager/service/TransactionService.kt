package com.evilcorp.soulmanager.service

import com.evilcorp.soulmanager.entity.valueablegood.ValueAbleGood


interface TransactionService {
    fun withdraw(valueAbleGood: ValueAbleGood, amount: Long): Boolean
    fun deposit(valueAbleGood: ValueAbleGood, amount: Long): Boolean
    fun send(fromValueAbleGood: ValueAbleGood, toValueAbleGood: ValueAbleGood, amount: Long): Boolean
}