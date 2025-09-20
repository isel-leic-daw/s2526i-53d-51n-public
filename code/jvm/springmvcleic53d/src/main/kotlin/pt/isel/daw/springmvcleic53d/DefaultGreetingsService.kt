package pt.isel.daw.springmvcleic53d

import org.springframework.stereotype.Component

@Component
class DefaultGreetingsService : GreetingsService {
    override fun getGreeting(): String {
        return "Hello from the greetings service"
    }
}