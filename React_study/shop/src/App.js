import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, Navbar, Container, Nav, NavDropdown} from 'react-bootstrap'
import { useState } from 'react';
import data from './data.js';
import { Routes, Route, Link } from 'react-router-dom'

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

      <Link to="/"> 홈 </Link>
      <Link to="/detail"> 디테일 </Link>
      <Link to="/about"> 어바웃 </Link>

      <Routes>
        <Route path="/" element={
          <div>

            <Button variant="primary">Primary</Button>
            <Button variant="secondary">Secondary</Button>{' '}
            <div className="container">
              <div className="row">
                {
                  shoes.map(function(a,i){
                    return (
                      <Card shoes={shoes[i]} i={i}></Card>
                    )
                  })
                  // shoes.map((a,i)=>{
                  //   return (
                  //     <Card shoes={shoes[i]} i={i}></Card>
                  //   )
                  // })
                }
              </div>
            </div> 

          </div> 
        }/>
        <Route path="/about" element={ <div>어바웃페이지임</div> } />
        <Route path="/detail" element={ <div>디테일페이지임</div> } />
      </Routes>

    </div>
    
  );
}

function Card(props) {
  return(
    <div className="col-md-4">
      <p>{props.i}</p>                
      <img src={props.shoes.imgpath} width="80%" />
      <h4>{props.shoes.title}</h4>
      <p>{props.shoes.content}</p>
    </div>
  )
}

export default App;
