import { ACCESS_TOKEN } from '../constants/index';
import { Navigate, Route, Routes, useLocation } from 'react-router-dom'

export function OAuth2RedirectHandler() {
    const location = useLocation();

    function getUrlParameter(name) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
        var results = regex.exec(location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    };
    
    const token = getUrlParameter('token');
    if(token) {
        localStorage.setItem(ACCESS_TOKEN, token);
    }
    return (
        <Routes>
            <Route path='/' element={<Navigate replace to="/"/>}/>; 
        </Routes>
    )
}