import React from 'react';
import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from "./pages/Login.jsx";
import PrivateRoute from "./services/PrivateRoute.jsx";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<Login />} />

                <Route element={<PrivateRoute />}>
                    <Route path="/" element={<Home />} />
                </Route>
            </Routes>
        </Router>
    );
}

export default App;