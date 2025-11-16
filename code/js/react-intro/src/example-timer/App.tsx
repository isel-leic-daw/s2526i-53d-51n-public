import { useState, useEffect } from "react";

function Timer() {
  const [observedCounter, setCounter] = useState(0);
  useEffect(() => {
    console.log("effect");
    const iid = setInterval(() => {
        console.log("setInterval callback called")
        setCounter(currCounter => currCounter + 1)
    }, 1000);
    return () => {
      console.log("effect cleanup");
      clearInterval(iid);
    };
  }, []);

  return (
    <div>
      <h3>Timer</h3>
      <p>{observedCounter}</p>
    </div>
  );
}
export function App() {
  const [observedShow, setShow] = useState(false);
  const buttonText = observedShow ? "hide" : "show";
  return (
    <div>
      <h1>Timer</h1>
      <button onClick={() => setShow(!observedShow)}>{buttonText}</button>
      {observedShow && <Timer />}
      {observedShow && <Timer />}
    </div>
  );
}
