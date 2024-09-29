import axios from "axios";

const API_URI = "http://localhost:8080/api/products";

class ProductService {

    addProduct(product) {
        return axios.post(API_URI, product);
    }

    getProductById(productId) {
        return axios.get(API_URI + "/" + productId);
    }

    getProductList() {
        return axios.get(API_URI + "/list/all");
    }

    updateroduct(product) {
        return axios.put(API_URI + '/update', product);
    }

    deleteroduct(productId) {
        return axios.delete(API_URI + '/' + productId);
    }
}


export default new ProductService;