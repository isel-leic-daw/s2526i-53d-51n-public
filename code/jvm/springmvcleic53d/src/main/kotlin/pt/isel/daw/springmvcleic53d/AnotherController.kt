package pt.isel.daw.springmvcleic53d

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AnotherController {

    @GetMapping("/another/one")
    fun anotherHandler() = "Another one"

}