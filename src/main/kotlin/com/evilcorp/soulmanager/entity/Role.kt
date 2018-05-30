package com.evilcorp.soulmanager.entity

import javax.persistence.*

@Entity
data class Role(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val name: String = "",
        @ManyToMany val users: List<User> = emptyList()
)