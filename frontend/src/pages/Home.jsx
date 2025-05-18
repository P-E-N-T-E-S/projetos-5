import React from 'react';
import "./styles/Home.css"
import { Sidebar } from '../components/Sidebar.jsx';
import {NavLink} from "react-router-dom";
import {FaBell, FaSearch} from "react-icons/fa";
import BookCard from "../components/BookCard.jsx";

const Home = () => {
    return (
        <div className="app-container">
            <Sidebar />

            <main className="main-content">
                <div className="header">
                    <h1 className="page-title">Home</h1>
                    <div className="icons">
                        <NavLink to="/multas"><FaSearch className="icon"/></NavLink>
                        <NavLink to="/multas"><FaBell className="icon"/></NavLink>
                    </div>
                </div>


                <nav className="tabs">
                    <NavLink to="/" className="tab">Livros</NavLink>
                    <NavLink to="/multas" className="tab">TCCs</NavLink>
                    <NavLink to="/multas" className="tab">Teses</NavLink>
                    <NavLink to="/multas" className="tab">Dissertações</NavLink>
                    <NavLink to="/multas" className="tab">Artigos</NavLink>
                </nav>

                <section className="book-section">
                    <h2 className="section-title">Novidades</h2>
                    <div className="book-grid">
                        <BookCard></BookCard>
                        <BookCard></BookCard>
                    </div>
                </section>

                <section className="book-section">
                    <h2 className="section-title">Arquitetura de Dados (for dummies)</h2>
                    <div className="book-grid">
                        <BookCard></BookCard>
                        <BookCard></BookCard>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default Home;