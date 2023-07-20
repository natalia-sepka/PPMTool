import { combineReducers } from "redux"
import errorReducer from "./errorReducer"
import projectsReducer from "./projectsReducer"
import backlogReducer from "./backlogReducer"

export default combineReducers ({
    errors: errorReducer,
    project: projectsReducer,
    backlog: backlogReducer
})