package pt.isel.daw.springmvcleic53d.pipeline

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class ExampleHandlerInterceptor : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        if(handler !is HandlerMethod) {
            return false
        }
        logger.info("Before calling handler {}", handler.shortLogMessage)
        response.setHeader("Another-Header", "Another-Value")
        response.status = 401
        return false
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ExampleHandlerInterceptor::class.java)
    }

}