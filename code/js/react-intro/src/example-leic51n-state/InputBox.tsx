import { useState } from "react";

type InputBoxProps = {
  title: string;
  minLength: number;
  maxLength: number;
  onSubmit: (value: string) => void;
};
export function InputBox(props: InputBoxProps) {
  const [text, setText] = useState("");
  const isEnabled =
    text.length >= props.minLength && text.length <= props.maxLength;
  return (
    <div>
      <h3>{props.title}</h3>
      <input
        type="text"
        value={text}
        onChange={(ev) => setText(ev.target.value)}
      ></input>
      <button 
      disabled={!isEnabled}
      onClick={() => {
        props.onSubmit(text)
        setText("")
      }}
      >OK</button>
    </div>
  );
}
