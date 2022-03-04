import { Link, useNavigate } from 'react-router-dom';

import '../styles/Header.css';


const Header = () => {
    const navigate = useNavigate();
    
    return (
        <div className='menu component'>
            <div className='menuitem'>
                <div className='menuitem'>
                    <Link to='/'>
                        <div>Library</div>
                    </Link>
                </div>
                <div className='menuitem'> | </div>
                <div className='menuitem'>
                    <Link to='/books'>
                        <div>Books</div>
                    </Link>
                </div>
                <div className='menuitem'> | </div>
                <div className='menuitem'>
                    <Link to='/book/add'>
                        <div>Add Book</div>
                    </Link>
                </div>
                <div className='menuitem'> | </div>
                <div className='menuitem'>
                    <Link to='/authors'>
                        <div>Authors</div>
                    </Link>
                </div>
            </div>
        </div>

    );
};

export default Header;