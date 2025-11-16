import { useState } from "react";

/*
 * An input box showing:
 * - A label with some informative text.
 * - A text input to collect some user's input.
 * - A button to submit the input text
 */ 
type InputBoxProps = {
  // the label
  label: string;

  // validity restriction on the input length
  minLength: number;
  maxLength: number;

  // callback to inform that the input content was submitted
  submitFunction: (value: string) => void;
};
export function InputBox(props: InputBoxProps) {
  const [observedValue, setValue] = useState("");
  const error =
    observedValue.length > 0 && observedValue.length < props.minLength
      ? `length must be greater or equal than ${props.minLength}`
      : observedValue.length > props.maxLength
      ? `length cannot be greater than ${props.maxLength}`
      : null;
  const submitEnabled = observedValue.length > 0 && !error;

  return (
    <div>
      <p>{props.label}</p>
      <input
        type="text"
        value={observedValue}
        onChange={(ev) => setValue(ev.target.value)}
      />
      {error}
      <div>
        <button
          disabled={!submitEnabled}
          onClick={() => props.submitFunction(observedValue)}
        >
          Submit
        </button>
      </div>
    </div>
  );
}

// https://react.dev/reference/react-dom/components/input#controlling-an-input-with-a-state-variable
