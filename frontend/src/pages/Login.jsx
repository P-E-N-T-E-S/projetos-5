import React, { useState } from 'react';
import api from '../services/api'; // Importa o api.js que você criou
import './styles/Login.css';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null); // Declara o estado de erro
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setError(null); // limpa erro antes de tentar
        try {
            const response = await api.post('/auth/login', { username, password });
            const token = response.data.token;
            localStorage.setItem('token', token);
            alert('Login realizado!');
            navigate('/'); // redireciona para home ou outra página após login
        } catch (error) {
            console.error('Erro no login:', error);
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
