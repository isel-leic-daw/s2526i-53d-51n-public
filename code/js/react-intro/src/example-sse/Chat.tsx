import { useEffect, useReducer } from "react";

type Event =
  | {
      type: "message";
      ts: Date;
      ix: number;
      messageType: string;
      messageValue: string;
      id: string;
    }
  | { type: "error"; ts: Date; ix: number }
  | { type: "open"; ts: Date; ix: number };

type State = {
  nextIx: number;
  events: Array<Event>;
};

type Action =
  | {
      type: "new-message";
      ts: Date;
      messageType: string;
      messageValue: string;
      id: string;
    }
  | { type: "new-error"; ts: Date }
  | { type: "open"; ts: Date };

function reducer(state: State, action: Action): State {
  const copy = state.events.slice(0, 10);
  switch (action.type) {
    case "new-message":
      return {
        nextIx: state.nextIx + 1,
        events: [
          ...copy,
          {
            type: "message",
            ts: action.ts,
            messageType: action.messageType,
            messageValue: action.messageValue,
            id: action.id,
            ix: state.nextIx,
          },
        ],
      };

    case "new-error":
      return {
        nextIx: state.nextIx + 1,
        events: [
          ...copy,
          {
            type: "error",
            ts: action.ts,
            ix: state.nextIx,
          },
        ],
      };

    case "open":
      return {
        nextIx: state.nextIx + 1,
        events: [
          ...copy,
          {
            type: "open",
            ts: action.ts,
            ix: state.nextIx,
          },
        ],
      };
  }
}

const initialState: State = { nextIx: 0, events: [] };
export function Chat() {
  const [state, dispatch] = useReducer(reducer, initialState);

  useEffect(() => {
    const eventSource = new EventSource(
      "http://localhost:5173/api/chat/listen"
    );

    eventSource.onopen = () => dispatch({ type: "open", ts: new Date() });
    eventSource.onerror = () => dispatch({ type: "new-error", ts: new Date() });
    eventSource.onmessage = (ev) =>
      dispatch({
        type: "new-message",
        ts: new Date(),
        id: ev.lastEventId,
        messageType: ev.type,
        messageValue: ev.data,
      });

    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <ul>
      {state.events.map((ev) => (
        <li key={ev.ix}>
          <dl>
            <dt>type</dt>
            <dd>{ev.type}</dd>
            <dt>ts</dt>
            <dd>{ev.ts.toISOString()}</dd>
            <dt>other</dt>
            <dd>{JSON.stringify(ev)}</dd>
          </dl>
        </li>
      ))}
    </ul>
  );
}
