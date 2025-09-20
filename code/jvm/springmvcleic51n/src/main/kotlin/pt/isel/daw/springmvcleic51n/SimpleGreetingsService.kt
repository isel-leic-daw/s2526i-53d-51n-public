package pt.isel.daw.springmvcleic51n

import org.springframework.stereotype.Component

@Component
class SimpleGreetingsService : GreetingsService {
    override fun getGreeting(): String {
        return "Hello Web (from greetings service)"
    }
}