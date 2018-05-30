package com.evilcorp.soulmanager.repository.valueablegood

import com.evilcorp.soulmanager.entity.valueablegood.ValueAbleGood
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ValueAbleGoodRepository : JpaRepository<ValueAbleGood, Long> {
    fun findByName(name: String): ValueAbleGood?
    fun findByOwnerUsername(username: String): List<ValueAbleGood>
}