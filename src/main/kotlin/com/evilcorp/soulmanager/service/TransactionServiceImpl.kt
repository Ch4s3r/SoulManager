package com.evilcorp.soulmanager.service

import com.evilcorp.soulmanager.entity.valueablegood.ValueAbleGood
import com.evilcorp.soulmanager.repository.valueablegood.ValueAbleGoodRepository
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl(
//        val bagRepository: BagRepository,
//        val pocketRepository: PocketRepository,
//        val walletRepository: WalletRepository,
        val valueAbleGoodRepository: ValueAbleGoodRepository) : TransactionService {

//    private fun saveValueAbleGood(valueAbleGood: ValueAbleGood) {
////        when (valueAbleGood) {
////            is Bag -> bagRepository.save(valueAbleGood)
////            is Pocket -> pocketRepository.save(valueAbleGood)
////            is Wallet -> walletRepository.save(valueAbleGood)
////        }
//    }

    override fun send(fromValueAbleGood: ValueAbleGood, toValueAbleGood: ValueAbleGood, amount: Long): Boolean {
        if (withdraw(fromValueAbleGood, amount)) {
            deposit(toValueAbleGood, amount)
            println("Transaction was successful")
            return true
        } else println("Transaction failed")
        return false
    }

    override fun withdraw(valueAbleGood: ValueAbleGood, amount: Long): Boolean {
        return if (valueAbleGood.value >= amount) {
            println("Withdrawing $amount gold from ${valueAbleGood.owner.fullname}'s ${valueAbleGood.name}")
            valueAbleGood.value -= amount
            valueAbleGoodRepository.save(valueAbleGood)
            true
        } else {
            println("Cannot withdraw $amount gold from ${valueAbleGood.owner.fullname}'s ${valueAbleGood.name}. Balance is to low.")
            false
        }
    }

    override fun deposit(valueAbleGood: ValueAbleGood, amount: Long): Boolean {
        println("Depositing $amount gold from ${valueAbleGood.owner.fullname}'s ${valueAbleGood.name}")
        valueAbleGood.value += amount
        valueAbleGoodRepository.save(valueAbleGood)
        return true
    }
}