package com.evilcorp.soulmanager.entity.valueablegood

import com.evilcorp.soulmanager.entity.User
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class ValueAbleGood(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @ManyToOne val owner: User = User(),
        val name: String = "",
        var value: Long = 0)