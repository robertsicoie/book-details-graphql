import React from "react";
import { useQuery } from "@apollo/client";

import Book from './Book';
import AddBook from "./AddBook";

import { QUERY_BOOKS } from './constants';

import '../styles/Books.css';

const Books = ({client}) => {
    const {
        data,
        loading,
        error
    } = useQuery(QUERY_BOOKS, {
        fetchPolicy: "network-only"
    });

    return (
        <>
            {loading && <p>Loading...</p>}
            {error &&<pre>{JSON.stringify(error, null, 2)}</pre>}
            <div className="books">
            {data && data.books.map((book) => (
                <Book key={book.id} book={book} />
            ))}
            </div>
        </>
    );
}

export default Books;