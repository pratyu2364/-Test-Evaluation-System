import { useState, useEffect } from 'react';

import {FormControl,FormGroup, Table, TableHead, TableCell, TableRow, TableBody, Button, styled } from '@mui/material'
import {getAllStudentResponses, getUsers, deleteUser,evaluateAllStudents } from '../Service/api';

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

function AllResponses() {

    const [users, setUsers] = useState([]);
    const navigate=useNavigate();
    useEffect(() => {
        getAllUsers();
    }, []);


    const getAllUsers = async () => {
        let response = await getAllStudentResponses();
       // console.log(response)
        setUsers(response.data);
    }
    const evaluate=async ()=>{
        let response=await evaluateAllStudents();
        console.log("eval all response is ")
        console.log(response)
        navigate('/allstudentsinfo');
    }

  return (
    <>
    <StyledTable>
    <TableHead>
        <THead>
            <TableCell>Student Name</TableCell>
            <TableCell>Question Description</TableCell>
            <TableCell>Correct Ans</TableCell>
            <TableCell>Student's Answer</TableCell>
            <TableCell>Weightage</TableCell>
            <TableCell>Marks obtained</TableCell>
           
        </THead>
    </TableHead>
    <TableBody>
        {users.map((user) => (
            <TRow key={user.id}>
                <TableCell>{user.student.studentName}</TableCell>
                <TableCell>{user.question.question_statement}</TableCell>
                <TableCell>{user.question.question_ans}</TableCell>
                <TableCell>{user.response}</TableCell>
                <TableCell>{user.question.weightage}</TableCell>
                <TableCell>{user.marks_obtained}</TableCell>
            </TRow>
        ))}
    </TableBody>
</StyledTable>
 <FormControl style={{display: 'flex',
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'center',
    alignItems: 'center'}}>
 <Button variant="contained" color="primary" onClick={() => evaluate()}>evaluate</Button>
</FormControl>
</>
  )
}

export default AllResponses