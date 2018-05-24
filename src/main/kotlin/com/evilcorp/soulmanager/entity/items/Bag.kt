package com.evilcorp.soulmanager.entity.items

import com.evilcorp.soulmanager.entity.Soul
import javax.persistence.Entity

@Entity
class Bag(id: Long,
          owner: Soul,
          value: Long,
          val size: BagSize,
          val color: String)
    : ValueAbleGood(id, owner, "bag", value)