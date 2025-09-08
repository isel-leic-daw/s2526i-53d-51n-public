package pt.isel.daw.e0.intro.step3

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import pt.isel.daw.e0.intro.HashFunctionSupplier
import pt.isel.daw.e0.intro.PlainTextPassword
import pt.isel.daw.e0.intro.UserId
import pt.isel.daw.e0.intro.step2.InMemoryPasswordStorage
import pt.isel.daw.e0.intro.step2.PasswordService
import pt.isel.daw.e0.intro.step2.UnsecureHashBasedPasswordTransformer
import java.security.MessageDigest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordServiceTests {
    @Test
    fun `basic test`() {
        // given: a password service
        val passwordService = compose()

        // and: a set of test data
        val alice = UserId("Alice")
        val bob = UserId("Bob")
        val carol = UserId("Carol")
        val alicePassword = PlainTextPassword("Alice's password")
        val bobPassword = PlainTextPassword("Bob's password")

        // when: setting Alice and Bob passwords
        passwordService.set(alice, alicePassword)
        passwordService.set(bob, bobPassword)

        // then: password verification works as expected
        assertTrue(passwordService.check(alice, alicePassword))
        assertTrue(passwordService.check(bob, bobPassword))

        assertFalse(passwordService.check(alice, bobPassword))
        assertFalse(passwordService.check(bob, alicePassword))

        assertFalse(passwordService.check(carol, alicePassword))
        assertFalse(passwordService.check(carol, bobPassword))
    }

    companion object {
        class Config {
            @Bean
            fun hashFunctionSupplier() = HashFunctionSupplier {
                MessageDigest.getInstance("SHA-256")
            }
        }

        fun compose(): PasswordService {
            val context: AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(
                PasswordService::class.java,
                UnsecureHashBasedPasswordTransformer::class.java,
                InMemoryPasswordStorage::class.java,
                Config::class.java,
            )

            return context.getBean(PasswordService::class.java)
        }
    }
}