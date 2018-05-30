package com.evilcorp.soulmanager

import com.evilcorp.soulmanager.entity.Role
import com.evilcorp.soulmanager.entity.User
import com.evilcorp.soulmanager.entity.valueablegood.Bag
import com.evilcorp.soulmanager.entity.valueablegood.BagSize
import com.evilcorp.soulmanager.entity.valueablegood.Pocket
import com.evilcorp.soulmanager.entity.valueablegood.Wallet
import com.evilcorp.soulmanager.repository.RoleRepository
import com.evilcorp.soulmanager.repository.UserRepository
import com.evilcorp.soulmanager.repository.valueablegood.ValueAbleGoodRepository
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer(val roleRepository: RoleRepository,
                          val userRepository: UserRepository,
                          val valueAbleGoodRepository: ValueAbleGoodRepository) {
    @PostConstruct
    fun init() {
        if (roleRepository.count() == 0L) {
            val roleAdmin = Role(name = "ROLE_ADMIN")
            val roleUser = Role(name = "ROLE_USER")
            roleRepository.saveAll(listOf(roleAdmin, roleUser))

            val goblin1 = User(username = "kcheap",
                    firstname = "Kexenkle",
                    lastname = "Cheapchart",
                    email = "goblin1@evilcorp.com",
                    password = BCrypt.hashpw("pw", BCrypt.gensalt()),
                    roles = listOf(roleUser))
            userRepository.save(goblin1)
            val wallet1 = Wallet(owner = goblin1, color = "blue", value = 10, weight = 15)
            valueAbleGoodRepository.save(wallet1)
            goblin1.wallets.add(wallet1)

            val goblin2 = User(username = "basher",
                    firstname = "Renkle",
                    lastname = "Foambasher",
                    email = "goblin2@evilcorp.com",
                    password = BCrypt.hashpw("pw", BCrypt.gensalt()),
                    roles = listOf(roleUser))
            userRepository.save(goblin2)
            val bag1 = Bag(owner = goblin2, color = "blue", value = 33, size = BagSize.MEDIUM)
            valueAbleGoodRepository.save(bag1)
            goblin2.bags.add(bag1)


            val bankelini = User(username = "bankelini",
                    firstname = "Magvys",
                    lastname = "Moneyhold",
                    email = "evilbitch@evilcorp.com",
                    password = BCrypt.hashpw("pw", BCrypt.gensalt()),
                    roles = listOf(roleUser, roleAdmin))
            userRepository.save(bankelini)
            val pocket = Pocket(owner = bankelini, value = 200)
            val bag2 = Bag(owner = bankelini, color = "black", value = 2100, size = BagSize.LARGE)
            valueAbleGoodRepository.saveAll(listOf(pocket, bag2))

        }
    }
}