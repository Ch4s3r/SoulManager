package com.evilcorp.soulmanager.entity.valueablegood

import com.evilcorp.soulmanager.entity.User
import javax.persistence.Entity

@Entity
class Wallet(id: Long = 0,
             owner: User = User(),
             value: Long = 0,
             val color: String = "grey",
             val weight: Long = 10)
    : ValueAbleGood(id, owner, "wallet", value)