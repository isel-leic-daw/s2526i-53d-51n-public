package pt.isel.daw.springmvcleic51n

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pt.isel.daw.springmvcleic51n.pipeline.UserAgentArgumentResolver

@SpringBootApplication
class Springmvcleic51nApplication(
    private val additionalResolvers: List<HandlerMethodArgumentResolver>,
    private val additionalInterceptors: List<HandlerInterceptor>,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver?>) {
        resolvers.addAll(additionalResolvers)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        additionalInterceptors.forEach {
            registry.addInterceptor(it)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Springmvcleic51nApplication>(*args)
}
