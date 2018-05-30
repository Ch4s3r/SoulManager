package com.evilcorp.soulmanager.entity.items

import com.evilcorp.soulmanager.entity.User
import javax.persistence.Entity

@Entity
class Pocket(id: Long,
             owner: User,
             value: Long)
    : ValueAbleGood(id, owner, "pocket", value)