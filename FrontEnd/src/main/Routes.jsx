import React from 'react'
/*import { Route, Redirect } from 'react-router'
import {HashRouter} from 'react-router-dom'
**/

import {Router, Route, Redirect, hashHistory} from 'react-router'
import Task from '../task/Task'
import About from '../about/About'
import Logout from '../template/Logout'
import Auth from '../auth/Auth'
import AuthOrApp from './AuthOrApp'


const Routes = props => (
    <Router history={hashHistory}>
                            
        <Route path='/tasks' component={Task} /> 
        <Route path='/about' component={About} /> 
        <Route path='/Logout' component={Logout} /> 
        
        
        
        <Redirect from='*' to='/tasks' />
        
    </Router>
)

export default Routes