import '../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../../node_modules/font-awesome/css/font-awesome.min.css'
import '../template/custom.css'

import React from 'react'
import Menu from '../template/Menu'
import Routes from './Routes'


/*export default function App  (props) { */

export default props => {
    return (
        <div className="container">            
            <Menu />
            <Routes />
        </div>
    )
}


 