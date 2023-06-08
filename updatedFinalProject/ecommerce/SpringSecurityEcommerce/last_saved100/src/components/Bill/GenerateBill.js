import "./GenerateBill.css";
function GenerateBill() {
  const today = new Date();
  const date = `${today.getDate()}/${
    today.getMonth() + 1
  }/${today.getFullYear()}`;
  const time = new Date();
  const currentTime =
    time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
  return (
    <div className="billcontainer">
      <center>
        <h1>Client Soft Tek</h1>
      </center>
      <div>
        <div className="Invoice">
          <h4>Invoice: 1</h4>
          <h4>Date: {date}</h4>
          <h4>Time: {currentTime}</h4>
        </div>
        <div className="buy">
          <h3>
            To:<span> </span>Anandh
          </h3>
        </div>

        <table className="table table-striped billtable">
          <thead>
            <tr>
              <th scope="col">No.</th>
              <th scope="col">Description</th>
              <th scope="col">PRICE</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>ERP Base Price</td>
              <td>$5000</td>
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>Customization</td>
              <td>$500</td>
            </tr>
            <tr>
              <th scope="row">3</th>
              <td>GST</td>
              <td>$15</td>
            </tr>
            <tr>
              <th scope="row"></th>
              <td>Total</td>
              <td>$5515</td>
            </tr>
          </tbody>
        </table>
      </div>
      <h6 className="caption"><i> Don't hesitate to reach out if you have any questions!!!</i></h6>
    </div>
  );
}

export default GenerateBill;
