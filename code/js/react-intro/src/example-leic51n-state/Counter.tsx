import { useState } from "react";
import { useSharedCounter } from "./exampleContext";

type CounterProps = {
  title: string;
  onChange?: (newValue: number) => void;
};
export function Counter(props: CounterProps) {
  const [observedCounter, setCounter] = useState(0);
  const sharedCounter = useSharedCounter();
  return (
    <div>
      <h3>{props.title}</h3>
      <h4>Context is {sharedCounter.value}</h4>
      <p>{observedCounter}</p>
      <button
        onClick={() => {
          setCounter(observedCounter + 1);
          props.onChange?.(observedCounter + 1)
          sharedCounter.setValue(observedCounter + 1)
        }}
      >
        Add
      </button>
    </div>
  );
}
