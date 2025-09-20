package pt.isel.daw.springmvcleic51n

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * A controller is a container of handlers
 * A handler is an instance member function responsible for processing (i.e. handling)
 * requests to a given HTTP method and HTTP request URI
 */
@RestController
class ExampleController(
    private val greetingsService: GreetingsService,
) {

    @GetMapping("/")
    fun myFirstHandler() = greetingsService.getGreeting()

    @GetMapping("/some/path")
    fun anotherHandler() = "Hello Web from a different path"

}