import './Navbar.css';
import { ACCESS_TOKEN, GOOGLE_AUTH_URL } from '../constants';
import { NavLink } from 'react-router-dom'
import LogoutIcon from '@mui/icons-material/Logout';
import GoogleIcon from '@mui/icons-material/Google';
import { ReactComponent as NavLogoImage } from './img/로고시안.svg'

export function Navbar (props) {

  return (
    <nav>
      <NavLink to='/'>
        <NavLogoImage width="60"></NavLogoImage>
      </NavLink>
      { props.authenticated ? (
        <div className='nav-div'>
          <NavLink className='nav-decoration' to='profile' style={{ textDecoration : 'none' }}>Profile</NavLink>
          <NavLink className='nav-decoration' to='team' style={{ textDecoration : 'none' }}>Team</NavLink>
          <NavLink to='/'>
            <LogoutIcon
              className='nav-decoration'
              onClick={() => {
                props.setAuthenticated(false)
                localStorage.removeItem(ACCESS_TOKEN)
            }}/>
          </NavLink>
        </div>
        ): (
        <div className='nav-div'>
          <a className='nav-decoration' href={GOOGLE_AUTH_URL} style={{ textDecoration : 'none' }}>
            <GoogleIcon/>
          </a>
        </div>
      )}
    </nav>
  );
};
