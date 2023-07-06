import { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import "./myheader.scss";
import logo from "../img/logo.png";

const Myheader = () => {
  const initialLoginState = Boolean(localStorage.getItem('token'));
  const [isLogin, setIsLogin] = useState(initialLoginState);
  const navigate = useNavigate();

  useEffect(() => {
    const handler = () => {
      const token = localStorage.getItem('token');
      setIsLogin(Boolean(token));
    };

    window.addEventListener('storage', handler);
    return () => {
      window.removeEventListener('storage', handler);
    };
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    setIsLogin(false);
    navigate('/main', { replace: true });
  };

  return (
    <div className="navigation">
      <div className="nav-container">
        <div className="brand">
          <NavLink to="/main">
            <img src={logo} style={{ width: '100px' }} alt="service_logo" />
          </NavLink>
        </div>
        <nav>
          <ul className="nav-list">
            <li>
              <NavLink to="/main" activeClassName="active">
                <span>Home</span>
              </NavLink>
            </li>
            {isLogin && (
              <li>
                <NavLink to="/service" activeClassName="active">
                  <span>Service</span>
                </NavLink>
              </li>
            )}
            <li>
              <NavLink to="/team21" activeClassName="active">
                <span>Team</span>
              </NavLink>
            </li>
            <li>
              <NavLink to="/postlist" activeClassName="active">
                <span>Post</span>
              </NavLink>
            </li>
            <li>
              <NavLink to="/faq" activeClassName="active">
                <span>FAQ</span>
              </NavLink>
            </li>
            {isLogin ? (
              <>
                <li>
                  <NavLink to="/mypage" activeClassName="active">
                    <span>Mypage</span>
                  </NavLink>
                </li>
                <li>
                  <NavLink to="/main" onClick={handleLogout} activeClassName="active">
                    <span>Logout</span>
                  </NavLink>
                </li>
              </>
            ) : (
              <>
                <li>
                  <NavLink to="/login" activeClassName="active">
                    <span>Login</span>
                  </NavLink>
                </li>
                <li>
                  <NavLink to="/signup" activeClassName="active">
                    <span>Sign Up</span>
                  </NavLink>
                </li>
              </>
            )}
          </ul>
        </nav>
      </div>
    </div>
  );
};

export default Myheader;
