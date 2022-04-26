import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {

  let [modal, setModal] = useState(false);  
  
  let post = '테스트';
  
  let [글제목, 글제목변경] = useState(['4번글', '2번글', '3번글']);  
  let [좋아요개수, 좋아요개수변경] = useState(0);

  function 글제목바꾸기함수(){    
    let copy = [...글제목];
    copy[0] = "여자코트추천";
    글제목변경(copy); 
  }

  function 글제목정렬하기함수(){
    let newArray = [...글제목];
    newArray = newArray.sort();
    글제목변경(newArray);
  }

  function 모달창상태변경함수(){
    setModal(!modal);
  }

  return (
    <div className="App">
      <div className="black-nav">
        <h4>블로그임~~!</h4>
      </div>      
      <h4>{ post }</h4>
      <h4> <button onClick={ 글제목바꾸기함수 }>글제목 바꾸기 테스트버튼</button> </h4>
      <h4> <button onClick={ 글제목정렬하기함수 }>글제목 정렬하기 테스트버튼</button> </h4>
      <h4> <button onClick={ 모달창상태변경함수 }>모달창 상태변경 테스트버튼</button> </h4>
      <div className="list">
        <h4>{ 글제목[0] } <span onClick={ ()=>{ 좋아요개수변경(좋아요개수+1) } }>👍</span> { 좋아요개수 } </h4>
        <p>4월 25일 발행</p>
      </div>
      <div className="list">
        <h4>{ 글제목[1] }</h4>
        <p>4월 26일 발행</p>
      </div>
      <div className="list">
        <h4>{ 글제목[2] }</h4>
        <p>4월 27일 발행</p>
      </div>
      {
        modal == true ? <Modal></Modal> : null
      }      
    </div>
  );
}

function Modal() {
  return (    
    <div className="modal">
      <h4>제목</h4>
      <p>날짜</p>
      <p>상세내용</p>
    </div>
  )
}

export default App;
