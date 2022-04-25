###init
npx create-react-app blog  
이거 그대로 입력하고 엔터누르면 프로젝트 생성 끝입니다.  
src 폴더 안에 있는 App.js 이게 메인페이지니까 거기다가 코드짜면 됩니다.   

###state  
1. 클릭시 뭔가 실행하고 싶으면 onClick={함수} 이렇게 이벤트 핸들러를 달아서 사용합니다.
2. state를 변경하시려면 state 변경함수를 꼭 이용하십시오.

###array/object state
리액트에서 array/object state를 수정하고 싶으면  
독립적인 카피본을 만들어서 수정하는게 좋습니다.  
[...기존state]  
{...기존state}  
이렇게 하면 독립적인 카피가 하나 생성됩니다.  

