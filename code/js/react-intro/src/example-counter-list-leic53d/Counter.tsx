import { useState, useContext } from "react";
import { ExampleContext, useExampleContext } from "./exampleContext";

/*
 * A counter component presenting a label, the current counter value, and a button to increment.
 */

type CounterProps = {
  // The label to show
  label: string;
  // An optional callback function to inform that the counter has a new value
  onIncrement?: (newValue: number) => void;
};

export function Counter(props: CounterProps) {
  const [observedCounter, setCounter] = useState(0);
  const context = useExampleContext();
  return (
    <div>
      <h3>{props.label}</h3>
      <p>{observedCounter}</p>
      <button
        onClick={() => {
          setCounter(observedCounter + 1);
          props.onIncrement?.(observedCounter + 1);
        }}
      >
        Increment
      </button>
      <p>Context counter - {context.counter}</p>
      <button onClick={() => context.setCounter(context.counter + 1)}>Increment context counter</button>
    </div>
  );
}
