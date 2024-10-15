import { apiClient } from "../securityApi/ApiClient";

export const signUpService = (firstName, lastName, userEmail, userName, userPassword) =>
  apiClient.post(`/user/create-user`, {
    firstName,
    lastName,
    userEmail,
    userName,
    userPassword,
  });
