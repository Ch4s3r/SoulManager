package com.evilcorp.soulmanager.repository.items

import com.evilcorp.soulmanager.entity.items.ValueAbleGood
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface ValueAbleGoodRepository<T : ValueAbleGood, ID : Serializable> : JpaRepository<T, ID> {
    fun findByName(name: String)
}