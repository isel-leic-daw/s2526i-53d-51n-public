import { useState, type ReactNode } from "react";
import { ExampleContext } from "./exampleContext";

type ExampleContextProviderProps = {
    children: ReactNode,
}
export function ExampleContextProvider(props: ExampleContextProviderProps) {
    const [value, setValue] = useState(0)
    const contextValue = {
        value: value,
        setValue: setValue,
    }
    return (
        <ExampleContext value={contextValue}>
            {props.children}
        </ExampleContext>
    )
}