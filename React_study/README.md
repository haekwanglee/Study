### init
npx create-react-app blog  
이거 그대로 입력하고 엔터누르면 프로젝트 생성 끝입니다.  
src 폴더 안에 있는 App.js 이게 메인페이지니까 거기다가 코드짜면 됩니다.   

### state  
1. 클릭시 뭔가 실행하고 싶으면 onClick={함수} 이렇게 이벤트 핸들러를 달아서 사용합니다.
2. state를 변경하시려면 state 변경함수를 꼭 이용하십시오.

### array/object state
리액트에서 array/object state를 수정하고 싶으면  
독립적인 카피본을 만들어서 수정하는게 좋습니다.  
[...기존state]  
{...기존state}  
이렇게 하면 독립적인 카피가 하나 생성됩니다.  
array/object 쓸때 let 타입변수는 포인터 느낌?

### props
자식 컴포넌트가 부모 컴포넌트에 있던 state를 쓰고 싶으면  
그냥 쓰면 안되고 props로 전송해서 써야합니다.  

### useState  
UI를 각각 상태에 대해 마련해놓고 이벤트 변경에 따라 스위치 역할을 하는  
useState의 상태변경 func를 통해 UI상태를 변경시켜줌.  

