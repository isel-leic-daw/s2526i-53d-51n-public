subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    repositories {
        mavenCentral()
    }
}
tasks.register<Exec>("composeDown") {
    commandLine("docker", "compose", "down")
}

extra["composeFileDir"] = layout.projectDirectory
println("composeFileDir - ${layout.projectDirectory}")