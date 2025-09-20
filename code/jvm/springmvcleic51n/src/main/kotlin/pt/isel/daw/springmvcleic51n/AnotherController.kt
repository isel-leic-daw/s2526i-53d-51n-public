package pt.isel.daw.springmvcleic51n

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AnotherController {

    @GetMapping("/another")
    fun someHandler() = "Hello World"
}