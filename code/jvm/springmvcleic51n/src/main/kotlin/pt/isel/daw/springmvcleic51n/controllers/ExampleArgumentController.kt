package pt.isel.daw.springmvcleic51n.controllers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/examples/args/")
class ExampleArgumentController {
    @GetMapping("/0/{id}")
    fun example0(
        @PathVariable id: String,
    ): String {
        return "Requested id is $id"
    }

    @GetMapping("/1")
    fun example1(
        @RequestParam id: String?,
    ): String {
        return "Request id is $id"
    }

    @GetMapping("/2")
    fun example2(
        @RequestParam params: MultiValueMap<String, String>,
    ): String {
        return "Request params are $params"
    }

    @GetMapping("/3")
    fun example3(
        request: HttpServletRequest,
        @RequestParam params: MultiValueMap<String, String>
    ): String {
        return "User agent is ${request.getHeader("user-agent")} and params are $params"
    }

    @PostMapping("/4")
    fun example4(
        @RequestBody input: StudentCreateInputModel
    ): String {
        return "Request for ${input.name} and ${input.number}"
    }

    @GetMapping("/5")
    fun example5(
        userAgent: UserAgent
    ): String {
        return "User agent is ${userAgent.value} "
    }
}