import axios from 'axios';

const usersUrl = 'http://localhost:9091/questions/';

const responsesUrl='http://localhost:9091/responses/'

const evaluateUrl='http://localhost:9091/evaluate/'

const studentUrl='http://localhost:9091/students/'
export const getUsers = async (id) => {
    id = id || '';
    try {
        return await axios.get(`${usersUrl}list/`);
    } catch (error) {
        console.log('Error while calling getUsers api ', error);
    }
}

export const addUser = async (user) => {
    return await axios.post(`${usersUrl}`, user);
}

export const deleteUser = async (id) => {
    return await axios.delete(`${usersUrl}delete/${id}`);
}

export const editUser = async (data) => {
    
    return await axios.put(`${usersUrl}update/`, data)
}

export const getAllStudentResponses=async()=>{
    return await axios.get(`${responsesUrl}list/`)
}

export const evaluateAllStudents=async()=>{
    return await axios.get(`${evaluateUrl}`)
}

export const getStudentInfo=async()=>{
    return await axios.get(`${studentUrl}list/`);
}