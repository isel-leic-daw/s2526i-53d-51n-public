package pt.isel.daw.springmvcleic53d.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/examples/ret/")
class HandlerReturnExamplesController {

    @GetMapping("/0")
    fun example0() = "Hello Web `açã"

    @GetMapping("/1")
    fun example1() = StudentOutputModel(
        "Alice",
        123,
    )

    @GetMapping("/2")
    fun example2() = ResponseEntity
        .ok()
        .header("Example-Header", "Example-Value")
        .body(
            StudentOutputModel(
                "Alice",
                12345
            )
        )

}