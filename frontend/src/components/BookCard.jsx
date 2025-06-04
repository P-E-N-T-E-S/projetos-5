import React from 'react';
import "./styles/BookCard.css"
import {MdOutlineBookmarkAdd} from "react-icons/md";
import capa from '../assets/pythonfluente.png';
import Button from "./Button.jsx";

const BookCard = ({ titulo, autores, sinopse }) => {
    return (
        <div className="book-card">
            <div className="book-image">
                <img src={capa} alt="Capa do livro" />
            </div>

            <div className="book-info">
                <h2 className="book-title">{titulo}</h2>
                <p className="book-author">
                    {autores.map((autor, index) => (
                        <span key={autor.id}>
                            {autor.nome}{index < autores.length - 1 ? ', ' : ''}
                        </span>
                    ))}
                </p>
                <p className="book-description">{sinopse}</p>

                <div className="book-buttons">
                    <Button variante="outline" onClick={() => console.log('Mais detalhes')}>
                        Mais detalhes
                    </Button>
                    <Button variante="solid" onClick={() => console.log('Reservar')}>
                        Reservar
                    </Button>
                </div>
            </div>
            <div>
                <button className="bookmark-btn"><MdOutlineBookmarkAdd /></button>
            </div>
        </div>
    );
};


export default BookCard;