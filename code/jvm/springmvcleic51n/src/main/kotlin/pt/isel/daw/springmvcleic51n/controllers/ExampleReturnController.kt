package pt.isel.daw.springmvcleic51n.controllers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/examples/ret")
class ExampleReturnController {

    @GetMapping("/0")
    fun example0(): String {
        return "Hello Web"
    }

    @GetMapping("/1")
    fun example1() =
        StudentOutputModel(
            "Alice",
            number = 123,
        )

    @GetMapping("/2")
    fun example2() = ResponseEntity
        .ok()
        .header("Example-Header", "Example-Value")
        .body(
            StudentOutputModel(
                "Alice",
                number = 123,
            )
        )

    @PostMapping("/3")
    fun example3(
        request: HttpServletRequest,
        @RequestBody body: StudentCreateInputModel
    ): String {
        val userAgent = request.getHeader("User-Agent")
        return "$userAgent, ${body.name}"
    }

    @PostMapping("/4")
    fun example4(
        requestEntity: RequestEntity<StudentCreateInputModel>
    ): String {
        val userAgent = requestEntity.headers.get("User-Agent")
        val body = requestEntity.body
        return "$userAgent, ${body?.name}"
    }
}