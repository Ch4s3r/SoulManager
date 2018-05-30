package com.evilcorp.soulmanager.entity

import com.evilcorp.soulmanager.entity.items.Bag
import com.evilcorp.soulmanager.entity.items.Pocket
import com.evilcorp.soulmanager.entity.items.Wallet
import javax.persistence.*

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val firstname: String = "",
        val lastname: String = "",
        val fullname: String = "$firstname $lastname",
        val username: String = "",
        val email: String = "",
        val password: String = "",
        @ManyToMany(mappedBy = "users") val roles: MutableList<Role> = mutableListOf(),
        @OneToMany(mappedBy = "from") val transactionsFrom: List<Transaction> = emptyList(),
        @OneToMany(mappedBy = "to") val transactionsTo: List<Transaction> = emptyList(),
        @OneToMany(mappedBy = "owner") val bags: List<Bag> = emptyList(),
        @OneToMany(mappedBy = "owner") val pockets: List<Pocket> = emptyList(),
        @OneToMany(mappedBy = "owner") val wallets: List<Wallet> = emptyList()
)