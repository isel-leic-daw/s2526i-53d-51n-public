import { createContext, useContext } from "react";

// 0 - define the state type
export type ExampleContextType = {
  counter: number;
  setCounter: (newValue: number) => void;
};

// 1 - create context
const defaultContext: ExampleContextType = {
  counter: 0,
  setCounter: () => {},
};
export const ExampleContext = createContext<ExampleContextType>(defaultContext);

export function useExampleContext() {
  return useContext(ExampleContext);
}
