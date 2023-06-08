import { Link } from "react-router-dom";
import "../index.css";
function List({ client, handleEdit, handleDelete }) {
  return (
    <div>
      <table className="table table-striped table-hover table-move">
        <thead>
          <tr>
            <th scope="col">No.</th>
            <th scope="col">Name</th>
            <th scope="col">Contact</th>
            <th scope="col">Need</th>
            <th scope="col">Category</th>
            <th></th>
            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
          {client.length > 0 ?
          (client.map((client,i) => (
            <tr key={client.id}>
              <td> {i+1} </td>
              <td> {client.name} </td>
              <td>{client.contact}</td>
              <td>{client.need}</td>
              <td>{client.category}</td>
              <td>
                <button className="btn btn-primary" onClick={() => handleEdit(client.id)}>Edit</button>
              </td>
              <td>
                <button className="btn btn-danger"onClick={() => handleDelete(client.id)}>Delete</button>
              </td>
              <td>
              {client.category === 'Prospect' && <button className="btn btn-primary"><Link to="/bill" className="generate">Generate</Link></button>}
              </td>
            </tr>
          ))) : (
            <tr>
                <td>No Client Found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default List;
