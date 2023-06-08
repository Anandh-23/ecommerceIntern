import { createContext, useState } from "react";
import Swal from "sweetalert2";
const AuthContext = createContext({
    token: '',
    isLoggedIn: false,
    login:(token) => {},
    logout:() => {}
});

export function AuthContextProvider(props){
    const initalToken = localStorage.getItem('token');
    const [token, setToken] = useState(initalToken);

    const userIsLoggedIn = !!token; //convert bool

    function loginHandler(token){
        setToken(token);
        localStorage.setItem('token',token);
        Swal.fire({
            icon: 'success',
            title: 'Logged In',
            text: `You are logged in`,
            showConfirmButton: false,
            timer: 1500
        })
    }

    function logoutHandler(){
        setToken(null);
        localStorage.removeItem('token');
        localStorage.removeItem('name');

    }

    const contextValue = {
        token: token,
        isLoggedIn: userIsLoggedIn,
        login: loginHandler,
        logout: logoutHandler
    }

    return <AuthContext.Provider value={contextValue}> 
        {props.children}
    </AuthContext.Provider>
}

export default AuthContext;