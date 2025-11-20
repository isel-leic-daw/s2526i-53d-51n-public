import { useState, type ReactNode } from "react"
import { ExampleContext, type ExampleContextType } from "./exampleContext"

type ExampleContextProviderProps = {
    children: ReactNode
}
export function ExampleContextProvider(props: ExampleContextProviderProps) {
    const [observedCounter, setCounter] = useState(0)
    const contextValue: ExampleContextType = {
        counter: observedCounter,
        setCounter: setCounter,
    }
    return (
        <ExampleContext value = {contextValue}>
            {props.children}
        </ExampleContext>
    )
}