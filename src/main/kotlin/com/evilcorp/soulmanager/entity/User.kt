package com.evilcorp.soulmanager.entity

import com.evilcorp.soulmanager.entity.valueablegood.Bag
import com.evilcorp.soulmanager.entity.valueablegood.Pocket
import com.evilcorp.soulmanager.entity.valueablegood.Wallet
import javax.persistence.*

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val firstname: String = "",
        val lastname: String = "",
        @Column(unique = true) val username: String = "",
        @Column(unique = true) val email: String = "",
        val password: String = "",
        @ManyToMany(fetch = FetchType.EAGER)
//        @JoinTable(name = "user_roles",
//                joinColumns = [(JoinColumn(name = "user_id", referencedColumnName = "id"))],
//                inverseJoinColumns = [(JoinColumn(name = "role_id", referencedColumnName = "id"))])
        val roles: List<Role> = emptyList(),
        @OneToMany(mappedBy = "from") val transactionsFrom: MutableList<Transaction> = mutableListOf(),
        @OneToMany(mappedBy = "to") val transactionsTo: MutableList<Transaction> = mutableListOf(),
        @OneToMany(mappedBy = "owner") val bags: MutableList<Bag> = mutableListOf(),
        @OneToMany(mappedBy = "owner") val pockets: MutableList<Pocket> = mutableListOf(),
        @OneToMany(mappedBy = "owner") val wallets: MutableList<Wallet> = mutableListOf()
) {
    val fullname: String
        get() = "$firstname $lastname"
}