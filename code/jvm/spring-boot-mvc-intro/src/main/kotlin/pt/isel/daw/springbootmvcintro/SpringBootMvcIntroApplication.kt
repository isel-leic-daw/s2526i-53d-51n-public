package pt.isel.daw.springbootmvcintro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class SpringBootMvcIntroApplication(
    private val argumentResolvers: List<HandlerMethodArgumentResolver>,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.addAll(argumentResolvers)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringBootMvcIntroApplication>(*args)
}
