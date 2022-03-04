import '../styles/App.css';
import { Navigate, Route, Routes } from 'react-router-dom';

import Header from './Header';
import Books from './Books';
import Authors from './Authors';
import AddBooks from './AddBook';
import Dashboard from './Dashboard';


function App() {
  return (
    <div className="App">
      <Header/>
      <Routes>
          <Route path="/" element={<Dashboard/>} />
          <Route path="/books" element={<Books/>} />
          <Route path="/book/add" element={<AddBooks/>} />
          <Route path="/authors" element={<Authors/>} />
        </Routes>
    </div>
  );
}

export default App;
