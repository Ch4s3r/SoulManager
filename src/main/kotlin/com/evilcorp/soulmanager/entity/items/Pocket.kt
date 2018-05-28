package com.evilcorp.soulmanager.entity.items

import com.evilcorp.soulmanager.entity.Soul
import javax.persistence.Entity

@Entity
class Pocket(id: Long,
             owner: Soul,
             value: Long)
    : ValueAbleGood(id, owner, "pocket", value)