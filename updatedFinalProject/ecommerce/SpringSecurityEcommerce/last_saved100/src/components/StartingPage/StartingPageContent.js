import Dashboard from '../../pages/Dashboard';
import classes from './StartingPageContent.module.css';
const StartingPageContent = () => {
  return (
    <div>
      <section className={classes.starting}>
      <Dashboard/>
    </section>
    </div>
    
  );
};

export default StartingPageContent;
