package com.evilcorp.soulmanager.entity

import java.time.Instant
import javax.persistence.*

@Entity
data class Transaction(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
        @ManyToOne val from: User,
        @ManyToOne val to: User,
        val amount: Long,
        val time: Instant)
