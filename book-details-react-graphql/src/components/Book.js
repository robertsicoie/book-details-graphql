import React from 'react';
import Author from './Author';

import '../styles/Book.css';

const Book = (props) => {

    const {book} = props;

    return (
        <div className='book'>
            <div className='title'>{book.name}</div> <Author author={book.author} />
            <div>{book.pageCount} pages</div>
        </div>

    );
}
export default Book;