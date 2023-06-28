import { createContext, useContext, useState } from "react";
import { toast } from 'react-toastify';
export const CartContext = createContext();

const CartProvider = ({ children }) => {
  const token = localStorage.getItem("jwtToken")
  const custId = localStorage.getItem("customerId");
  console.log(custId)
  // const [cart, setCart] = useState({
  //   customer: {
  //     customerId: custId,
  //   },
  //   cartProducts: [],
  // });
  const [cart, setCart] = useState(() => {
    const custId = localStorage.getItem("customerId");
    console.log("IN state",custId)
    return {
      customer: {
        customerId: 11,
      },
      cartProducts: [],
    };
  });
  console.log("Init",cart)
  let customerCartId;
  
  const isCartEmpty = async () => {
    const response = await fetch(
      `http://localhost:8080/cart/customer/11`,{
        headers:{
          'Authorization' : `Bearer ${token}`
      },
      }
    );
    const jsonData = await response.json();
    customerCartId = jsonData.cartId;
    console.log("JSON DATA");
    console.log(jsonData);
    setCart((prevCart) => ({
      ...prevCart,
      cartProducts: jsonData.cartProducts,
    }));
    console.log("CART");
    console.log(cart);
    console.log("CartId",jsonData.cartId)
    return jsonData.cartId === undefined;
  };

  const addToCart = async (product) => {
    const emptyCart = await isCartEmpty();
    let updatedCart;
    if (emptyCart) {
      console.log("Empty Cart");
      console.log("AddTo",cart)
      updatedCart = {
        ...cart,
        cartProducts: [
          {
            product: {
              productId: product.productId,
            },
            quantity: 1,
          },
        ],
      };

      console.log("UP",updatedCart)

      const cartResponse = await fetch("http://localhost:8080/cart/addToCart", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          'Authorization' : `Bearer ${token}`
        },
        body: JSON.stringify(updatedCart),
      });

      if (cartResponse.ok) {
        toast.success("Added Successfully");
      } else {
        console.log("Empty Cart error");
      }
    } 
    
    else {
      const existingProduct = cart.cartProducts.find(
        (cartProduct) => cartProduct.product.productId === product.productId
      );

      if (existingProduct) {
        updatedCart = {
          ...cart,
          cartProducts : cart.cartProducts.map(
            (item) => item.product.productId === product.productId ? 
            {
              ...item,
              quantity: item.quantity + 1,
            } : item
            )
        }
      } 
      
      else {
        updatedCart = {
          ...cart,
          cartProducts: [
            ...cart.cartProducts,
            {
              product: {
                productId: product.productId,
              },
              quantity: 1,
            },
          ],
        };
      }

      const cartResponse = await fetch(
        `http://localhost:8080/cart/updateCart/${customerCartId}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            'Authorization' : `Bearer ${token}`
          },
          body: JSON.stringify(updatedCart),
        }
      );

      if (cartResponse.ok) {
        toast.success("Updated Successfully");
      } else {
        console.log("Update Cart error");
      }
    }
    setCart(updatedCart);
  };

  return (
    <CartContext.Provider value={{ cart, addToCart }}>
      {children}
    </CartContext.Provider>
  );
};

export default CartProvider;
export const useCart = () => useContext(CartContext);