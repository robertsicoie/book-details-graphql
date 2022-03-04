import React from 'react';
import Author from './Author';

import '../styles/Book.css';

const Book = (props) => {

    const {book} = props;

    return (
        <div className='book'>
            <Author author={book.author} />
            <div className='title'>{book.name}</div> 
            <div>{book.pageCount} pages</div>
        </div>

    );
}
export default Book;