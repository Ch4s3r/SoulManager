package com.evilcorp.soulmanager.repository.items

import com.evilcorp.soulmanager.entity.items.Pocket
import org.springframework.stereotype.Repository

@Repository
interface PocketRepository : ValueAbleGoodRepository<Pocket, Long> {

}