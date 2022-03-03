import React from 'react';

import '../styles/Author.css'

const Author = (props) => {
    const {author} = props;

    return ( author ?
        <span className='author'>
            <span>{author.firstName} {author.lastName}</span>
        </span>
        : 'unknown'
    );


};

export default Author;