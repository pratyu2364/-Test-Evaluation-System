import { useState, useEffect } from 'react';

import { FormGroup, FormControl, InputLabel, Input, Button, styled, Typography } from '@mui/material';
import { useNavigate, useParams } from 'react-router-dom';
import { getUsers, editUser } from '../Service/api';


const initialValue = {
    question_statement: '',
    question_ans: '',
    weightage:''
}
const Container = styled(FormGroup)`
    width: 50%;
    margin: 5% 0 0 25%;
    & > div {
        margin-top: 20px}
`;


const EditUser = () => {
    const [user, setUser] = useState(initialValue);
    const { question_statement,question_ans,weightage} = user;
    const { id } = useParams();
    
    let navigate = useNavigate();

    useEffect(() => {
        loadUserDetails();
    }, []);

    const loadUserDetails = async() => {
        const response = await getUsers(id);
        setUser(response.data);
    }

    const editUserDetails = async() => {
        const data={
            "id":id,
            "question_statement":user.question_statement,
            "question_ans":user.question_ans,
            "weightage":user.weightage
        }
        const response = await editUser(data);
        navigate('/all');
    }

    const onValueChange = (e) => {

        setUser({...user, [e.target.name]: e.target.value})
    }

    return (
        <Container>
            <Typography variant="h4">Edit Question</Typography>
            <FormControl>
                <InputLabel htmlFor="my-input">Question Description</InputLabel>
                <Input onChange={(e) => onValueChange(e)} name='question_statement' value={question_statement} id="my-input" />
            </FormControl>
            <FormControl>
                <InputLabel htmlFor="my-input">Question Answer</InputLabel>
                <Input onChange={(e) => onValueChange(e)} name='question_ans' value={question_ans} id="my-input" />
            </FormControl>
            <FormControl>
                <InputLabel htmlFor="my-input">weightage</InputLabel>
                <Input onChange={(e) => onValueChange(e)} name='weightage' value={weightage} id="my-input" />
            </FormControl>

            <FormControl>
                <Button variant="contained" color="primary" onClick={() => editUserDetails ()}>Update Question</Button>
            </FormControl>
        </Container>
    )
}

export default EditUser;