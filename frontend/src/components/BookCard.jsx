import React from 'react';
import "./styles/BookCard.css"
import {NavLink} from "react-router-dom";
import {FaBell, FaSearch} from "react-icons/fa";
import {MdOutlineBookmarkAdd} from "react-icons/md";
import capa from '../assets/pythonfluente.png';

const BookCard = () => {
    return (
        <div className="book-card">
            <div className="book-image">
                <img src={capa} alt="Capa do livro"/>
            </div>

            <div className="book-info">
                <h2 className="book-title">Python Fluente: Programação Clara, Con...</h2>
                <p className="book-author">Luciano Ramalho</p>
                <p className="book-description">
                    A simplicidade de Python permite que você se torne produtivo rapidamente, porém isso muitas vezes significa que você não estará usando tudo que ela tem a oferecer. Com este guia prático, você aprende...
                </p>

                <div className="book-buttons">
                    <button className="btn-outline">Mais detalhes</button>
                    <button className="btn-solid">Reservar</button>
                </div>
            </div>
            <div>
                <button className="bookmark-btn"><MdOutlineBookmarkAdd/></button>
            </div>
        </div>
    );
};

export default BookCard;