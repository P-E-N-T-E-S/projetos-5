import { Routes, Route } from 'react-router-dom';
import PrivateRoute from '../services/PrivateRoute.jsx';
import Login from '../pages/Login.jsx';
import Home from '../pages/user/Home.jsx';
import AdminHome from '../pages/admin/AdminHome.jsx';
import NaoAutorizado from '../pages/NaoAutorizado.jsx'
import Lista from "../pages/user/Lista.jsx";

const AppRoutes = () => (
    <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/unauthorized" element={<NaoAutorizado />} />

        <Route element={<PrivateRoute allowedCargos={["ROLE_GRADUANDO", "ROLE_MESTRADO", "ROLE_PROFESSOR"]} />}>
            <Route path="/" element={<Home />} />
            <Route path="/minhalista" element={<Lista />} />
        </Route>

        <Route element={<PrivateRoute allowedCargos={["ROLE_BIBLIOTECARIA"]} />}>
            <Route path="/admin" element={<AdminHome />} />
        </Route>
    </Routes>
);

export default AppRoutes;
