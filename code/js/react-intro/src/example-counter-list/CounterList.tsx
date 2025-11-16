import { useReducer } from "react";
import { Counter } from "./Counter";
import { InputBox } from "./InputBox";

/*
 * A component to manage a mutable list of counters.
 * Uses the _reducer_ technique to manage the state.
 */

// 0. We start by defining the state.
type CounterState = {
  label: string;
  value: number;
};
type State = {
  total: number;
  counters: Array<CounterState>;
};

// 1. Then we define the actions that may result in a state change.
type Action =
  | { type: "addCounter"; label: string }
  | { type: "removeCounter"; index: number }
  | { type: "valueUpdate"; index: number; newValue: number };

function actionAddCounter(label: string): Action {
  return {
    type: "addCounter",
    label: label,
  };
}

function removeCounter(index: number): Action {
  return {
    type: "removeCounter",
    index: index,
  };
}

function valueUpdate(index: number, newValue: number): Action {
  return {
    type: "valueUpdate",
    index: index,
    newValue: newValue,
  };
}

// 2. The reducer function maps the current state and an action into the next state.
function reducer(state: State, action: Action): State {
  switch (action.type) {
    case "addCounter":
      return {
        ...state,
        counters: [...state.counters, { label: action.label, value: 0 }],
      };

    case "removeCounter": {
      const counter = state.counters[action.index];
      const newTotal = state.total - counter.value;
      return {
        total: newTotal,
        counters: [
          ...state.counters.slice(0, action.index),
          ...state.counters.slice(action.index + 1),
        ],
      };
    }

    case "valueUpdate": {
      const counter = state.counters[action.index];
      const newTotal = state.total - counter.value + action.newValue;
      return {
        total: newTotal,
        counters: [
          ...state.counters.slice(0, action.index),
          { label: counter.label, value: action.newValue },
          ...state.counters.slice(action.index + 1),
        ],
      };
    }
  }
}

export function CounterList() {
  const [observedState, dispatch] = useReducer(reducer, {
    total: 0,
    counters: [],
  });
  return (
    <div>
      <InputBox
        label="label for new counter"
        minLength={2}
        maxLength={10}
        submitFunction={(label) => dispatch(actionAddCounter(label))}
      />
      <p>Total count is {observedState.total}</p>
      {observedState.counters.map((counter, ix) => (
        <div key={ix}>
          <Counter
            label={counter.label}
            onIncrement={(newValue) =>
              dispatch(valueUpdate(ix,  newValue))
            }
          />
          <button
            onClick={() => dispatch(removeCounter(ix))}
          >
            delete
          </button>
        </div>
      ))}
    </div>
  );
}
