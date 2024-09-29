import React, { useEffect, useState } from "react";
import productService from "../services/product.service";
import { Link } from "react-router-dom";

const Home = () => {

    const [productList, setProductList] = useState([]);
    useEffect(() => {
        initilazeProductList();

    }, []);

    const initilazeProductList = () => {
        productService.getProductList().then((res) => {
            setProductList(res.data);
            console.log(res.data);
        }).catch((err) => {
            console.log(err);
        });
    }



    const deleteProduct = (productId) => {
        productService.deleteroduct(productId).then((res) => {
            initilazeProductList();
        }).catch((err) => {
            console.log(err);
        })
    }


    return (
        <>
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <div className="card">
                            <div className="card-body">
                                <div className="card-header">
                                    All Products
                                </div>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Id</th>
                                            <th scope="col">Product Name</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Stock</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {productList.map((p, num) => (
                                            <tr>
                                                <td>{num + 1}</td>
                                                <td>{p.productName}</td>
                                                <td>{p.productPrice}</td>
                                                <td>{p.productStock}</td>
                                                <td><Link to={'updateProduct/' + p.productId} className="btn btn-sm btn-primary">Edit</Link> </td>
                                                <td><button onClick={() => deleteProduct(p.productId)} className="btn btn-sm btn-danger">Delete</button> </td>
                                            </tr>
                                        ))
                                        }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Home;