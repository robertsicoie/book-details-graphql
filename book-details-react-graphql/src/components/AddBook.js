import React, { useState } from 'react';
import { useMutation, useQuery } from '@apollo/client';
import { useNavigate } from 'react-router-dom';

import { ADD_BOOK, QUERY_AUTHORS } from './constants';

import '../styles/AddBook.css';



const AddBook = ({client}) => {
    const navigate = useNavigate();

    const [formState, setFormState] = useState({
        name: '',
        pageCount: 0,
        authorId: ''
    });

    const [addBook] = useMutation(ADD_BOOK, {
        variables : {
            name: formState.name,
            pageCount: formState.pageCount,
            authorId: formState.authorId
        },
        update: (cache, {data: {addBook}}) => {
            console.log('sending ');
        },
        onCompleted: () => {
            navigate('/books');
        }

    });

    const {
        loading,
        data,
        error,
        refetch
    } = useQuery(QUERY_AUTHORS, {
        fetchPolicy: "cache-and-network"
    });

    if (loading) return null;
    {error && <pre>{JSON.stringify(error, null, 2)}</pre>}

    return (
        <div className='add-book component'>
            {loading && <p>Loading...</p>}
            {error &&<pre>{JSON.stringify(error, null, 2)}</pre>}
            <form onSubmit={(e) => {
                e.preventDefault();
                addBook();
            }}>
                <label>Title
                <input
                    id='title'
                    value={formState.name}
                    onChange={(e) => setFormState({
                        ...formState,
                        name: e.target.value
                    })}
                    type='text'
                    placeholder='Book title' />
                </label>
                <label>Page count
                <input 
                    id='pageCount'
                    value={formState.pageCount}
                    onChange={(e) => setFormState({
                        ...formState,
                        pageCount: parseInt(e.target.value)
                    })}
                    type='text'
                    placeholder='Number of pages' />
                </label>
                <label>Author
                    <select 
                        value={formState.authorId}
                        onChange={(e) => setFormState({
                            ...formState,
                            authorId: e.target.value
                        })}> 
                    {data.authors.map((author) => (
                        <option value={author.id} name={author.id}>{author.firstName} {author.lastName}</option>
                    ))}
                        <option name='other'>Other... (todo)</option>
                    </select>
                </label>
                <button type='submit'>Submit</button>
            </form>
        </div>
    )
};

export default AddBook;