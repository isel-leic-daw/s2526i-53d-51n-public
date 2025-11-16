import { getLanguages } from "./messages"
import { useMessages } from "./MessagesContext"


export function LanguageSelector() {
    const [, language, setLanguage] = useMessages()
    return (
        <select onChange={(ev) => setLanguage(ev.target.value)} value={language}>
            {getLanguages().map((it,ix) => <option key={ix} value={it} >{it}</option>)}
        </select>
    )
}