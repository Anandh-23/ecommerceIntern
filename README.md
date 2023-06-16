import { useState, useEffect } from "react";
import { Card, Button } from "react-bootstrap";

const Cart = () => {
  localStorage.setItem("customerId", 16);
  const custId = localStorage.getItem("customerId");

  const [cartItems, setCartItems] = useState([]);
  const [cart, setCart] = useState({
    customer: {
      customerId: custId,
    },
    cartProducts: [],
  });
  const [change, setChange] = useState(false);

  const updateCartItems = (jsonData) => {
    setCartItems(jsonData.cartProducts);
    console.log(jsonData.cartProducts);
    setCart((prevCart) => ({
      ...prevCart,
      cartProducts: jsonData.cartProducts,
    }));
  };

  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/cart/customer/${custId}`
        );
        const jsonData = await response.json();

        console.log(jsonData);
        updateCartItems(jsonData);
      } catch (error) {
        console.error("Error fetching cart:", error);
      }
    };

    fetchCart();
  }, [change]);

  const incrementHandler = (cartProductId , quantity ) => {
    const updatedCartItems = cartItems.map((item,index) => {
      if(index === cartProductId){
        return {...item, quantity : quantity + 1};
      }
      return item;
    });
    setChange(!change);
    setCartItems(updateCartItems);
  }

  const decrementHandler = (cartProductId , quantity ) => {
    if(quantity <= 1){
      return;
    }

    const updatedCartItems = cartItems.map((item,index) => {
      if(index === cartProductId){
        return {...item, quantity : quantity - 1};
      }
      return item;
    });

    setChange(!change);
    setCartItems(updateCartItems);
    
  }

  return (
    <div className = "d-flex justify-content-center align-items-center" style={{minHeight : "100vh"}}>
      <div className = "mx-auto">
        <div style={{ marginTop: "8rem"}} className="container">
        {Array.isArray(cartItems) && cartItems.length > 0 ? (
          <center>
              {cartItems.map((item,index) => (
                <div className="row mb-4"  key={item.cartProductId}>
                  <div className="col-lg-4 col-md-5 col-sm-8 mb-3">
                    <Card style={{height : "100%", width: "500px"}} key={index}>
                      <div className="row no-gutters">
                        <div className="col-md-5">
                          <Card.Img
                            variant="top"
                            src={item.product.url}
                            className= "ml-2 mt-2 mb-1"
                            alt={item.product.productName}
                            style={{ width: "100%", height: "auto" }}
                          />
                          <Card.Text>
                              <span className="mb-2">
                                <button style={{background:"none", border: "none", outline: "none"}} 
                                    onClick = {() => decrementHandler(index, item.quantity)}>
                                  -
                                </button>
                                <b>{item.quantity}</b>
                                <button style={{background:"none", border: "none", outline: "none"}} 
                                    onClick = {() => incrementHandler(index, item.quantity)}>
                                  +
                                </button>
                              </span>
                          </Card.Text>
                        </div>
                        <div className="col-md-6 d-flex flex-column justify-content-between" style ={{marginTop : "1rem"}}>
                          <div>
                            <Card.Title>Name : {item.product.productName}</Card.Title>
                            <p>Price : {item.product.price * item.quantity}</p>
                            <Button variant="danger">
                              Remove
                            </Button>
                          </div>
                        </div>
                      </div>
                    </Card>
                  </div>
                </div>
              ))}
            <div className="row">
              <div className="col-lg-12 d-flex justify-content-center">
                <Button variant="success">
                  Order Now (Total : Rs. {cartItems.amount})
                </Button>
              </div>
            </div>
          </center>
        ) : (
          <p>No items in the cart.</p>
        )}
        </div>
      </div>
    </div>
  );
};

export default Cart;
