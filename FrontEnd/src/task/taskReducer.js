const INITIAL_STATE = { 
    description: '', 
    list: [],
    selectedCategoryID: '1' , 
    listCategory: ["teste","teste2"]
}

const taskReducer = (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case 'DESCRIPTION_CHANGED':
            console.log(action.payload)
            return { ...state, description: action.payload }
        case 'TASK_SEARCHED':            
            return { ...state, list: action.payload }
        case 'TASK_CLEAR':
            return { ...state, description: '' }
        case 'CATEGORY_CHANGED':
            console.log(action.payload )
            return {...state, selectedCategoryID: action.payload }
         
        default:
            return state
    }
}

export default taskReducer