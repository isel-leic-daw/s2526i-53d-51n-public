package pt.isel.daw.springmvcleic53d.controllers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pt.isel.daw.springmvcleic53d.StudentCreateInputModel

@RestController
@RequestMapping("/examples/args/")
class ArgumentResolutionExamplesController {

    @GetMapping("0/{id}/{id2}")
    fun example0(
        @PathVariable id: String,
        @PathVariable id2: String,
    ): String {
        return "Value of path vars is $id and $id2"
    }

    @GetMapping("1")
    fun example1(
        @RequestParam a: String?,
        @RequestParam b: Int,
    ): String {
        return "Value of params is $a and $b"
    }

    @GetMapping("2")
    fun example2(
        @RequestParam params: MultiValueMap<String, String>?,
    ): String {
        return "Value of params is $params"
    }

    @GetMapping("3")
    fun example3(
        request: HttpServletRequest,
    ): String {
        return "User-Agent is ${request.getHeader("user-agent")}"
    }

    @PostMapping("4")
    fun example4(
        request: HttpServletRequest,
        @RequestBody studentCreateInputModel: StudentCreateInputModel,
    ): String {
        return "user agent is ${request.getHeader("user-agent")}, name is ${studentCreateInputModel.name} and number is ${studentCreateInputModel.number}"
    }

    @GetMapping("5")
    fun example5(
        userAgent: UserAgent,
    ): String {
        return "user agent is ${userAgent.value}"
    }

}