import {toastr} from 'react-redux-toastr'
import Axios from 'axios'
import constants from '../constants'


export function login(email, password){
  
    return submit(email, password,`${constants.API_AUTH_LOGIN}`)
}

export function signup(values){
    return submit(values,`${constants.API_AUTH_SIGNUP}`)
}

function submit(email, password, url){

    localStorage.removeItem(constants.USERKEY)  
    /*localStorage.clear(); */
    return (
        dispatch =>{
            Axios.post(url,{email: `${email}`, password: `${password}`})
                .then( resp => {
                    dispatch([
                        {type: 'USER_FETCHED', payload: resp}
                    ])
                })
                .catch(e => {
                    console.log(e)
                })
        }
    )
}

export function logout(){
    
    return { type: 'LOGOUT',  payload : false}
}

export function validateToken(token){
    return dispatch =>{
        if(token){
            Axios.post(`${constants.API_AUTH_VALIDATE_TOKEN}`, {token})
                .then(resp => {
                    dispatch({ type: 'TOKEN_VALIDATED', payload: resp})
                })
                .catch( e => dispatch ({ type: 'TOKEN_VALIDATED', payload: false}))
        }else{
            dispatch({ type: 'TOKEN_VALIDATED', payload : false})
        }
    }
}


