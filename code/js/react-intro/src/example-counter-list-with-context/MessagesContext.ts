import { createContext, useContext } from "react";
import {
  getCurrentMap,
  type Lang,
  type MessageMap,
  type MessageKey,
} from "./messages";

type ContextType = {
  currentMap: MessageMap;
  setLanguage: (lang: string) => void
};

export const MessagesContext = createContext<ContextType>({
  currentMap: getCurrentMap(),
  setLanguage: () => {},
});

export function useMessages(): [
  (key: MessageKey) => string,
  Lang,
  (lang: string) => void
] {
  const context = useContext(MessagesContext);
  function s(key: MessageKey): string {
    return context.currentMap[key] || "INVALID KEY";
  }
  return [s, context.currentMap._lang, context.setLanguage];
}
