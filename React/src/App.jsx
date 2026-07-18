import { useState } from "react";
import LoginControl from "./components/LoginControl";
import GuestPage from "./components/GuestPage";
import UserPage from "./components/UserPage";
import "./App.css";

const GUEST_NAME = "Aditi";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => setIsLoggedIn(true);
  const handleLogout = () => setIsLoggedIn(false);

  
  let page;
  if (isLoggedIn) {
    page = <UserPage userName={GUEST_NAME} />;
  } else {
    page = <GuestPage />;
  }

  return (
    <div className="app">
      <header className="app-header">
        <div className="brand">
          <span className="brand-mark">✈</span>
          <span className="brand-name">Skyline</span>
        </div>
        <LoginControl
          isLoggedIn={isLoggedIn}
          onLogin={handleLogin}
          onLogout={handleLogout}
        />
      </header>

      <main>{page}</main>

      <footer className="app-footer">
        <span>Skyline Air &middot; ticketbookingapp</span>
      </footer>
    </div>
  );
}

export default App;
