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
//TODO enable later in production
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
                    .authorizeRequests().antMatchers("/graphiql", "/graphql").permitAll()
                    //TODO enable later in production
                    .anyRequest().denyAll()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and().exceptionHandling().authenticationEntryPoint({ request, response, authException ->
//                        response.addHeader("WWW-Autenticate", "Bearer")
//                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.message)
//                    })
//                    .and().exceptionHandling().accessDeniedHandler(AccessDeniedHandler { request, response, accessDeniedException ->
//                        response.sendError(HttpServletResponse.SC_FORBIDDEN)
//                    })
                    .and().csrf().disable()
                    .headers().frameOptions().disable() //for h2
                    .and().addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        }
    }
}