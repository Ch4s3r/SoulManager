//package com.evilcorp.soulmanager.service
//
//import com.evilcorp.soulmanager.repository.UserRepository
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.User
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.stereotype.Component
//
//@Component
//class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
//
//    override fun loadUserByUsername(email: String): UserDetails {
//        val user = userRepository.findByEmail(email)
//                ?: throw UsernameNotFoundException("not existing: $email")
//        val authorities = user.roles.map { SimpleGrantedAuthority(it.name) }.toList()
//        return User(user.email, user.password, authorities)
//    }
//}