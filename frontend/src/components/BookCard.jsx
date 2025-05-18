import React from 'react';
import "./styles/BookCard.css"
import {NavLink} from "react-router-dom";
import {FaBell, FaSearch} from "react-icons/fa";
import {MdOutlineBookmarkAdd} from "react-icons/md";

const BookCard = () => {
    return (
        <div className="book-card">
            <div className="book-image">
                <img src="caminho/para/imagem.jpg" alt="Capa do livro"/>
            </div>

            <div className="book-info">
                <div className="book-header">
                    <h2 className="book-title">Python Fluente: Programação Clara, Con...</h2>
                    <button className="bookmark-btn"><MdOutlineBookmarkAdd /></button>
                </div>
                <p className="book-author">Luciano Ramalho</p>
                <p className="book-description">
                    A simplicidade de Python permite que você se torne produtivo rapidamente, porém isso muitas vezes
                    significa...
                </p>

                <div className="book-buttons">
                    <button className="btn-outline">Mais detalhes</button>
                    <button className="btn-solid">Reservar</button>
                </div>
            </div>
        </div>
    );
};

export default BookCard;