import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {

  let [App글제목, App글제목변경] = useState(['1번글', '2번글', '3번글']);  
  let [좋아요개수, 좋아요개수변경] = useState([0, 0, 0]);
  let [modal, setModal] = useState(false); 
  let [modal글제목Index, modal글제목Index변경] = useState(0);

  function App글제목바꾸기함수(){    
    let copy = [...App글제목];
    copy[0] = "남자코트추천";
    App글제목변경(copy); 
  }

  function App글제목정렬하기함수(){
    let newArray = [...App글제목];
    newArray = newArray.sort();
    App글제목변경(newArray);
  }

  function 모달창상태변경함수(index){
    modal글제목Index변경(index);
    setModal(!modal);    
  }

  function 좋아요변경함수(i){
    let copy = [...좋아요개수];
    copy[i] = copy[i]+1;
    좋아요개수변경(copy);
  }

  return (
    <div className="App">
      <div className="black-nav">
        <h4>블로그임~~!</h4>
      </div>      
      <h4> <button onClick={ App글제목바꾸기함수 }>App글제목 바꾸기 테스트버튼</button> </h4>
      <h4> <button onClick={ App글제목정렬하기함수 }>App글제목 정렬하기 테스트버튼</button> </h4>
      <h4> <button onClick={ ()=>{모달창상태변경함수(0)} }>모달창 상태변경 테스트버튼</button> </h4>
      {
        App글제목.map(function(글제목,i){
          return (
            <div className="list" key={i}>
              <h4 onClick={()=>{모달창상태변경함수(i)}}>
                { App글제목[i] } 
                <button onClick={()=>{ 
                  좋아요변경함수(i);
                }}>👍</button> 
                { 좋아요개수[i] } 
              </h4>
              <p>4월 25일 발행</p>
            </div>
          )
        })
      }
      {
        modal == true ? 
        <Modal func={App글제목바꾸기함수} color={'skyblue'} title={App글제목} index={modal글제목Index}>          
        </Modal> : null
      }      
    </div>
  );
}

function Modal(props) {
  return (    
    <div className="modal" style={{background : props.color}}>
      <h4>{props.title[props.index]}</h4>
      <p>날짜</p>
      <p>상세내용</p>
      <button onClick={props.func}>글수정</button>
    </div>
  )
}

export default App;
