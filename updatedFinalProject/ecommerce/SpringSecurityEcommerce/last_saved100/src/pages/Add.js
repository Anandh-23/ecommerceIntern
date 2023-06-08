import { useState, useRef, useEffect } from "react";
import Swal from "sweetalert2";
import './Add.css'

function Add({ client, setClient, setAdd }) {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [contact, setContact] = useState("");
  const [budget, setBudget] = useState("");
  const [need, setNeed] = useState("");
  const [size, setSize] = useState("");
  const [date, setDate] = useState("");
  const input = useRef(null);

  useEffect(() => {
    input.current.focus();
  }, []);

  function handleAdd(event) {
    event.preventDefault();
    if (!name || !email || !contact || !budget || !need || !size || !date) {
      return Swal.fire({
        icon: "error",
        title: "Error!",
        text: "All fields are required",
        showConfirmButton: true,
      });
    }
    const id = client.length + 1;
    const category = "Prospect";

    const newClient = {
      id,
      name,
      email,
      contact,
      budget,
      need,
      size,
      category,
      date,
    };
    client.push(newClient);
    setClient(client);
    setAdd(false);
    console.log(category + " " + need)

    Swal.fire({
        icon: 'success',
        title: 'Added',
        text: `${name} has been added`,
        showConfirmButton: false,
        timer: 1500
    })
  }


  return (
    <div className="container">
      <form onSubmit={handleAdd}>
        <center><h1>Add Client</h1></center>
        <div>
          <label htmlFor="name">Name</label>
          <input
            id="name"
            type="text"
            ref={input}
            placeholder="Enter name"
            name="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          ></input>
        </div>

        <div>
          <label htmlFor="email">Email</label>
          <input
            id="email"
            type="email"
            placeholder="Enter mail id"
            name="mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          ></input>
        </div>

        <div>
          <label htmlFor="contact">Contact</label>
          <input
            id="contact"
            type="number"
            placeholder="Enter Contact"
            name="contact"
            value={contact}
            onChange={(e) => setContact(e.target.value)}
          ></input>
        </div>

        <div>
          <label htmlFor="budget">Budget</label>
          <input
            id="budget"
            type="number"
            placeholder="Enter Budget"
            name="budget"
            value={budget}
            onChange={(e) => setBudget(e.target.value)}
          ></input>
        </div>

        <div>
          <label htmlFor="need">Need</label>
          <input
            id="need"
            type="text"
            placeholder="Enter Need"
            name="need"
            value={need}
            onChange={(e) => setNeed(e.target.value)}
          ></input>
        </div>

        <div>
          <label htmlFor="size">Size</label>
          <input
            id="size"
            type="number"
            placeholder="Enter Size"
            name="size"
            value={size}
            onChange={(e) => setSize(e.target.value)}
          ></input>
        </div>

        <div>
          <label htmlFor="date">Date</label>
          <input
            id="date"
            type="date"
            name="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
          ></input>
        </div>

        <div className="center">
          <input className="btn btn-success" type="submit" value="Add" />
          <input className="btn btn-danger" type="button" value="Cancel" onClick={() => setAdd(false)} />
        </div>
      </form>
    </div>
  );
}

export default Add;
