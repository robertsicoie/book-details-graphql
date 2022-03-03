import React from 'react';
import AddBook from './AddBook';
import Authors from './Authors';
import Books from './Books';


const Dashboard = () => {
    
    return (
        <>
            <Books/>
            <Authors/>
            <AddBook/>
        </>
    );
};

export default Dashboard;