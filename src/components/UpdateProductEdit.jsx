import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import productService from "../services/product.service";

const UpdateProduct = () => {
    const [product, setProduct] = useState({
        productId: "",
        productName: "",
        productPrice: "",
        productStock: ""
    });

    const { productId } = useParams();
    console.log(productId);
    const navigate = useNavigate();

    useEffect(() => {
        productService.getProductById(productId).then((res) => {
            console.log(res.data);
            setProduct(res.data);
        }).catch((err) => {
            console.log(err);
        })
    }, []);

    const handleChange = (e) => {
        const value = e.target.value;
        setProduct({ ...product, [e.target.name]: value });
    }

    const ProudctUpdate = (e) => {
        e.preventDefault();
        productService.updateroduct(product).then((reponse) => {
            console.log("Updated successfuly!");
            navigate("/");
        }).catch((err) => {
            console.log("Update error!");
        })
    }

    return (
        <>
            <div className="container">
                <div className="row">
                    <div className="col-md-6 offset-md-3">
                        <div className="card">
                            <div className="card-header">
                                Update Product Page
                            </div>
                            <div className="card-body">
                                <form onSubmit={(e => ProudctUpdate(e))}>
                                    <div className="mb-3">
                                        <label>Product Name: </label>
                                        <input type="text" name="productName" className="form-control" onChange={(e) => handleChange(e)} value={product.productName}></input>
                                    </div>

                                    <div className="mb-3">
                                        <label>Product Price:</label>
                                        <input type="number" name="productPrice" className="form-control" onChange={(e) => handleChange(e)} value={product.productPrice}></input>
                                    </div>


                                    <div className="mb-3">
                                        <label>Product Stock:</label>
                                        <input type="number" name="productStock" className="form-control" onChange={(e) => handleChange(e)} value={product.productStock}></input>
                                    </div>
                                    <button className="btn btn-primary col-md-12">Update</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default UpdateProduct;