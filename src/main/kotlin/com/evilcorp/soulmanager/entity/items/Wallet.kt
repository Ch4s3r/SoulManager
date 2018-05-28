package com.evilcorp.soulmanager.entity.items

import com.evilcorp.soulmanager.entity.Soul
import javax.persistence.Entity

@Entity
class Wallet(id: Long,
             owner: Soul,
             value: Long,
             val color: String,
             val weight: Long)
    : ValueAbleGood(id, owner, "wallet", value)