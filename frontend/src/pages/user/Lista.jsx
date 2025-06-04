import React, { useState, useEffect } from 'react';
import api from "../../services/api.js"
import "../styles/Lista.css";
import { Sidebar } from '../../template/Sidebar.jsx';
import { NavLink } from "react-router-dom";
import { FaBell, FaSearch } from "react-icons/fa";
import {Header} from "../../template/Header.jsx";

const Lista = () => {
    const [livros, setLivros] = useState([]);

    useEffect(() => {
        async function fetchLivros() {
            try {
                const response = await api.get('/livros');
                console.log('Livros recebidos:', response.data);
                setLivros(response.data);
            } catch (error) {
                console.error('Erro ao buscar livros:', error);
            }
        }

        fetchLivros();
    }, []);

    return (
        <div className="app-container">
            <Sidebar />

            <main className="main-content">
                <Header title="Minhas listas" />

            </main>
        </div>
    );
};

export default Lista;
