import React from 'react';
import "styles/Home.css"
import { Sidebar } from '../components/template/Sidebar.jsx';

const Home = () => {
    return (
        <div className="app-container">
            <Sidebar />

            <main className="main-content">

            </main>
        </div>
    );
};

export default Home;