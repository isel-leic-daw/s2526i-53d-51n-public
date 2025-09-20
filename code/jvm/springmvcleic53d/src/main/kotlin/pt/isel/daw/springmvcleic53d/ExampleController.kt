package pt.isel.daw.springmvcleic53d

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController(
    private val greetingsService: GreetingsService,
) {

    @GetMapping("/")
    fun exampleHandler() = greetingsService.getGreeting()

    @GetMapping("/another")
    fun anotherHandler() = "Hello Web from another"
}