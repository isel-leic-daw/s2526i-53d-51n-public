import { createRoot } from "react-dom/client";
import React, { createElement } from "react";
import { ReactElement } from "react";
import { createMutationObserver } from "./mutationObserver";

console.log("main running");
const root = createRoot(document.getElementById("container")!);

type Model = {
  items: Array<string>;
  nextId: number;
};
const model: Model = {
  items: [],
  nextId: 0,
};

function updateModel() {
  model.items.push(`item-${model.nextId++}`);
  if (model.items.length > 5) {
    model.items.shift();
  }
}

type ItemProps = {
  label: string;
};

// React Component
function Item({ label }: ItemProps) {
  return (
    <div key={label}>
      <p>{label}</p>
      <input type="text" />
    </div>
  );
}

function computeView(model: Model): ReactElement {
  return (
    <div>
      {model.items.map((it) => (
        <Item key={it} label={it} />
      ))}
    </div>
  );
}

const observer = createMutationObserver();
observer.observe(document.getElementById("container")!, {
  childList: true,
  subtree: true,
});

setInterval(() => {
  updateModel();
  root.render(computeView(model));
}, 2000);
