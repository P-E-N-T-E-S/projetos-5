import React from 'react';
import { NavLink } from 'react-router-dom';
import {FaBell, FaSearch} from 'react-icons/fa';
import './styles/Header.css';

export const Header = ({ title }) => {
    return (
        <div className="header">
            <h1 className="page-title">{title}</h1>
            <div className="icons">
                <NavLink to="/pesquisar"><FaSearch className="icon"/></NavLink>
                <NavLink to="/notificacoes"><FaBell className="icon"/></NavLink>
            </div>
        </div>
    )
}