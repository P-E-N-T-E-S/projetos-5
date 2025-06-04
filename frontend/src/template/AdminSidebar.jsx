import React from 'react';
import { NavLink } from 'react-router-dom';
import { FaMoneyBillWave } from 'react-icons/fa';
import {MdDashboard, MdEditNote, MdOutlineBookmarkRemove} from "react-icons/md";
import {IoMdSettings} from "react-icons/io";
import './styles/Sidebar.css';
import avatar from '../assets/avatar.png';
import {GiBookshelf} from "react-icons/gi";

export const AdminSidebar = () => {
    return (
        <div className="sidebar">
            <div className="info-user">
                <img src={avatar} alt="Foto de perfil" className="avatar"></img>
                <div className="info-user2">
                    <p className="username">Ana Clara</p>
                    <p className="email">acad@cesar.school</p>
                </div>
            </div>
            <ul className="nav-list">
                <li><NavLink to="/admin"><MdDashboard className="icon" /> Dashboard</NavLink></li>
                <li><NavLink to="/minhalista"><GiBookshelf className="icon"/> Acervo</NavLink></li>
                <li><NavLink to="/emprestimos"><MdOutlineBookmarkRemove className="icon"/> Empréstimos</NavLink></li>
                <li><NavLink to="/publicacoes"><MdEditNote className="icon"/> Publicações</NavLink></li>
                <li><NavLink to="/multas"><FaMoneyBillWave className="icon"/> Multas</NavLink></li>
                <li><NavLink to="/configuracoes"><IoMdSettings className="icon"/> Configurações</NavLink></li>
            </ul>
        </div>
    )
}