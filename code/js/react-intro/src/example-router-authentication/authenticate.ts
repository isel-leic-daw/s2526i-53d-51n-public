/*
 * Some functions to *simulate* an authentication remote API.
 */
function delay(delayInMs: number) {
  return new Promise(resolve => {
    setTimeout(() => resolve(undefined), delayInMs);
  });
}

export async function authenticate(
  username: string, 
  password: string
): Promise<string | undefined> {
    await delay(2000);
    if(username == 'carol') {
      throw new Error("Carol is forbidden")
    }
    if ((username == 'alice' || username == 'bob') && password == '1234') {
      return username;
    }
    return undefined;
  }

