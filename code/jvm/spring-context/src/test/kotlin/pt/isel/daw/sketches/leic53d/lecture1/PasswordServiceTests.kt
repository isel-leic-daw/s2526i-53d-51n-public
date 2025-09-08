package pt.isel.daw.sketches.leic53d.lecture1

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import java.security.MessageDigest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordServiceTests {
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

        /*val passwordService = PasswordService(
            InMemoryPasswordStorage(),
            UnsafeHashBasedPasswordTransformer(
                {
                    MessageDigest.getInstance("SHA-512")
                },
            ),
        )*/

        // AnnotationConfigApplicationContext is provided by the
        // Spring Context library
        val container = AnnotationConfigApplicationContext()
        container.register(
            InMemoryPasswordStorage::class.java,
            PasswordService::class.java,
            UnsafeHashBasedPasswordTransformer::class.java,
            BeanProvider::class.java,
        )
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