import React, { Component } from "react";
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Grid from '../template/Grid'
import IconButton from '../template/IconButton'
import { add, changeDescription, search, clear, listAllCategory, changeCategory } from './taskActions'


class TaskForm extends Component {
    constructor(props) {
        super(props)
        
        this.keyHandler = this.keyHandler.bind(this)
        this.renderOptions = this.renderOptions.bind(this)
        
    }

    componentDidMount() {
        this.props.search()
    }

    keyHandler(e) {
        const { add, search, description, clear, selectedCategoryID } = this.props
        console.log(selectedCategoryID)
        if (e.key === 'Enter') {
            e.shiftKey ? search() : add(description,selectedCategoryID)
        } else if (e.key === 'Escape') {
            clear()
        }
    }

    renderOptions() {

        const { listAllCategory } = this.props




        return this.props.listCategory.forEach(category => {
            <option value={category.categoryId}>{category.name}</option>
        });
    }

    render() {
        const { add, search, description,selectedCategoryID ,listCategory} = this.props
        console.log("listCategory: "+listCategory)
        console.log("categoryId:"+selectedCategoryID)
        
        return (
            <div role='form' className='taskForm'>
                <Grid cols='9 9 7'>
                    <input id='description' className='form-control' placeholder='Adicione uma tarefa'
                        onChange={this.props.changeDescription}
                        onKeyUp={this.keyHandler}
                        value={this.props.description}></input>



                </Grid>

                <Grid cols='3 3 3'>
                    <select id='selectedCategoryID' className="form-control" value={this.props.selectedCategoryID} onChange={this.props.changeCategory}>
                        <option value={1}>USER SUPPORT</option>
                        <option value={2}>LABORATORY SUPPORT</option>
                        <option value={3}>DIGITAL CONTENT CREATION</option>
                        <option value={4}>PC MAINTENANCE</option>
                        <option value={5}>NETWORK MAINTENANCE</option>
                    </select>
                </Grid>

                <Grid cols='12 3 2'>

                    <IconButton btnStyle='primary' icon='plus' onClick={() => add(description, selectedCategoryID)}></IconButton>
                    <IconButton btnStyle='info' icon='search' onClick={search} />
                    <IconButton btnStyle='default' icon='close' onClick={this.props.clear} />
                </Grid>
            </div>
        )
    }
}




const mapStateToProps = state => ({ description: state.task.description, selectedCategoryID: state.task.selectedCategoryID, listCategory: state.task.listCategory })
const mapDispatchToProps = dispatch =>
    bindActionCreators({ add, changeDescription, search, clear, listAllCategory, changeCategory }, dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(TaskForm)
