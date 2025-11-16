import { useReducer } from "react";
import { Counter } from "./Counter";
import { InputBox } from "./InputBox";

/*
 * A component to manage a mutable list of counters.
 * Uses the _reducer_ technique to manage the state.
 */

// 0. We start by defining the state.
type CounterState = {
  id: string;
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
  | { type: "removeCounter"; id: string }
  | { type: "valueUpdate"; id: string; newValue: number };

function actionAddCounter(label: string): Action {
  return {
    type: "addCounter",
    label: label,
  };
}

function actionRemoveCounter(id: string): Action {
  return {
    type: "removeCounter",
    id: id,
  };
}

function actionValueUpdate(id: string, newValue: number): Action {
  return {
    type: "valueUpdate",
    id: id,
    newValue: newValue,
  };
}

// 2. The reducer function maps the current state and an action into the next state.
function reducer(state: State, action: Action): State {
  switch (action.type) {
    case "addCounter": {
      const id = Math.random().toString();
      return {
        ...state,
        counters: [
          ...state.counters,
          { id: id, label: action.label, value: 0 },
        ],
      };
    }

    case "removeCounter": {
      const counter = state.counters.find((it) => it.id == action.id);
      if (!counter) {
        throw new Error("id not found");
      }
      const newTotal = state.total - counter.value;
      return {
        total: newTotal,
        counters: state.counters.filter((it) => it.id !== action.id),
      };
    }

    case "valueUpdate": {
      const counter = state.counters.find((it) => it.id == action.id);
      if (!counter) {
        throw new Error("id not found");
      }
      const newTotal = state.total - counter.value + action.newValue;
      return {
        total: newTotal,
        counters: state.counters.map((it) =>
          it.id === action.id ? { ...it, value: action.newValue } : it
        ),
      };
    }
  }
}
const initialState = {
  total: 0,
  counters: [],
};
export function CounterList() {
  const [observedState, dispatch] = useReducer(reducer, initialState);
  return (
    <div>
      <InputBox
        label="label for new counter"
        minLength={2}
        maxLength={10}
        onSubmit={(label) => dispatch(actionAddCounter(label))}
      />
      <p>Total count is {observedState.total}</p>
      {observedState.counters.map((counter) => (
        <div key={counter.id}>
          <Counter
            label={counter.label}
            onIncrement={(newValue) =>
              dispatch(actionValueUpdate(counter.id, newValue))
            }
          />
          <button onClick={() => dispatch(actionRemoveCounter(counter.id))}>
            delete
          </button>
        </div>
      ))}
    </div>
  );
}
