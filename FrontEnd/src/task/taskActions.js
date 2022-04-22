import Axios from 'axios'
import constants from '../constants'


//let token = 'eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBcGkgZG8gc2lzdGVtYSBFc3RhZ2lhcmlvcyIsInN1YiI6IjEiLCJpYXQiOjE2NDk2MjA4MTksImV4cCI6MTY1ODI2MDgxOX0.qS8xrQChqw1YCh9pTivgDxqh2ibQVgxNQMsYSwPRi3c'







export const changeDescription = event => ({
    type: 'DESCRIPTION_CHANGED',
    payload: event.target.value
})

export const changeCategory = event => ({
    type: 'CATEGORY_CHANGED',
    payload: event.target.value
})



export const listAllCategory = () => {
    const instance = Axios.create({
        baseURL: 'http://localhost:8080/api/',
        timeout: 10000,
        headers: { 'Authorization': 'Bearer ' +  JSON.parse(localStorage.getItem(constants.USERKEY)) }
    });
 
    return (dispatch, getState) => {
        
        
        const request = instance.get(`${constants.API_URL}tasks/category`)
            .then(resp => dispatch({type: 'CATEGORY_LIST', payload: resp.data}))
    }

    //const request = instance.get(`tasks/?page=0&size=10&sort=id,asc&search=`)
}




export const search = () => {

    const instance = Axios.create({
        baseURL: 'http://localhost:8080/api/',
        timeout: 10000,
        headers: { 'Authorization': 'Bearer ' +  JSON.parse(localStorage.getItem(constants.USERKEY)) }
    });

    console.log('search : ')
 
    return (dispatch, getState) => {
        const description = getState().task.description     
        //const request = instance.get(`${constants.API_URL}tasks/?search=`+description)
        const request = instance.get(`${constants.API_URL}tasks/?search=`+description)
            .then(resp => dispatch({type: 'TASK_SEARCHED', payload: resp.data}))
    }

    //const request = instance.get(`tasks/?page=0&size=10&sort=id,asc&search=`)
}


export const add = (description,categoryID) => {

    
    const instance = Axios.create({
        baseURL: 'http://localhost:8080/api/',
        timeout: 10000,
        headers: { 'Authorization': 'Bearer ' +  JSON.parse(localStorage.getItem(constants.USERKEY)) }
    });

    return dispatch => {
        instance.post(`${constants.API_URL}tasks`, { description: `${description}`, categoryID :`${categoryID}` , done: false })
            .then(resp => dispatch(clear()))
            .then(resp => dispatch(search()))
    }
}

export const markAsDone = (task) => {

    const instance = Axios.create({
        baseURL: 'http://localhost:8080/api/',
        timeout: 10000,
        headers: { 'Authorization': 'Bearer ' +  JSON.parse(localStorage.getItem(constants.USERKEY)) }
    });

    return dispatch => {
        instance.put(`${constants.API_URL}tasks/${task.id}`, { ...task, done: true })
            .then(resp => dispatch(search()))
    }
}

export const markAsPending = (task) => {

    const instance = Axios.create({
        baseURL: 'http://localhost:8080/api/',
        timeout: 10000,
        headers: { 'Authorization': 'Bearer ' +  JSON.parse(localStorage.getItem(constants.USERKEY)) }
    });

    return dispatch => {
        instance.put(`${constants.API_URL}tasks/${task.id}`, { ...task, done: false })
            .then(resp => dispatch(search()))
    }
}

export const remove = (task) => {

    const instance = Axios.create({
        baseURL: 'http://localhost:8080/api/',
        timeout: 10000,
        headers: { 'Authorization': 'Bearer ' +  JSON.parse(localStorage.getItem(constants.USERKEY)) }
    });
    
    return dispatch => {
        instance.delete(`${constants.API_URL}tasks/${task.id}`)
            .then(resp => dispatch(search()))
    }
}

export const clear = () => {
    return [{ type: 'TASK_CLEAR' },search()]
}