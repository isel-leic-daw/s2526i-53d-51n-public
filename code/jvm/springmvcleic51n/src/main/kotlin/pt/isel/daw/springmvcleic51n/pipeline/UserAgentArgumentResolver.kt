package pt.isel.daw.springmvcleic51n.pipeline

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import pt.isel.daw.springmvcleic51n.controllers.UserAgent

@Component
class UserAgentArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == UserAgent::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java)
            ?: return UserAgent("Not available")
        val value = request.getHeader("User-Agent")
            ?: return UserAgent("Not available")
        return UserAgent(value)
    }
}