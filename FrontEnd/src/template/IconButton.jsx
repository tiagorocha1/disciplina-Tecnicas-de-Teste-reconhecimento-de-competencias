import React from 'react'
import If from './If'

const IconButton = props => (
    <If test={!props.hide}>

        <button id={props.id} className={'btn btn-' + props.btnStyle} onClick={props.onClick}>
            <i className={'fa fa-' + props.icon}></i>
        </button>
        
    </If>
)

export default IconButton