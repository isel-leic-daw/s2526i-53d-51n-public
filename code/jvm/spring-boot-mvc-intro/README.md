# Project containing Spring MVC usage examples

## Folder and files

* [SpringBootMvcIntroApplication.kt](src/main/kotlin/pt/isel/daw/springbootmvcintro/SpringBootMvcIntroApplication.kt) - 
application startup, including the registration of pipeline elements such as argument resolvers.
* [ExampleController](src/main/kotlin/pt/isel/daw/springbootmvcintro/ExampleController.kt) - Simple controller
illustrating handler mapping and constructor dependency injection.
* [controllers](src/main/kotlin/pt/isel/daw/springbootmvcintro/controllers) - folder with controllers exemplifying 
argument resolution and message conversion, namely from handler return values into response messages.
* [pipeline](src/main/kotlin/pt/isel/daw/springbootmvcintro/controllers) - folder with some example pipeline elements,
such as argument resolvers and message converters.
* [ExampleJacksonCustomizer](src/main/kotlin/pt/isel/daw/springbootmvcintro/ExampleJacksonCustomizer.kt) - customizes
how the Jackson-based message converter behaves.
