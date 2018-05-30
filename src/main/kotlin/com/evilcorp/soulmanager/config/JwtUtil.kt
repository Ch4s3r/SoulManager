package com.evilcorp.soulmanager.config

import com.evilcorp.soulmanager.entity.Role
import com.evilcorp.soulmanager.entity.User
import com.evilcorp.soulmanager.repository.RoleRepository
import com.evilcorp.soulmanager.repository.UserRepository
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct
import javax.management.relation.RoleNotFoundException

@Component
@DependsOn("databaseInitializer")
class JwtUtil(val userRepository: UserRepository, val roleRepository: RoleRepository) {

    //    @Value("\${jwt.secret}")
    val secret: String = "your-256-bit-secret"
    lateinit var roles: Map<String, Role>

    @PostConstruct
    fun init() {
        roles = roleRepository.findAll().map {
            it.name to it
        }.toMap()
    }

    fun parseToken(token: String): User? {
        return try {
            val body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
            User(username = body["name"].toString(),
                    id = body.subject.toLong(),
                    roles = (body["roles"] as List<String>).map {
                        roles[it] ?: throw RoleNotFoundException()
                    }.toList())
        } catch (e: JwtException) {
            e.printStackTrace()
            null
        } catch (e: ClassCastException) {
            e.printStackTrace()
            null
        }
    }

    fun generateToken(user: User): String {
        val claims = Jwts.claims().setSubject(user.id.toString()).setExpiration(Date.from(Instant.now().plusSeconds(24 * 60 * 60)))
        val user = userRepository.findByUsername(user.username)
                ?: throw Exception("username not found: ${user.username}")
        claims["name"] = user.username
        claims["roles"] = user.roles.map { it.name }
        return Jwts.builder().setHeaderParam("typ", "JWT").setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact()
    }
}