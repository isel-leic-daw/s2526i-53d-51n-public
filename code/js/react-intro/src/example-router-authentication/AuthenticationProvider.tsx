import { useState, type ReactNode } from "react";
import { AuthenticationContext, type AuthenticationState } from "./authentication";

type AuthenticationProviderProp = {
  children: ReactNode,
}
export function AuthenticationProvider({ children }: AuthenticationProviderProp) {
  const [observedUsername, setUsername] = useState<string | undefined>(undefined);
  console.log(`provider ${observedUsername}`);
  const value: AuthenticationState  = {
    username: observedUsername,
    setUsername: (username) => setUsername(username),
  };
  return <AuthenticationContext value={value}>{children}</AuthenticationContext>;
}