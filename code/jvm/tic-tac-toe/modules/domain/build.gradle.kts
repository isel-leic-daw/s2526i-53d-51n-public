plugins {
    kotlin("jvm")
}

group = "pt.isel.daw"
version = "1.0-SNAPSHOT"

dependencies {
    // To use Kotlin specific date and time functions
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")

    // To get password encode
    api("org.springframework.security:spring-security-core:6.5.5")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
