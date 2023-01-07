import AllUsers from './Component/AllUsers';
import AddUser from './Component/AddUser';
import EditUser from './Component/EditUser';
import NavBar from './Component/NavBar';
import NotFound from './Component/NotFound'; 
import AllResponses from './Component/AllResponses'
import AllStudentInfo from './Component/AllStudentsInfo'
import { BrowserRouter, Route, Routes } from 'react-router-dom';


function App() {
  return (
    <BrowserRouter>
      <NavBar />
      <Routes>

        <Route path="/all" element={<AllUsers />} exact/>
        <Route path="/add" element={<AddUser />} exact/>
        <Route path="/edit/:id" element={<EditUser />} exact/>
        <Route  path="/allresponses" element={<AllResponses/>} exact></Route>
        <Route  path="/allstudentsinfo" element={<AllStudentInfo/>} exact/>
        <Route element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
