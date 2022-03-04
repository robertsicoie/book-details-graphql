import React from 'react';
import { useQuery} from '@apollo/client';
import { useLocation, useNavigate } from 'react-router-dom';

import Author from './Author';

import { QUERY_AUTHORS } from './constants';

import '../styles/Authors.css';



const Authors = ({client}) => {
    const navigate = useNavigate();
    const location = useLocation();

    const {
        data,
        loading,
        error,
    } = useQuery(QUERY_AUTHORS, {
        fetchPolicy: "cache-and-network"
    });

    return (
        <>
        <div className='authors component'>
            {loading && <p>Loading...</p>}
            {error && <pre>{JSON.stringify(error, null, 2)}</pre>}
            {data && data.authors.map((author) => (
                <Author key={author.id} author={author} />
            ))}
        </div>
        </>

    );
};


export default Authors;