import constants from '../constants'
const userKey =   constants.USERKEY//'D%t@woWZNPhTu*RNsjTA'

const INITIAL_STATE = {
    user: JSON.parse(localStorage.getItem(constants.USERKEY)),
    validToken: false
   
}


export default (state = INITIAL_STATE, action) => {

    switch (action.type) {

        case 'TOKEN_VALIDATED':
            if (action.payload) {
                
                return { ...state, validToken: true }
            } else {
                
                //localStorage.removeItem(userKey)    
                //localStorage.clear()            
                return { ...state, validToken: false, user: '' }
            }
        case 'USER_FETCHED':
           
            localStorage.setItem(userKey, JSON.stringify(action.payload.data.user))
            
            state.user = action.payload.data.user
            state.validToken = true
           
            return { ...state, user: action.payload.data.user, validToken: action.payload.data.validToken }

        case  'LOGOUT':
            state.user = ''
            state.validToken = false
            //localStorage.removeItem(userKey)  
            //localStorage.clear();
            return { ...state, user: '', validToken: false }
        default:
            return state
    }
}