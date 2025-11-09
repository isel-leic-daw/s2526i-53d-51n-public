import { createRoot } from "react-dom/client";
import React, { createElement } from "react";
import { App } from "./example-counter/App.tsx";

const root = createRoot(document.getElementById("container")!);

root.render(<App />);
