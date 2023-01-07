import react, { useState } from 'react';
import { FormGroup, FormControl, InputLabel, Input, Button, styled, Typography } from '@mui/material';
import { addUser } from '../Service/api';
import { useNavigate } from 'react-router-dom';

const initialValue = {
    question_statement: '',
    question_ans: '',
    weightage:''
}

const Container = styled(FormGroup)`
    width: 50%;
    margin: 5% 0 0 25%;
    & > div {
        margin-top: 20px
    }
`;

const AddUser = () => {
    const [user, setUser] = useState(initialValue);
    const { question_statement,question_ans,weightage} = user;
    let navigate = useNavigate();

    const onValueChange = (e) => {
        setUser({...user, [e.target.name]: e.target.value})
    }

    const addUserDetails = async() => {
        console.log(user)
        await addUser(user);
        navigate('/all');
    }

    return (
        <Container>
            <Typography variant="h4">Add Question</Typography>
            <FormControl>
                <InputLabel htmlFor="my-input">Question Description</InputLabel>
                <Input onChange={(e) => onValueChange(e)} name='question_statement' value={question_statement} id="my-input" />
            </FormControl>
            <FormControl>
                <InputLabel htmlFor="my-input">Question Answer</InputLabel>
                <Input onChange={(e) => onValueChange(e)} name='question_ans' value={question_ans} id="my-input" />
            </FormControl>
            <FormControl>
                <InputLabel htmlFor="my-input">Weightage</InputLabel>
                <Input onChange={(e) => onValueChange(e)} name='weightage' value={weightage} id="my-input" />
            </FormControl>

            <FormControl>
                <Button variant="contained" color="primary" onClick={() => addUserDetails()}>Add Question</Button>
            </FormControl>
        </Container>
    )
}

export default AddUser;