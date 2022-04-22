import React from 'react'
import IconButton from '../template/IconButton'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import { markAsDone, markAsPending,remove } from './taskActions'

const TaskList = props => {
    const renderRows = () => {
        const list = props.list || []                     
        return list.map(task => (
            <tr key={task.id} className='focus-in-expand-fwd'>                             
          
                <td className={task.done ? 'markedAsDone col-lg-7 col-md-6 col-sm-5 col-xs-6' : 'col-lg-7 col-md-6 col-sm-5 col-xs-6'} >{task.description}</td>            
                <td className='col-lg-3 col-md-2 col-sm-1 col-xs-0' >{task.categoryName}</td>  
                <td className='col-lg-2 col-md-1 col-sm-1 col-xs-1'>
                    
                    <IconButton btnStyle='success' icon='check' hide={task.done} onClick={() => props.markAsDone(task)} />
                    <IconButton btnStyle='warning' icon='undo' hide={!task.done} onClick={() => props.markAsPending(task)} />
                    <IconButton btnStyle='danger' icon='trash-o'   onClick={() => props.remove(task)}/>
                </td>
            </tr>
        )) 
    }
    return (
        <table className='table table-striped'>
            <thead >
                <tr>                    
                    <th>Description</th>
                    <th>Category</th>
                    <th className='tableActions'>Actions</th>
                </tr>
            </thead>
            <tbody>
                {renderRows()}
            </tbody>
        </table>
    )
}

const mapStateToProps = state => ({list: state.task.list})
const mapDispatchToProps = dispatch =>  bindActionCreators({ markAsDone, markAsPending,remove }, dispatch)
    export default connect(mapStateToProps, mapDispatchToProps)(TaskList)


