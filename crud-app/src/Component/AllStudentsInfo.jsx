import { useState, useEffect } from 'react';

import { Table, TableHead, TableCell, TableRow, TableBody, Button, styled } from '@mui/material'
import { getUsers, deleteUser, getStudentInfo} from '../Service/api';
import { Link, useNavigate } from 'react-router-dom';



const StyledTable = styled(Table)`
    width: 90%;
    margin: 50px 0 0 50px;
`;

const THead = styled(TableRow)`
    & > th {
        font-size: 20px;
        background: #000000;
        color: #FFFFFF;
    }
`;

const TRow = styled(TableRow)`
    & > td{
        font-size: 18px
    }
`;

function AllStudentsInfo() {

    const [users, setUsers] = useState([]);
    const navigate=useNavigate();
    useEffect(() => {
        getAllUsers();
    }, []);


    const getAllUsers = async () => {
        let response = await getStudentInfo();
        console.log(response)
        setUsers(response.data);
    }


  return (
    <StyledTable>
    <TableHead>
        <THead>
            <TableCell>Student Id</TableCell>
            <TableCell>Student Name</TableCell>
            <TableCell>Marks obtained</TableCell>
            
        </THead>
    </TableHead>
    <TableBody>
        {users.map((user) => (
            <TRow key={user.id}>
                <TableCell>{user.id}</TableCell>
                <TableCell>{user.studentName}</TableCell>
                <TableCell>{user.marks_obtained}</TableCell>
                
            </TRow>
        ))}
    </TableBody>
</StyledTable>
  )
}

export default AllStudentsInfo