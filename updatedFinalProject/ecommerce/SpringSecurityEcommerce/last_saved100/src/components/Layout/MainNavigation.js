import { useContext } from 'react';
import { Link, useHistory } from 'react-router-dom';
import Swal from 'sweetalert2';
import AuthContext from '../../store/auth-context';
import classes from './MainNavigation.module.css';
const MainNavigation = () => {
  const authCtx = useContext(AuthContext);
  const history = useHistory();
  const isLoggedIn = authCtx.isLoggedIn;

  function logoutHandler(){
    Swal.fire({
      icon: 'success',
      title: 'Logged Out',
      text: `You have been logged out`,
      showConfirmButton: false,
      timer: 1500
  })
    authCtx.logout();
    history.replace('/logout')
    
  }

  return (
    <header className={classes.header}>
      <Link to='/'>
        <div className={classes.logo}>Loyalty App</div>
      </Link>
      <nav>
        <ul>
          {!isLoggedIn && (
            <li>
              <Link to='/auth'>Login</Link>
            </li>
          )}
          {isLoggedIn && (
            <li>
              <Link to='/profile'>Profile</Link>
            </li>
          )}
          {isLoggedIn && (
            <li>
              <button onClick={logoutHandler}>Logout</button>
            </li>
          )}
          
        </ul>
      </nav>
    </header>
  );
};

export default MainNavigation;