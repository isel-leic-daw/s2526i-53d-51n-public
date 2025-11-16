# React context

0. Start by defining the type of the context

```typescript
type ExampleContextType = {
  value: number;
  setValue: (value: number) => value;
};
```

1. Create an object that "represents" the context

```typescript
const ExampleContext = React.createContext<ExampleContextType>(defaultValue);
```

3. Create a "provider" component that uses the context internally

```typescript
function ExampleContextProvider({children}) {
    return (
        <ExampleContext value = {context value visible by the descendants}>
            {children}
        </ExampleContext>
    )
}
```

4. Use the provider

```typescript
function App() {
  return 
  <ExampleContextProvider>
    <...>
  </ExampleContextProvider>;
}
```

5. Descendants use the context

```typescript
function SomeComponent() {
  const context = useContext(ExampleContext);
}
```
