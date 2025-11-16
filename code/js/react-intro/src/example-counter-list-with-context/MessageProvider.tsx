import { type ReactNode, useState } from "react";
import { getCurrentMap, setLanguage } from "./messages";
import { MessagesContext } from "./MessagesContext";

export function MessageProvider({ children }: { children: ReactNode }) {
  const [languageMap, setLanguageMap] = useState(getCurrentMap());
  function set(lang: string) {
    const newMap = setLanguage(lang)
    setLanguageMap(newMap);
  }
  return (
    <MessagesContext value={{ currentMap: languageMap, setLanguage: set }}>
      {children}
    </MessagesContext>
  );
}
