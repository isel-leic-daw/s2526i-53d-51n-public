import { useContext, useReducer } from "react";
import { InputBox } from "./InputBox";
import { Counter } from "./Counter";
import { ExampleContext } from "./exampleContext";
import { AutoCounter } from "./AutoCounter";

type CounterState = {
  id: string;
  title: string;
  value: number;
};
type State = {
  sum: number;
  counters: Array<CounterState>;
};

type Action =
  | { type: "add-counter"; title: string }
  | { type: "delete-counter"; index: number }
  | { type: "value-changed"; index: number; newValue: number };

function reducer(state: State, action: Action): State {
  switch (action.type) {
    case "add-counter":
      return {
        ...state,
        counters: [
          ...state.counters,
          { value: 0, title: action.title, id: Math.random().toString() },
        ],
      };
    case "delete-counter": {
      const counter = state.counters[action.index];
      return {
        sum: state.sum - counter.value,
        counters: state.counters.filter((_, ix) => ix != action.index),
      };
    }
    case "value-changed": {
      const counter = state.counters[action.index];
      return {
        sum: state.sum - counter.value + action.newValue,
        counters: state.counters.map((it, ix) =>
          ix === action.index ? { ...counter, value: action.newValue } : it
        ),
      };
    }
  }
}

function addCounter(title: string): Action {
  return { type: "add-counter", title: title };
}

export function CounterList() {
  const [observedState, dispatch] = useReducer(reducer, {
    sum: 0,
    counters: [],
  });
  const context = useContext(ExampleContext);
  return (
    <div>
      <h4>Context is {context.value}</h4>
      <p>Sum is {observedState.sum}</p>
      <InputBox
        title="create counter"
        minLength={3}
        maxLength={10}
        onSubmit={(title) => dispatch(addCounter(title))}
      />
      {observedState.counters.map((it, ix) => (
        <div key={it.id}>
          <Counter
            title={it.title}
            onChange={(newValue) =>
              dispatch({ type: "value-changed", index: ix, newValue: newValue })
            }
          />
          <AutoCounter title={it.title} />
          <button
            onClick={() => dispatch({ type: "delete-counter", index: ix })}
          >
            Remove
          </button>
        </div>
      ))}
    </div>
  );
}
