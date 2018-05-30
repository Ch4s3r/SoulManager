package com.evilcorp.soulmanager.entity.valueablegood

import com.evilcorp.soulmanager.entity.User
import javax.persistence.Entity

@Entity
class Bag(id: Long = 0,
          owner: User = User(),
          value: Long = 0,
          val size: BagSize = BagSize.LARGE,
          val color: String = "blue")
    : ValueAbleGood(id, owner, "bag", value)