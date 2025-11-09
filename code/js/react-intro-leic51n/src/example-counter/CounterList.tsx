import React from "react";
import { Counter } from "./Counter";

export function CounterList() {
  const [observedSize, setSize] = React.useState(0);
  return (
    <div>
      <h2>Counter List</h2>
      <button onClick={() => setSize(observedSize + 1)}>add</button>
      <button
        onClick={() => setSize(observedSize - 1)}
        disabled={observedSize <= 0}
      >
        remove
      </button>

      {[...Array(observedSize).keys()].map((it) => (
        <Counter key={observedSize - it} label={`counter ${it}`} />
      ))}
      
    </div>
  );
}
