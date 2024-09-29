import React, { useState } from "react";
import productService from "../services/product.service";

const AddProuct = () => {
    const [product, setProduct] = useState({
        productName: "",
        productPrice: "",
        productStock: ""
    });

    const handleChange = (e) => {
        const value = e.target.value;
        setProduct({ ...product, [e.target.name]: value });
    }

    const ProudctRegister = (e) => {
        e.preventDefault();
        productService.addProduct(product).then((reponse) => {
            console.log("Saved successfuly!");
        }).catch((err) => {
            console.log("Saved error!");
        })
    }

    return (
        <>
            <div className="container">
                <div className="row">
                    <div className="col-md-6 offset-md-3">
                        <div className="card">
                            <div className="card-header">
                                Add Product Page
                            </div>
                            <div className="card-body">
                                <form onSubmit={(e => ProudctRegister(e))}>
                                    <div className="mb-3">
                                        <label>Product Name:</label>
                                        <input type="text" name="productName" className="form-control" onChange={(e) => handleChange(e)} ></input>
                                    </div>

                                    <div className="mb-3">
                                        <label>Product Price:</label>
                                        <input type="number" name="productPrice" className="form-control" onChange={(e) => handleChange(e)}></input>
                                    </div>


                                    <div className="mb-3">
                                        <label>Product Stock:</label>
                                        <input type="number" name="productStock" className="form-control" onChange={(e) => handleChange(e)}></input>
                                    </div>
                                    <button className="btn btn-primary col-md-12">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default AddProuct;