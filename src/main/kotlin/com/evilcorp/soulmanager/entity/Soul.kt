package com.evilcorp.soulmanager.entity

import com.evilcorp.soulmanager.entity.items.Bag
import com.evilcorp.soulmanager.entity.items.Pocket
import com.evilcorp.soulmanager.entity.items.Wallet
import javax.persistence.*

@Entity
data class Soul(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
        val firstName: String,
        val lastName: String,
        val fullName: String = "$firstName $lastName",
        val username: String,
        val email: String,
        val password: String,
        @OneToMany(mappedBy = "from") val transactionsFrom: Set<Transaction>,
        @OneToMany(mappedBy = "to") val transactionsTo: Set<Transaction>,
        @OneToMany(mappedBy = "owner") val bags: Set<Bag>,
        @OneToMany(mappedBy = "owner") val pockets: Set<Pocket>,
        @OneToMany(mappedBy = "owner") val wallets: Set<Wallet>
)