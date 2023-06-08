import { useState, useEffect } from "react";
import HashLoader from "react-spinners/HashLoader";
import classes from './Loader.module.css';
function Loader() {
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    setLoading(true);
      setTimeout(() => {
        setLoading(false);
      }, 8000);
  }, []);
  return (
    <div className={classes.alignCenter}>
      {loading ? (
        <HashLoader
          color={`#1630db`}
          loading={loading}
          size={70}
          aria-label="Loading Spinner"
          data-testid="loader"
          speedMultiplier={2}
        />
      ) : null}
    </div>
  );
}

export default Loader;
