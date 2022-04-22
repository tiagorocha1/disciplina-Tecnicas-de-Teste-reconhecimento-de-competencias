import React, { Component } from "react";
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import Auth from "../auth/Auth";
import { logout } from "../auth/authActions";

import constants from '../constants'
import IconButton from '../template/IconButton' 

class Logout extends Component {
    constructor(props) {
        super(props)
      
    }

    componentWillMount(){
        {this.props.logout()}
    }

 

    render() {

       // localStorage.removeItem(constants.USERKEY)
        //{this.props.logout()}
        
        return (
           <h1>saindo...</h1>
        
        )

    }
}

const mapStateToProps = state => ({user: state.auth.user})
const mapDispatchToProps = dispatch => bindActionCreators({logout}, dispatch)
export default connect(mapStateToProps,mapDispatchToProps)(Logout)