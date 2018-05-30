package com.evilcorp.soulmanager.service

import com.evilcorp.soulmanager.entity.items.Bag
import com.evilcorp.soulmanager.entity.items.Pocket
import com.evilcorp.soulmanager.entity.items.ValueAbleGood
import com.evilcorp.soulmanager.entity.items.Wallet
import com.evilcorp.soulmanager.repository.items.BagRepository
import com.evilcorp.soulmanager.repository.items.PocketRepository
import com.evilcorp.soulmanager.repository.items.WalletRepository
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl(
        val bagRepository: BagRepository,
        val pocketRepository: PocketRepository,
        val walletRepository: WalletRepository) : TransactionService {

    private fun saveValueAbleGood(valueAbleGood: ValueAbleGood) {
        when (valueAbleGood) {
            is Bag -> bagRepository.save(valueAbleGood)
            is Pocket -> pocketRepository.save(valueAbleGood)
            is Wallet -> walletRepository.save(valueAbleGood)
        }
    }

    override fun send(from: ValueAbleGood, to: ValueAbleGood, amount: Long) {
        if (withdraw(from, amount)) {
            deposit(to, amount)
            print("Transaction was successful")
        } else print("Transaction failed")
    }

    override fun withdraw(from: ValueAbleGood, amount: Long): Boolean {
        return if (from.value >= amount) {
            print("Withdrawing $amount gold from ${from.owner.fullname}'s ${from.name}")
            from.value -= amount
            saveValueAbleGood(from)
            true
        } else {
            print("Cannot withdraw $amount gold from ${from.owner.fullname}'s ${from.name}. Balance is to low.")
            false
        }
    }

    override fun deposit(to: ValueAbleGood, amount: Long) {
        print("Depositing $amount gold from ${to.owner.fullname}'s ${to.name}")
        to.value += amount
        saveValueAbleGood(to)
    }
}