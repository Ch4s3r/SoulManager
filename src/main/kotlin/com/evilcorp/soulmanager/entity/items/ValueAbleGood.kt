package com.evilcorp.soulmanager.entity.items

import com.evilcorp.soulmanager.entity.Soul
import javax.persistence.*

@MappedSuperclass
abstract class ValueAbleGood(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @ManyToOne val owner: Soul,
        val name: String,
        var value: Long = 0)