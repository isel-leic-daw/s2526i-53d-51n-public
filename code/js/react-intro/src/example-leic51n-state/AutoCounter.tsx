import { useEffect, useState } from "react";

type AutoCounterProps = {
  title: string;
};
export function AutoCounter(props: AutoCounterProps) {
  const [observedCounter, setCounter] = useState(0);
  useEffect(() => {
    console.log("Effect called");
    const iid = setInterval(() => {
      console.log("setInterval callback called");
      setCounter((prevState) => prevState + 1);
    }, 1000);
    return () => {
      clearInterval(iid);
    };
  }, []);
  return (
    <div>
      <h3>{props.title}</h3>
      {observedCounter}
    </div>
  );
}
