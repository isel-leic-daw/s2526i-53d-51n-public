package pt.isel.daw.sketches.leic53d.lecture2

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.security.MessageDigest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordServiceTests {
    @Component
    class BeanProvider {
        @Bean
        fun hashFunctionSupplierProvider(): HashFunctionSupplier {
            return object : HashFunctionSupplier {
                override fun get(): MessageDigest {
                    return MessageDigest.getInstance("SHA-512")
                }
            }
        }
    }

    @Test
    fun `basic tests`() {
        // given:
        // composition
        // AnnotationConfigApplicationContext is provided by the
        // Spring Context library
        val container = AnnotationConfigApplicationContext()
        container.scan("pt.isel.daw.sketches.leic53d.lecture2")
        container.refresh()
        val passwordService = container
            .getBean(PasswordService::class.java)

        val aliceId = UserId("alice")
        val bobId = UserId("bob")
        val alicePassword = ClearTextPassword("Alice's password")

        // when:
        passwordService.set(aliceId, alicePassword)

        // then:
        assertTrue(
            passwordService.check(
                aliceId,
                alicePassword,
            ),
        )

        // and:
        assertFalse(
            passwordService.check(
                aliceId,
                ClearTextPassword("not Alice's password"),
            ),
        )

        // and:
        assertFalse(
            passwordService.check(
                bobId,
                alicePassword,
            ),
        )
    }
}