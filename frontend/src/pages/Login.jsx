import React, { useState } from 'react';
import api from '../services/api';
import './styles/Login.css';
import { useNavigate } from 'react-router-dom';
import {useAuth} from "../services/AuthContext.jsx";

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post('/auth/login', { username, password });
            const { token, cargo } = response.data;
            login(token, cargo);

            if (cargo === "ROLE_BIBLIOTECARIA") {
                navigate('/admin');
            }
            else {
                navigate('/');
            }
        } catch (error) {
            console.error('Erro no login', error);
            setError('Usuário ou senha inválidos');
        }
    };

    return (
        <div className="login-container">
            <div className="login-left">
                <form className="login-content" onSubmit={handleLogin}>
                    <div className="login-loader" />
                    <h2>Olá!</h2>
                    <p>Por favor, insira suas credenciais para entrar.</p>

                    <input
                        type="text"
                        placeholder="E-mail institucional"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />

                    <input
                        type="password"
                        placeholder="Senha"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />

                    <a href="#">Esqueci a minha senha</a>

                    {error && <p className="erro-login">{error}</p>}

                    <button type="submit">Entrar</button>
                </form>
            </div>
            <div className="login-right" />
        </div>
    );
};

export default Login;
