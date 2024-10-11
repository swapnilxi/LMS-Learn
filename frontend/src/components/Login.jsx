import React from 'react'
import Image from "../assets/4529183.jpg";
import GoogleLogo from "../assets/google.png";
import FacebookLogo from "../assets/facebook.png";
import TwitterLogo from "../assets/twitter.png";
import { Navigate, useNavigate } from 'react-router-dom';

const Login = ({ isLogin }) => {

  const GreenColor = "#BEED9A";
  const navigate = useNavigate();

  return (
    <div className='flex p-10 justify-center'>
      <div className='my-auto'>
        <img src={Image} className='w-4/5 h-auto max-w-xl rounded-lg' />
      </div>

      <div className='flex flex-col  gap-5 p-5'>
        <h2 className='text-3xl font-bold ' style={{ color: GreenColor }}>{isLogin ? "Sign in" : "Sign up"}</h2>

        <form action="" className='flex flex-col gap-4'>


          {!isLogin && (<div className='flex gap-8 justify-between'>
            <label>
              <div className='flex flex-col gap-1'>
                First name
                <input type="text" name="fname" className='border rounded-md p-2' placeholder='First name' />
              </div>
            </label>

            <label>
              <div className='flex flex-col gap-1'>
                Last name
                <input type="text" name="fname" className='border rounded-md p-2' placeholder='Last name' />
              </div>
            </label>
          </div>)}


          <label>
            <div className='flex flex-col gap-1'>
              Email/Username
              <input type="text" name="fname" className='border rounded-md p-2' placeholder='Email/Username' />
            </div>
          </label>
          <label>
            <div className='flex flex-col gap-1'>
              Password
              <input type="text" name="fname" className='border rounded-md p-2' placeholder='Password' />
            </div>
          </label>

          <div className='flex justify-between align-middle'>
            <div className='flex gap-2'>
              <input type="checkbox" className='border-black rounded-3xl' />
              <span>Remember me?</span>
            </div>

            <div>
              <span>Forgot password?</span>
            </div>
          </div>

          <div className='flex flex-col gap-4'>
            <button className={`p-2 bg-greenColor border rounded-md text-white text-xl`}>Get Started</button>
            <div className='flex gap-2 justify-between'>
              <button className='border rounded-md w-1/3 flex justify-center items-center'>
                <img src={GoogleLogo} className='h-1/2' />
              </button>
              <button className='border rounded-md w-1/3 flex justify-center items-center'>
                <img src={TwitterLogo} className='h-1/2' />
              </button>
              <button className='border rounded-md w-1/3 flex justify-center items-center'>
                <img src={FacebookLogo} className='h-1/2' />
              </button>

            </div>

            <span>By Signing up, you agree to our Terms of Use and Privacy Policy</span>
            <div className='text-center cursor-pointer font-semibold' onClick={() => isLogin ? navigate("/signup") : navigate("/")}>{isLogin ? "Don't have an Account? Sign up here." : "Already have an Account? Sign in here."}</div>
          </div>
        </form>
      </div >
    </div >
  )
}

export default Login