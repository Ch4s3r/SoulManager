package com.evilcorp.soulmanager.entity.items

import com.evilcorp.soulmanager.entity.User
import javax.persistence.Entity

@Entity
class Wallet(id: Long,
             owner: User,
             value: Long,
             val color: String,
             val weight: Long)
    : ValueAbleGood(id, owner, "wallet", value)