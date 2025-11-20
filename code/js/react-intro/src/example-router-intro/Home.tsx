import { Link, Outlet } from "react-router";

export function Home() {
  return (
    <div>
      <h2>Home</h2>
      <ul>
        <li>
          <Link to="/panel1">panel 1</Link>
        </li>
        <li>
          <a href="/panel1">panel 1</a>
        </li>
        <li>
          <Link to="/panel2/123">panel 2</Link>
        </li>
        <li>
          <Link to="/panel3/123/path/abc">panel 3 1</Link>
        </li>
        <li>
          <Link to="/panel3/xyz/path/123">panel 3 2</Link>
        </li>
      </ul>
      <Outlet />
    </div>
  );
}
