import logo from './logo.svg';
import './App.css';
import Navbar from './components/Navbar';
import { Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import AddProuct from './components/AddProduct';
import UpdateProduct from './components/UpdateProductEdit';

function App() {
  return (
    <>
      <Navbar />

      <Routes>
        <Route path='/' element={<Home />} ></Route>
        <Route path='/addProduct' element={<AddProuct />} ></Route>
        <Route path='/updateProduct/:productId' element={<UpdateProduct />} ></Route>
      </Routes>
    </>
  );
}

export default App;
