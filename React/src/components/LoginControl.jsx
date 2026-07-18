function LoginButton({ onClick }) {
  return (
    <button className="btn btn-accent" onClick={onClick}>
      Log in
    </button>
  );
}

function LogoutButton({ onClick }) {
  return (
    <button className="btn btn-ghost" onClick={onClick}>
      Log out
    </button>
  );
}

/**
 * LoginControl decides which button to render based on the
 * isLoggedIn flag. This is the "element variable" pattern:
 * we build up the element in a variable, then return it.
 */
function LoginControl({ isLoggedIn, onLogin, onLogout }) {
  let button;

  if (isLoggedIn) {
    button = <LogoutButton onClick={onLogout} />;
  } else {
    button = <LoginButton onClick={onLogin} />;
  }

  return <div className="login-control">{button}</div>;
}

export default LoginControl;
