import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, Navbar, Container, Nav, NavDropdown} from 'react-bootstrap'
import { useState } from 'react';
import data from './data.js';

function App() {

  let [shoes, shoesStateCB] = useState(data);

  return (
    <div>
      <div className="main-bg">
        <img src={process.env.PUBLIC_URL + '/logo192.png'} /> 
      </div>
      <Navbar bg="light" expand="lg">
        <Container>
          <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="#home">Home</Nav.Link>
              <Nav.Link href="#link">Link</Nav.Link>
              <NavDropdown title="Dropdown" id="basic-nav-dropdown">
                <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
                <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
              </NavDropdown>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Button variant="primary">Primary</Button>
      <Button variant="secondary">Secondary</Button>{' '}

      <div className="container">
        <div className="row">
          <Card shoes={shoes}></Card>
          <Card shoes={shoes}></Card>
          <Card shoes={shoes}></Card>
        </div>
      </div> 

    </div>
  );
}

function Card(props) {
  return(
    <div className="col-md-4">            
      <img src="https://codingapple1.github.io/shop/shoes1.jpg" width="80%" />
      <h4>{props.shoes[0].title}</h4>
      <p>{props.shoes[0].content}</p>
    </div>
  )
}

export default App;
