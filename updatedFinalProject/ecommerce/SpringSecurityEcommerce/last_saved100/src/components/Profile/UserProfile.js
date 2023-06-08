import ProfileForm from './ProfileForm';
import classes from './UserProfile.module.css';

const UserProfile = () => {
  const username = localStorage.getItem("name");
  return (
    <center className={classes.profile}>
      <h1>{"Hello " + username}</h1>
      <ProfileForm />
    </center>
  );
};

export default UserProfile;
