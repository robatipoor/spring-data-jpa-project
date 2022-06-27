package dev.robit

import dev.robit.config.DataJpaConfig
import dev.robit.entity.User
import dev.robit.repository.UserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.transaction.Transactional
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [DataJpaConfig::class])
class AppTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    @Transactional
    fun testUserRepository() {
        val user: User = User()
        user.firstName = "firstname"
        user.lastName = "lastname"
        user.username = "username"
        userRepository.save(user)
        println("${user.id}")
        val list = userRepository.findAll()
        assertAll({
            assertEquals(1, list.count())
        })
    }
}
