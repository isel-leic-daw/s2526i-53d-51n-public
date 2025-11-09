import React from "react";

type CounterProps = {
  label: string;
};
export function Counter({ label }: CounterProps) {
  const [observedCounter, setCounter] = React.useState(0);
  return (
    <div>
      <h3>{label}</h3>
      <p>{observedCounter}</p>
      <button onClick={() => setCounter(observedCounter + 1)}>increment</button>
    </div>
  );
}
