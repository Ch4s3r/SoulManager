package com.evilcorp.soulmanager.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig {
    @Configuration
    class SecurityConfigurerAdapter(val jwtAuthenticationFilter: JwtAuthenticationFilter) : WebSecurityConfigurerAdapter() {

        @Bean
        fun customAuthenticationManager(): AuthenticationManager {
            return authenticationManager()
        }


        override fun configure(http: HttpSecurity) {
            http
                    .requestMatchers().antMatchers("/graphiql", "/graphql").and().authorizeRequests().anyRequest().permitAll()
                    .anyRequest().authenticated()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().csrf().disable()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        }
    }
}