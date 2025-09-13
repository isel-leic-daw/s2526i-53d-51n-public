package pt.isel.daw.sketches.leic51n.lecture2

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.security.MessageDigest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordServiceTests {
    @Test
    fun `basic tests`() {
        // given:
        // - composition

        /*val passwordService = PasswordService(
            UnsafeHashBasedPasswordTransformer(
                object : HashFunctionFactory {
                    override fun create(): MessageDigest {
                        return MessageDigest.getInstance("SHA-512")
                    }
                }
            ),
            InMemoryPasswordStorage(),
        )*/

        val passwordService = compose()
        val alice = UserId("Alice")
        val bob = UserId("Bob")
        val alicePassword = ClearTextPassword("Alice's password")
        val notAlicePassword = ClearTextPassword("Not Alice's password")
        passwordService.set(alice, alicePassword)

        // when:
        val correctPasswordCheck = passwordService.check(alice, alicePassword)
        val incorrectPasswordCheck = passwordService.check(alice, notAlicePassword)
        val bobCheck = passwordService.check(bob, alicePassword)

        // then:
        assertTrue(correctPasswordCheck)
        assertFalse(incorrectPasswordCheck)
        assertFalse(bobCheck)
    }

    companion object {
        @Component
        class BeanProvider {
            @Bean
            fun hashFunctionFactory(): HashFunctionFactory {
                return object : HashFunctionFactory {
                    override fun create(): MessageDigest {
                        return MessageDigest.getInstance("SHA-512")
                    }
                }
            }
        }

        fun compose(): PasswordService {
            val context = AnnotationConfigApplicationContext()
            // Component scanning - opt-in
            context.scan("pt.isel.daw.sketches.leic51n.lecture2")
            context.refresh()
            return context.getBean(
                PasswordService::class.java,
            )
        }
    }
}