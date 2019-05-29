import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { Route, Link, BrowserRouter as Router, Switch } from 'react-router-dom'
import BookList from './pages/books/BookList';
import RegistrationForm from './pages/account/RegistrationForm';
import AnnouncePublishForm from './pages/books/AnnouncePublishForm';
import Notfound from './pages/common/NotFound';

class App extends Component {

  constructor() {
    super();
    console.log("[App] constructor");
}

componentDidMount() {
  console.log("[App] componentDidMount"); 
}

componentWillUnmount() {
  console.log("[App] componentWillUnmount");
}

render() {
  console.log("[App] render");

  return (
   <Router>
      <div className="App">
        <div>
          <Link to="/general">BookList</Link>
          <br></br>
          <Link to="/register">Register</Link>
          <br></br>
          <Link to="/publish">Publish book</Link>
        </div>
        <Switch>
          <Route exact path="/" component={BookList} />
          <Route path="/general" component={BookList} />
          <Route path="/publish" component={AnnouncePublishForm} />
          <Route path="/register" component={RegistrationForm} />
          <Route component={Notfound} />          
        </Switch>
      </div>
      </Router>
    );
}
}

export default App;