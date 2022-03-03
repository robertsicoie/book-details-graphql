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
        data,
        loading,
        error,
    } = useQuery(QUERY_AUTHORS, {
        fetchPolicy: "cache-and-network"
    });

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
                        pageCount: e.target.value
                    })}
                    type='text'
                    placeholder='Number of pages' />
                </label>
                {/* <input 
                    value={formState.authorId}
                    onChange={(e) => setFormState({
                        ...formState,
                        authorId: e.target.value
                    })}
                    type='text'
                    placeholder='Author id' /> */}
                
                {console.log('here' + JSON.stringify(data, null, 2))}
                <label>Author
                    <select> 
                    {data && data.authors.map((author) => {
                        <option name={author.id}>{author.firstname} {author.lastName}</option>
                    })}
                    <option name='1'>aha</option>
                    </select>
                </label>
                <button type='submit'>Submit</button>
            </form>
        </div>
    )
};

export default AddBook;