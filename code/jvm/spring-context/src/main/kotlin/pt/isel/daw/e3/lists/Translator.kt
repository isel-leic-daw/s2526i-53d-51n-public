package pt.isel.daw.e3.lists

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.getBean
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component

private val log = LoggerFactory.getLogger("main")

interface LanguageTranslator {
    fun translate(englishWord: String): String?

    val targetLanguage: String
}

@Component
class Translator(
    languageTranslators: List<LanguageTranslator>,
) {
    private val map: Map<String, LanguageTranslator> =
        languageTranslators.associateBy {
            it.targetLanguage
        }

    fun translate(
        englishWord: String,
        targetLanguage: String,
    ) = map[targetLanguage]?.translate(englishWord)
        ?: "Sorry, no available translation for '$englishWord' in '$targetLanguage'"
}

fun main() {
    log.info("## Example: illustrates the use of list dependencies")

    log.info("Create context.")
    val context = AnnotationConfigApplicationContext()
    log.info("Component scan and refresh")
    context.scan("pt.isel.daw.e3")
    context.refresh()

    log.info("Obtain `Translator instance`")
    val translator = context.getBean<Translator>()

    log.info(translator.translate("Hello", "pt"))
    log.info(translator.translate("Hello", "es"))
    log.info(translator.translate("Hello", "sv"))
    log.info(translator.translate("Hello", "fr"))
    log.info(translator.translate("Hello", "de"))
    log.info(translator.translate("hello", "zh-CN"))
    log.info(translator.translate("hello", "it"))
    /*
     * Conclusions:
     * - Adding a new translator only requires adding a class implementing the `LanguageTranslator` interface
     *   and annotated with `@Component`.
     * - The context will automatically create an instance of Translator using all discovered `LanguageTranslator`
     *   implementations.
     */
}