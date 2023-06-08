import { useState, useContext } from "react";
import clientList from "../store/clientList";
import Header from "./Header";
import List from "./List";
import Add from "./Add";
import Edit from "./Edit";
import Swal from "sweetalert2";
import AuthContext from "../store/auth-context";

function Dashboard() {
  const [client, setClient] = useState(clientList);
  const [selectedClient, setSelectedClient] = useState(null);
  const [add, setAdd] = useState(false);
  const [edit, setEdit] = useState(false);

  const authCtx = useContext(AuthContext);

  const handleEdit = (id) => {
    const [client] = clientList.filter(client => client.id === id);
    setSelectedClient(client);
    setEdit(true);
}

  function handleDelete(id) {
    Swal.fire({
        icon: 'warning',
        title: 'Are you sure?',
        text: 'Data will be lost permanently',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it',
        cancelButtonText: 'No, cancel',
    }).then(result =>{
        if(result.value){
            const [client] = clientList.filter(client => client.id === id);
            Swal.fire({
                icon: 'success',
                title: 'Deleted!',
                text: `${client.name} has been deleted `,
                showConfirmButton: false,
                timer: 1500,
            });

            setClient(clientList.filter(client => client.id !== id));
        }
    })
}

  return (
    <div>
      {!add && !edit && authCtx.isLoggedIn && (
        <>
          <Header setAdd={setAdd} />
          <List
            client={client}
            handleEdit={handleEdit}
            handleDelete={handleDelete}
          />
        </>
      )}

      {add && <Add client={client} setClient={setClient} setAdd={setAdd} />}

      {edit && <Edit client={client} selectedClient={selectedClient} setClient={setClient} setEdit={setEdit} />}
    </div>
  );
}

export default Dashboard;
