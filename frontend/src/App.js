import "./index.css";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./components/Login";
import Home from "./components/Home";
import Welcome from "./components/Welcome";
import AuthProvider, { useAuth } from "./components/securityApi/AuthContext";

function App() {
  function AuthRoute({ children }) {
    const authContext = useAuth();
    if (authContext.isAuth) return children;
    return <Navigate to="/" />;
  }
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login isLogin={true} />} />
          <Route path="/signup" element={<Login />} />
          <Route
            path="/home"
            element={
              <AuthRoute>
                <Home />
              </AuthRoute>
            }
          />
          <Route
            path="/welcome"
            element={
              <AuthRoute>
                <Welcome />
              </AuthRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
