import React, { useState, useEffect } from 'react';
import api from "../../services/api.js"
import "../styles/Home.css";
import { AdminSidebar } from '../../template/AdminSidebar.jsx';
import { NavLink } from "react-router-dom";
import BookCard from "../../components/BookCard.jsx";
import {Header} from "../../template/Header.jsx";

const AdminHome = () => {
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
            <AdminSidebar />

            <main className="main-content">
                <Header title="Dashboard"/>

                <nav className="tabs">
                    <NavLink to="/frontend/public" className="tab">Livros</NavLink>
                    <NavLink to="/multas" className="tab">TCCs</NavLink>
                    <NavLink to="/multas" className="tab">Teses</NavLink>
                    <NavLink to="/multas" className="tab">Dissertações</NavLink>
                    <NavLink to="/multas" className="tab">Artigos</NavLink>
                </nav>

                <section className="book-section">
                    <h2 className="section-title">Arquitetura de Dados (for dummies)</h2>
                    <div className="book-grid">
                        {livros.map((livro) => (
                            <BookCard
                                key={livro.isbn}
                                titulo={livro.titulo}
                                autores={livro.autores}
                                sinopse={livro.sinopse}
                            />
                        ))}
                    </div>
                </section>
            </main>
        </div>
    );
};

export default AdminHome;
