import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import { logout } from '../auth/authActions'



class Menu extends Component {
    constructor(props) {
        super(props)


    }




    render() {


        return (
            <nav className="navbar navbar-inverse bg-inverse">
                <div className="container">
                    <div className="navbar-header">
                        <a className="navbar-brand" href="#">
                            <i className="fa fa-calendar-check-o"></i> Trainee Tasks
                        </a>
                    </div>

                    <div id="navbar" className="navbar-collapse collapse">
                        <ul className="nav navbar-nav">
                            <li><a href="#/todos">Tasks</a></li>
                            <li><a href="#/about">About</a></li>
                        </ul>

                        <ul className="nav navbar-nav navbar-right">
                            <li><a href="#/logout" className="logout">Logout</a></li>
                        </ul>

                    </div>

                </div>


            </nav>

        )
    }
}


const mapStateToProps = state => ({ auth: state.auth })
const mapDispatchToProps = dispatch => bindActionCreators({ logout }, dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(Menu)
