package pt.isel.daw.sketches.leic51n.lecture2

import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.util.Base64
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.set

/**
 * Present the Inversion of Control (IoC) technique
 * and related concepts and tools:
 * - Dependency Injection (DI)
 * - Dependency Injection Container (DI Container)
 */

data class UserId(
    val value: String,
)

data class ClearTextPassword(
    val value: String,
)

data class TransformedPassword(
    val value: String,
)

// Annotation needed for
// component scanning to consider this class
@Component
class PasswordService(
    private val passwordStorage: PasswordStorage,
    private val passwordTransformer: PasswordTransformer,
) {
    fun set(userId: UserId, password: ClearTextPassword) {
        passwordStorage.set(
            userId,
            passwordTransformer.transform(password),
        )
    }

    fun check(userId: UserId, password: ClearTextPassword): Boolean {
        val transformedPassword = passwordStorage.get(userId)
            ?: return false
        return passwordTransformer.check(
            password,
            transformedPassword,
        )
    }
}

interface PasswordTransformer {
    fun transform(password: ClearTextPassword): TransformedPassword

    fun check(
        password: ClearTextPassword,
        transformedPassword: TransformedPassword,
    ): Boolean
}

interface PasswordStorage {
    fun set(
        userId: UserId,
        transformedPassword: TransformedPassword,
    )

    fun get(userId: UserId): TransformedPassword?
}

@Component
class InMemoryPasswordStorage : PasswordStorage {
    private val map = ConcurrentHashMap<String, String>()

    override fun set(
        userId: UserId,
        transformedPassword: TransformedPassword,
    ) {
        map[userId.value] = transformedPassword.value
    }

    override fun get(userId: UserId): TransformedPassword? {
        return map[userId.value]
            ?.let { TransformedPassword(it) }
    }
}

fun interface HashFunctionFactory {
    fun create(): MessageDigest
}

@Component
class UnsafeHashBasedPasswordTransformer(
    private val hashFunctionFactory: HashFunctionFactory,
) : PasswordTransformer {
    override fun transform(password: ClearTextPassword): TransformedPassword {
        val hashFunction = hashFunctionFactory.create()
        val passwordBytes = password.value.toByteArray(Charsets.UTF_8)
        val transformedBytes = hashFunction.digest(passwordBytes)
        val transformedString = Base64.getEncoder().encodeToString(transformedBytes)
        return TransformedPassword(transformedString)
    }

    override fun check(
        password: ClearTextPassword,
        transformedPassword: TransformedPassword,
    ): Boolean {
        val hashFunction = hashFunctionFactory.create()
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