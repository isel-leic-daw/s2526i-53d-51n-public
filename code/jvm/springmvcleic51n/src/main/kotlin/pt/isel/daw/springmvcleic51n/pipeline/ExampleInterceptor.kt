package pt.isel.daw.springmvcleic51n.pipeline

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class ExampleInterceptor : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        logger.info("Handler {} is going to be called",
            handler.method.name)
        return true
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ExampleInterceptor::class.java)
    }

}