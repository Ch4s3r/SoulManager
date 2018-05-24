package com.evilcorp.soulmanager.repository

import com.evilcorp.soulmanager.entity.Soul
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SoulRepository : JpaRepository<Soul, Long> {
}