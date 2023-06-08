import classes from './ProfileForm.module.css';
import { useRef, useContext, useState } from 'react';
import { useHistory } from 'react-router-dom';
import AuthContext from '../../store/auth-context';
const ProfileForm = () => {

  const history = useHistory();
  const[isLoading, setIsLoading] = useState(false);
  const newPassRef = useRef();
  const authCtx =  useContext(AuthContext);
  function formSubmit(event){
    event.preventDefault();
    const enteredNewPassword = newPassRef.current.value;
    setIsLoading(true);
    console.log(isLoading);
    fetch('https://identitytoolkit.googleapis.com/v1/accounts:update?key=AIzaSyAFNIQv4QVSNohvvKXf1fahGtbbm30cMEc',{
      method: 'POST',
      body: JSON.stringify({
        idToken: authCtx.token,
        password: enteredNewPassword,
        returnSecureToken: false
      }),
      headers: {
        'CONTENT-Type': 'application/json'
      }
    }).then(res => {
      setIsLoading(false);
      if(res.ok){
        return res.json();
      }
      else{
        return res.json().then(data => {
          let errorMessage = 'Authentication failed'
          if(data && data.error.message){
            errorMessage = data.error.message;
          }
          throw new Error(errorMessage);
        });
      }
    }).then(data => {
      authCtx.login(data.idToken);
      history.replace('/');
    }).catch(err => {
      alert(err.message);
    })
  }

  return (
    <form className={classes.form} onSubmit={formSubmit}>
      <div className={classes.control}>
        <label htmlFor='new-password'>New Password</label>
        <input type='password' id='new-password' ref={newPassRef}/>
      </div>
      <div className={classes.action}>
        <button className="btn btn-success">Change Password</button>
      </div>
    </form>
  );
}

export default ProfileForm;
