package com.evilcorp.soulmanager.repository.items

import com.evilcorp.soulmanager.entity.items.Wallet
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository : ValueAbleGoodRepository<Wallet, Long> {

}