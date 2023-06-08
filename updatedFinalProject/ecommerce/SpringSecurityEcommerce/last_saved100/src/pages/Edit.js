import { useState } from "react";
import Swal from "sweetalert2";
import clientList from "../store/clientList";
import './Add.css'

function Edit({ client, selectedClient, setClient, setEdit }) {
  const id = selectedClient.id;
  const [name, setName] = useState(selectedClient.name);
  const [email, setEmail] = useState(selectedClient.email);
  const [contact, setContact] = useState(selectedClient.contact);
  const [budget, setBudget] = useState(selectedClient.budget);
  const [need, setNeed] = useState(selectedClient.need);
  const [size, setSize] = useState(selectedClient.size);
  const [date, setDate] = useState(selectedClient.date);
    
  function handleUpdate(event){
        event.preventDefault();
        if (!name || !email || !contact || !budget || !need || !size || !date) {
          return Swal.fire({
            icon: "error",
            title: "Error!",
            text: "All fields are required",
            showConfirmButton: true,
          });
        }
        const category = "Prospect";
        const updateClient = {
            id,
            name,
            email,
            contact,
            budget,
            need,
            size,
            category,
            date
        };

        for(let i=0;i<client.length;i++){
            if(client[i].id === id ){
                client.splice(i, 1, updateClient);
                break;
            }
        }
        setClient(clientList);
        setEdit(false);
    
        Swal.fire({
            icon: 'success',
            title: 'Updated',
            text: `${name} has been added`,
            showConfirmButton: false,
            timer: 1500
        })
      }

  return(
    <div className="container">
      <form onSubmit={handleUpdate}>
      <center><h1>Edit Client Info</h1></center>
        <div className="form-group">
          <label htmlFor="name">Name :</label>
          <input
            id="name"
            type="text"
            placeholder="Enter first name"
            name="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          ></input>
        </div>

        <div className="form-group">
          <label htmlFor="email">Email :</label>
          <input
            id="email"
            type="email"
            placeholder="Enter mail id"
            name="mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          ></input>
        </div>

        <div className="form-group">
          <label htmlFor="contact">Contact :</label>
          <input
            id="contact"
            type="number"
            placeholder="Enter Contact"
            name="contact"
            value={contact}
            onChange={(e) => setContact(e.target.value)}
          ></input>
        </div>

        <div className="form-group">
          <label htmlFor="budget">Budget :</label>
          <input
            id="budget"
            type="number"
            placeholder="Enter Budget"
            name="budget"
            value={budget}
            onChange={(e) => setBudget(e.target.value)}
          ></input>
        </div>

        <div className="form-group">
          <label htmlFor="need">Need :</label>
          <input
            id="need"
            type="text"
            placeholder="Enter Need"
            name="need"
            value={need}
            onChange={(e) => setNeed(e.target.value)}
          ></input>
        </div>

        <div className="form-group">
          <label htmlFor="size">Size :</label>
          <input
            id="size"
            type="number"
            placeholder="Enter Size"
            name="size"
            value={size}
            onChange={(e) => setSize(e.target.value)}
          ></input>
        </div>

        <div className="form-group">
          <label htmlFor="date">Date</label>
          <input
            id="date"
            type="date"
            name="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
          ></input>
        </div>

        <div className="form-group center">
          <input className="btn btn-success" type="submit" value="Update" />
          <input className="btn btn-danger" type="button" value="Cancel" onClick={() => setEdit(false)} />
        </div>
      </form>
    </div>
  )
}

export default Edit;
