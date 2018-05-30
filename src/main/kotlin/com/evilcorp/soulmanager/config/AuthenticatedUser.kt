//package com.evilcorp.soulmanager.config
//
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//
//class AuthenticatedUser(val id:Long, val myUserName: String, val token: String, val myAuthorities: MutableCollection<GrantedAuthority>) : UserDetails {
//    override fun getAuthorities() = myAuthorities
//    override fun getUsername(): String = myUserName
//    override fun getPassword(): String = ""
//
//    override fun isAccountNonExpired() = true
//    override fun isAccountNonLocked() = true
//    override fun isCredentialsNonExpired() = true
//    override fun isEnabled() = true
//}