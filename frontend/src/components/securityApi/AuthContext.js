import { createContext, useContext, useState } from "react";
import { executeJwtAuthService } from "./AuthApiService";
import { apiClient } from "./ApiClient";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({ children }) {
  const [isAuth, setAuth] = useState(false);
  const [username, setUsername] = useState(null);
  const [token, setToken] = useState(null);

  async function login(username, password) {
    try {
      const response = await executeJwtAuthService(username, password);
      // Check if the response contains the expected status code
      if (response.data.status === 202 && response.data) {
        const jwtToken = "Bearer " + response.data; // Token is in response.data

        // Set auth state
        setAuth(true);
        setUsername(username);
        setToken(jwtToken);

        // Configure apiClient to use the token for future requests
        apiClient.interceptors.request.use((config) => {
          config.headers.Authorization = jwtToken;
          return config;
        });

        return true; // Login successful
      } else {
        logout(); // Logout in case of an unexpected status code
        return false; // Login failed
      }
    } catch (error) {
      console.error("Login error:", error); // Log the error for debugging
      logout(); // Reset the auth state on error
      return false; // Login failed
    }
  }

  function logout() {
    setUsername(null);
    setAuth(false);
    setToken(null);
  }

  return (
    <AuthContext.Provider value={{ isAuth, login, logout, username, token }}>
      {children}
    </AuthContext.Provider>
  );
}
