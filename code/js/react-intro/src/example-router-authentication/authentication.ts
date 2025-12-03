import { useContext } from 'react';
import { createContext } from 'react';

export type AuthenticationState = {
  /** read the currently authenticated user, or `undefined` if not authenticated user exists */
  username: string | undefined;
  /** set the currently authenticated user or logout (set `undefined`) */
  setUsername: (username: string | undefined) => void;
};

export const AuthenticationContext = createContext<AuthenticationState>({ username: undefined, setUsername: () => {} });

/**
 * Custom hook to access the authentication state
 * @returns a pair with the currently authentication user and a function to set it
 */
// Custom Hook
export function useAuthentication(): [string | undefined, (value: string | undefined) => void] {
  const state: AuthenticationState = useContext(AuthenticationContext);

  return [
    state.username,
    state.setUsername
  ]
}

