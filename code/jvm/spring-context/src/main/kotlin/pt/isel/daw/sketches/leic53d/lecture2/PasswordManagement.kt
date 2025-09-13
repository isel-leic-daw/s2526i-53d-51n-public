
package pt.isel.daw.sketches.leic53d.lecture2

import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.util.Base64
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Supplier

/*
 Introduction to Inversion of Control (IoC) design technique
 Domain: password management
 Decomposition:
    - Storage
    - Password transformation
 Concepts:
    - Decomposition
    - Dependency
    - Dependent
    - Composition
    - Dependency Injection (DI)
        - Providing (i.e. injecting) a dependency to a dependent
        - Constructor-based dependency injection
    - IoC/DI container
        - Goal: simplify the composition
        - Usage:
            - Provide "recipes" to the container
            - Request a component from the container
    - Spring Context
        - A container is called "context"
        - In Spring Context,
            a "component" is sometimes called a "bean"
        - Example of recipe
            - To obtain an PasswordStorage,
                create an instance of InMemoryPasswordStorage using the primary constructor
            - To obtain an PasswordTransformer,
                create an instance of UnsafeHashBasedPasswordTransformer using the primary constructor
            - To obtain a PasswordService,
                create an instance of that class using the primary constructor
            - To obtain a HashFunctionSupplier create an anonymous class with the function { MessageDigest.createInstance("SHA-512") }
 */

@JvmInline
value class UserId(
    val id: String,
)

@JvmInline
value class ClearTextPassword(
    val value: String,
)

@JvmInline
value class TransformedPassword(
    val value: String,
)

interface PasswordStorage {
    fun set(user: UserId, transformedPassword: TransformedPassword)

    fun get(userId: UserId): TransformedPassword?
}

interface PasswordTransformer {
    fun transform(password: ClearTextPassword): TransformedPassword

    fun check(
        password: ClearTextPassword,
        transformedPassword: TransformedPassword,
    ): Boolean
}

@Component
class InMemoryPasswordStorage : PasswordStorage {
    private val map = ConcurrentHashMap<String, String>()

    override fun set(
        user: UserId,
        transformedPassword: TransformedPassword,
    ) {
        map[user.id] = transformedPassword.value
    }

    override fun get(userId: UserId): TransformedPassword? {
        return map[userId.id]?.let { TransformedPassword(it) }
    }
}

fun interface HashFunctionSupplier : Supplier<MessageDigest>

@Component
class UnsafeHashBasedPasswordTransformer(
    private val hashFunctionSupplier: HashFunctionSupplier,
) : PasswordTransformer {
    override fun transform(password: ClearTextPassword): TransformedPassword {
        val hashFunction = hashFunctionSupplier.get()
        val passwordBytes = password.value.toByteArray(Charsets.UTF_8)
        val transformedBytes = hashFunction.digest(passwordBytes)
        val transformedString = Base64.getEncoder().encodeToString(transformedBytes)
        return TransformedPassword(transformedString)
    }

    override fun check(
        password: ClearTextPassword,
        transformedPassword: TransformedPassword,
    ): Boolean {
        val hashFunction = hashFunctionSupplier.get()
        val passwordBytes = password.value.toByteArray(Charsets.UTF_8)
        val transformedBytes = hashFunction.digest(passwordBytes)
        val originalTransformedBytes = try {
            Base64.getDecoder()
                .decode(transformedPassword.value)
        } catch (_: IllegalArgumentException) {
            return false
        }
        return transformedBytes
            .contentEquals(originalTransformedBytes)
    }
}

@Component
class PasswordService(
    // PasswordService *depends* on PasswordStorage
    private val passwordStorage: PasswordStorage,
    // PasswordService *depends* on PasswordTransformer
    private val passwordTransformer: PasswordTransformer,
) {
    fun set(userId: UserId, password: ClearTextPassword) {
        val transformedPassword = passwordTransformer
            .transform(password)
        passwordStorage.set(userId, transformedPassword)
    }

    fun check(userId: UserId, password: ClearTextPassword): Boolean {
        val transformedStoredPassword = passwordStorage.get(userId)
            ?: return false
        return passwordTransformer.check(
            password,
            transformedStoredPassword,
        )
    }
}