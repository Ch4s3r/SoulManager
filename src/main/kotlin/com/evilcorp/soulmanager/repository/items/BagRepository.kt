package com.evilcorp.soulmanager.repository.items

import com.evilcorp.soulmanager.entity.items.Bag
import org.springframework.stereotype.Repository

@Repository
interface BagRepository : ValueAbleGoodRepository<Bag, Long> {

}