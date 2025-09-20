package pt.isel.daw.springmvcleic53d

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pt.isel.daw.springmvcleic53d.controllers.UserAgentArgumentResolver
import pt.isel.daw.springmvcleic53d.pipeline.ExampleHandlerInterceptor

@SpringBootApplication
class Springmvcleic53dApplication(
    private val userAgentArgumentResolver: UserAgentArgumentResolver,
    private val exampleHandlerInterceptor: ExampleHandlerInterceptor,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver?>) {
        resolvers.add(userAgentArgumentResolver)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(exampleHandlerInterceptor)
    }
}

fun main(args: Array<String>) {
    runApplication<Springmvcleic53dApplication>(*args)
}
