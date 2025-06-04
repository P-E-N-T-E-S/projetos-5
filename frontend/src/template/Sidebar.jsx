import React from 'react';
import { NavLink } from 'react-router-dom';
import { FaHome, FaMoneyBillWave } from 'react-icons/fa';
import {MdBook, MdEditNote} from "react-icons/md";
import {PiArrowsDownUp} from "react-icons/pi";
import {IoMdSettings} from "react-icons/io";
import './styles/Sidebar.css';
import avatar from '../assets/avatar.png';

export const Sidebar = () => {
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
                <li><NavLink to="/"><FaHome className="icon" /> Home</NavLink></li>
                <li><NavLink to="/minhalista"><MdBook className="icon"/> Minha lista</NavLink></li>
                <li><NavLink to="/emprestimos"><PiArrowsDownUp className="icon"/> Empréstimos</NavLink></li>
                <li><NavLink to="/publicacoes"><MdEditNote className="icon"/> Publicações</NavLink></li>
                <li><NavLink to="/multas"><FaMoneyBillWave className="icon"/> Multas</NavLink></li>
                <li><NavLink to="/configuracoes"><IoMdSettings className="icon"/> Configurações</NavLink></li>
            </ul>
        </div>
    )
}