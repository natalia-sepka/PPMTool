import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/Project/UpdateProject";

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            <Route exact path="/dashboard" component={ props => <Dashboard {...props} />} />
            <Route exact path="/addProject" component={ props => <AddProject {...props} /> } />
            <Route exact path="/updateProject/:id" component={ props => <UpdateProject {...props} /> } />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
