package com.evilcorp.soulmanager.service

import com.evilcorp.soulmanager.entity.items.Bag
import com.evilcorp.soulmanager.entity.items.Pocket
import com.evilcorp.soulmanager.entity.items.ValueAbleGood
import com.evilcorp.soulmanager.entity.items.Wallet
import com.evilcorp.soulmanager.repository.items.BagRepository
import com.evilcorp.soulmanager.repository.items.PocketRepository
import com.evilcorp.soulmanager.repository.items.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl : TransactionService {
    @Autowired
    lateinit var bagRepository: BagRepository
    @Autowired
    lateinit var pocketRepository: PocketRepository
    @Autowired
    lateinit var walletRepository: WalletRepository

    override fun send(from: ValueAbleGood, to: ValueAbleGood, amount: Long) {
        if (withdraw(from, amount)) {
            deposit(to, amount)
            print("Transaction was successful")
        } else print("Transaction failed")
    }

    override fun withdraw(from: ValueAbleGood, amount: Long): Boolean {
        return if (from.value >= amount) {
            print("Withdrawing $amount gold from ${from.owner.fullName}'s ${from.name}")
            from.value -= amount
            when (from) {
                is Bag -> bagRepository.save(from)
                is Pocket -> pocketRepository.save(from)
                is Wallet -> walletRepository.save(from)
            }
            true
        } else {
            print("Cannot withdraw $amount gold from ${from.owner.fullName}'s ${from.name}. Balance is to low.")
            false
        }
    }

    override fun deposit(to: ValueAbleGood, amount: Long) {
        print("Depositing $amount gold from ${to.owner.fullName}'s ${to.name}")
        to.value += amount
        when (to) {
            is Bag -> bagRepository.save(to)
            is Pocket -> pocketRepository.save(to)
            is Wallet -> walletRepository.save(to)
        }
    }
}