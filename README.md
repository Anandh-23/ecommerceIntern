# ecommerceIntern

const [cart, setCart] = useState({
        customer: {
            customerId:custId
        },
        cartProducts:[]
    });

    const isCartEmpty = async() => {
        const response = await fetch(`http://localhost:8080/cart/customer/${custId}`);
        const jsonData = await response.json();
        console.log("JSON DATA");
        console.log(jsonData);
        setCart(jsonData);
        console.log("CART");
        console.log(cart);
        return cart.cartProducts.length === 0;
    };
