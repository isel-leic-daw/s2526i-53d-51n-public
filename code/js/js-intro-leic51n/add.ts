const msg = "I'm the add.js module";
console.log(msg);
console.log("I'm being served by vite")
export function add(x: number, y: number): number {
  console.log(msg)
  return x + y;
}
