import React, { useEffect } from 'react';
import "./styles/Button.css"

const Button = ({ onClick, type = 'button', children, disabled = false, variante = 'solid' }) => {
    return (
        <button
            type={type}
            onClick={onClick}
            disabled={disabled}
            className={`btn-${variante}`}
        >
            {children}
        </button>
    );
};

export default Button;
