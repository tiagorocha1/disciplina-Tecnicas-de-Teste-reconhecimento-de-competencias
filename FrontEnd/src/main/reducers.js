import { combineReducers } from 'redux'
import TaskReducer from '../task/taskReducer'
import AuthReducer from '../auth/authReducer'

const rootReducer = combineReducers({ 
    task: TaskReducer,
    auth: AuthReducer

})

export default rootReducer