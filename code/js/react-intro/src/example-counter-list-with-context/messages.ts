export type MessageMap = {
  _lang: keyof(LanguagesMap)
  submit: string;
  increment: string;
};
export type MessageKey = keyof(MessageMap)
type LanguagesMap = {
  PT: MessageMap;
  EN: MessageMap;
};
export type Lang = keyof(LanguagesMap)
const map: LanguagesMap = {
  PT: {
    _lang: "PT",
    submit: "Submeter",
    increment: "Incrementar",
  },
  EN: {
    _lang: "EN",
    submit: "Submit",
    increment: "Increment",
  },
};

let currentMap: MessageMap = map["EN"];

function isLanguage(lang: string): lang is Lang {
    return lang in map
}

export function getLanguages() {
  return Object.keys(map);
}

export function getLanguage(): Lang {
  return currentMap._lang;
}

export function setLanguage(lang: string): MessageMap {
  if (isLanguage(lang)) {
    console.log(`Setting language to ${lang}`)
    currentMap = map[lang];
  }
  return currentMap
}
export function getCurrentMap(): MessageMap {
    return currentMap
}
export function s(key: MessageKey): string {
  return currentMap[key] || "MISSING KEY";
}
