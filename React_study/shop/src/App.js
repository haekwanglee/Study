import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import logo from './logo.svg';
import data from './data.js';
import Detail from './routes/Detail.js';
import About from './routes/About.js';

import {useState} from 'react';
import {Button, Navbar, Container, Nav, NavDropdown} from 'react-bootstrap';
import {Routes, Route, Link, useNavigate, Outlet} from 'react-router-dom';

function App() {

  let [shoes, shoesStateCB] = useState(data);

  let navigate = useNavigate();

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

      <button onClick={()=>{ navigate('/') }}>홈</button>
      <button onClick={()=>{ navigate('/detail') }}>디테일</button>
      <button onClick={()=>{ navigate('/about') }}>어바웃</button>
      <button onClick={()=>{ navigate('/about/member') }}>어바웃 멤버</button>
      <button onClick={()=>{ navigate('/about/location') }}>어바웃 위치</button>
      
      <button onClick={()=>{ navigate(-1)}}>뒤로가기</button>
      <button onClick={()=>{ navigate(1)}}>앞으로가기</button>

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
                      <Card shoes={shoes[i]} i={i}/>
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
        
        <Route path="/detail" element={
          <Detail/>
        }/>
        <Route path="/about" element={ <About/> }>  
          <Route path="member" element={ <div>멤버들</div> } />
          <Route path="location" element={ <div>회사위치</div> } />
        </Route>
        <Route path="*" element={ 
          <div>없는페이지임</div> 
        }/>
        
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
