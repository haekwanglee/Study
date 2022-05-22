import { Outlet } from "react-router"

function About(props) {
    return(
      <div className="container">
        <div className="row">
          <div className="col-md-6">
            <img src="https://codingapple1.github.io/shop/shoes2.jpg" width="100%" />
          </div>
          <div className="col-md-6">
            <h4 className="pt-5">어바웃</h4>
          </div>
        </div>
        <Outlet/>
      </div> 
    )
  }

  export default About