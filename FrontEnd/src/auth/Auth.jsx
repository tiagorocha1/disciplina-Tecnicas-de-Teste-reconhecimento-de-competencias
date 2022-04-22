import React, { Component, useState } from "react";
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import { login, changeEmail } from './authActions'

import './auth.css'



class Auth extends Component {

    constructor(props) {
        super(props)
        this.state = { email: '', password: '' };

        this.handleChangeEmail = this.handleChangeEmail.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
    }

    handleChangeEmail(event) {
        this.setState({ email: event.target.value });
    }

    handleChangePassword(event) {
        this.setState({ password: event.target.value });
    }


    render() {

        const { login } = this.props

        return (
            <div className="teste">

                <form className="form-signin">

                    <div className="text-center mb-4">
                        <img className="logo" src="task-logo.png" alt="" width="68" height="68"/>
                            <h1 className="tracking-in-contract-bck h3 mb-3 font-weight-normal">Trainee Tasks</h1>
                            <p> A system for recording <strong>tasks</strong></p>
                    </div>

                    <div className="text-center mb-8 ">
                        <h1 className="h3 mb-3 font-weight-normal"></h1>
                    </div>


                    <div className="form-label-group">
                        <label >E-mail</label>
                        <input value={this.state.email} onChange={this.handleChangeEmail} id="inputEmail" className="form-control" placeholder="Endereço de email" />
                    </div>

                    <div className="form-label-group">
                        <label >Password</label>
                        <input value={this.state.password} onChange={this.handleChangePassword} type="password" id="inputPassword" className="form-control" placeholder="Password" />
                    </div>

                    <div className="form-label-group mb-8">
                        <button className="btn btn-lg btn-primary btn-block" onClick={() => { login(this.state.email, this.state.password) }} >Entrar</button>
                    </div>
                </form>
            </div>
        )
    }
}

const mapDispatchToProps = dispatch => bindActionCreators({ login }, dispatch)
export default connect(null, mapDispatchToProps)(Auth)
