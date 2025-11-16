//import { AutoCounter } from "./AutoCounter";
import { CounterList } from "./CounterList";
//import { CounterList } from "./CounterList";
import { ExampleContextProvider } from "./ExampleContextProvider";

export function App() {
  return (
    <ExampleContextProvider>
      <CounterList />
    </ExampleContextProvider>
  );
}
