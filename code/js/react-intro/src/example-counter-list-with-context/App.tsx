import { CounterList } from "./CounterList";
import { LanguageSelector } from "./LanguageSelector";
import { MessageProvider } from "./MessageProvider";

export function App() {
  return (
    <>
      <MessageProvider>
        <LanguageSelector />
        <h1>CounterList with Context</h1>
        <CounterList />
      </MessageProvider>
    </>
  );
}
