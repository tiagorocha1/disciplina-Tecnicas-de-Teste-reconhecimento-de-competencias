import '../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../../node_modules/font-awesome/css/font-awesome.min.css'
import '../template/custom.css'
import React, { Component } from "react";
import Axios from 'axios'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import constants from '../constants'

import Auth from '../auth/Auth'
import App from './App'

import { validateToken, logout } from "../auth/authActions";
import listAllCategory from '../task/taskActions'

class AuthOrApp extends Component {

    constructor(props) {
        super(props)
    }

    componentWillUnmount() {

        if (this.props.auth.user) {
            this.props.validateToken(this.props.auth.user)
        }
    }

    render() {

        if (this.props.auth.validToken) {
            //Axios.defaults.headers.common['authorization'] = "Bearer "+user.token
            // Axios.defaults.headers.common = {'Authorization': `bearer ${user.token}`}
            // return <App>{this.props.children}</App>           
            return <App> </App>
        } else if (!this.props.auth.validToken) {
            return <Auth />
        }/*else if(!validToken){
            if(this.props.auth.user){           
                if(this.props.validateToken(this.props.auth.user)){
                    console.log('validou o token')
                    return <App/>
                }else{                    
                    console.log('não validou o token')
                    console.log(this.props.auth.user)
                    return false
                }
            }  
        }*/
        else {
            return false
        }
    }
}

const mapStateToProps = state => ({ auth: state.auth })
const mapDispatchToProps = dispatch => bindActionCreators({ validateToken, listAllCategory }, dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(AuthOrApp)