package com.evilcorp.soulmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SoulManagerApplication

fun main(args: Array<String>) {
    runApplication<SoulManagerApplication>(*args)
}
