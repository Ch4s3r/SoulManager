package com.evilcorp.soulmanager.entity.valueablegood

import com.evilcorp.soulmanager.entity.User
import javax.persistence.Entity

@Entity
class Pocket(id: Long = 0,
             owner: User = User(),
             value: Long = 0)
    : ValueAbleGood(id, owner, "pocket", value)