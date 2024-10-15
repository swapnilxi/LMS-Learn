import React, { useState } from "react";
import Image from "../assets/4529183.jpg";
import GoogleLogo from "../assets/google.png";
import FacebookLogo from "../assets/facebook.png";
import TwitterLogo from "../assets/twitter.png";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./securityApi/AuthContext";
import { signUpService } from "./api/Signup";

const Login = ({ isLogin }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const authContext = useAuth();

  const GreenColor = "#BEED9A";
  const navigate = useNavigate();

  async function handelLogin() {
    if (await authContext.login(username, password)) {
      navigate("/home");
    } else {
      alert("Invalid username or password");
    }
  }

  async function handelSignUp() {
    try {
      const response = await signUpService(
        firstName,
        lastName,
        email,
        username,
        password
      );

      // Check if the response contains a valid status
      if (response.data && response.data.status === 201) {
        alert("User Created Successfully!");
        navigate("/home");
      } else {
        alert(
          "Error creating user: " + (response.data.message || "Unknown error")
        );
      }
    } catch (error) {
      alert("Error creating user: " + error.message);
    }
  }

  return (
    <div className="flex p-10 justify-center">
      <div className="my-auto">
        <img src={Image} className="w-4/5 h-auto max-w-xl rounded-lg" />
      </div>

      <div className="flex flex-col  gap-5 p-5">
        <h2 className="text-3xl font-bold " style={{ color: GreenColor }}>
          {isLogin ? "Sign in" : "Sign up"}
        </h2>

        <form action="" className="flex flex-col gap-4">
          {!isLogin && (
            <div className="flex gap-8 justify-between">
              <label>
                <div className="flex flex-col gap-1">
                  First name
                  <input
                    type="text"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                    name="fname"
                    className="border rounded-md p-2"
                    placeholder="First name"
                  />
                </div>
              </label>

              <label>
                <div className="flex flex-col gap-1">
                  Last name
                  <input
                    type="text"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                    name="lname"
                    className="border rounded-md p-2"
                    placeholder="Last name"
                  />
                </div>
              </label>

              <label>
                <div className="flex flex-col gap-1">
                  Email
                  <input
                    type="text"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    name="email"
                    className="border rounded-md p-2"
                    placeholder="Email"
                  />
                </div>
              </label>
            </div>
          )}

          <label className="flex flex-col gap-1">
            Username
            <input
              type="text"
              name="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="border rounded-md p-2"
              placeholder="Username"
            />
          </label>

          <label>
            <div className="flex flex-col gap-1">
              Password
              <input
                type="text"
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="border rounded-md p-2"
                placeholder="Password"
              />
            </div>
          </label>

          <div className="flex justify-between align-middle">
            <div className="flex gap-2">
              <input type="checkbox" className="border-black rounded-3xl" />
              <span>Remember me?</span>
            </div>

            <div>
              <span>Forgot password?</span>
            </div>
          </div>

          <div className="flex flex-col gap-4">
            <button
              className={`p-2 bg-greenColor border rounded-md text-white text-xl`}
              type="button"
              name="login"
              onClick={isLogin ? handelLogin : handelSignUp}
            >
              Get Started
            </button>
            <div className="flex gap-2 justify-between">
              <button className="border rounded-md w-1/3 flex justify-center items-center">
                <img src={GoogleLogo} className="h-1/2" />
              </button>
              <button className="border rounded-md w-1/3 flex justify-center items-center">
                <img src={TwitterLogo} className="h-1/2" />
              </button>
              <button className="border rounded-md w-1/3 flex justify-center items-center">
                <img src={FacebookLogo} className="h-1/2" />
              </button>
            </div>

            <span>
              By Signing up, you agree to our Terms of Use and Privacy Policy
            </span>
            <div
              className="text-center cursor-pointer font-semibold"
              onClick={() => (isLogin ? navigate("/signup") : navigate("/"))}
            >
              {isLogin
                ? "Don't have an Account? Sign up here."
                : "Already have an Account? Sign in here."}
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
