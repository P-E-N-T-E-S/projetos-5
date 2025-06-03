import React from 'react';
import './App.css'
import {AuthProvider} from "./services/AuthContext.jsx";
import AppRoutes from "./routes/AppRoutes.jsx";
import {BrowserRouter} from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <AuthProvider>
                <AppRoutes/>
            </AuthProvider>
        </BrowserRouter>
    );
}

export default App;