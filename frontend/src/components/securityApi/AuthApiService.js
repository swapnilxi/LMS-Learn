import { apiClient } from "./ApiClient";

export const executeJwtAuthService = (userName, userPassword) =>
  apiClient.post(`/public/login-user`, { userName, userPassword });
