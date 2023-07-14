import {createStore, applyMiddleware, compose} from "redux"
import thunk from "redux-thunk"
import rootReducer from "./reducers"

const initialState = {}
const middleware = [thunk]
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose

let store

if (window.navigator.userAgent.includes("Chrome")) {
    store = createStore(rootReducer, composeEnhancers(applyMiddleware(...middleware)));
      
} else {
    store = createStore(rootReducer, initialState, compose(applyMiddleware(...middleware)))
}

export default store;