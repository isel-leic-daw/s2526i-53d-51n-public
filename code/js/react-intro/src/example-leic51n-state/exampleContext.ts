import { createContext, useContext } from "react"

// 0 - start by defining the context type
type ContextType = {
    value: number,
    setValue: (newValue: number) => void,
}

// 1 - then create a context object
const defaultContext = {
    value: 0,
    setValue: () => {},
}
export const ExampleContext = createContext<ContextType>(defaultContext)

export function useSharedCounter() {
    return useContext(ExampleContext)
}