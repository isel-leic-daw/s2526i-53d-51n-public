import { useState } from "react";
import { Counter } from "./Counter";
import { InputBox } from "./InputBox";

export function App() {
  const [counter1, setCounter1] = useState(0);
  const [counter2, setCounter2] = useState(0);
  return (
    <div>
      <InputBox
        title="Insert something"
        minLength={3}
        maxLength={10}
        onSubmit={(value) => alert(value)}
      />
      <p>Sum is {counter1 + counter2}</p>
      <Counter
        title="My first counter"
        onChange={(newValue) => setCounter1(newValue)}
      />
      <Counter
        title="My second counter"
        onChange={(newValue) => setCounter2(newValue)}
      />
    </div>
  );
}
