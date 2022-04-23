## 다수 Record 처리를 위한 성능 개선

### _**요구사항/배경**_

- 10만 개 이상의 데이터 Record를 처리 (ADD, DEL, MOD) 할 수 있어야 한다.
- Java.* 라이브러리만 사용

![요구사항문서](/image/사례3-요구사항.png)


### _**활동 내용**_

### 1. 10만개의 데이터를 읽고 커맨드를 처리하는 Test Case 작성

  - [PR: Load Test 추가](https://github.ecodesamsung.com/Best-Reviewer-3-11/TeamProject_Best/pull/50) (https://github.ecodesamsung.com/Best-Reviewer-3-11/TeamProject_Best/pull/50)
    ![PR](/image/사례3-활동1.png)

### 2. 문제점 발견: DEL, MOD 연산 할 때 너무 느림

  - 10만개 추가하고 전부 DEL 하면 3분 이상 걸림
  
    ![대화1](/image/사례3-활동2.png)
    ![대화2](/image/사례3-활동3.png)
    ![대화3](/image/사례3-활동4.png)

### 3. [Refactoring #1] DEL 연산시 Employee 비교에 HashSet 적용

  - DEL, MOD 동작은 search 결과를 가지고 List.removeAll 하거나 List.removeAll -> List.addAll 하도록 구현됨 (record 당 O(N))
  - HashSet 사용하면 contains 연산을 O(1)에 가능하기 때문에 remove 연산이 O(1)으로 처리 가능
  
    ![코드1](/image/사례3-활동5.png) 
  - [PR: List > HashSet으로 변경](https://github.ecodesamsung.com/Best-Reviewer-3-11/TeamProject_Best/pull/52) (https://github.ecodesamsung.com/Best-Reviewer-3-11/TeamProject_Best/pull/52)
  - 결과: 3분↑ 걸리던 것이 3.88초로 단축됨 (약 50배 빨라짐!)
    
### 4. [Refactoring #2] SCH/MOD 연산의 stream filter에 parallel 적용
  
  - SCH, MOD 연산은 stream filter를 사용하고 각각의 Record에 독립적이므로 parallel을 적용
  
    ![코드2](/image/사례3-활동6.png) 
  
  - [PR: parallel 적용](https://github.ecodesamsung.com/Best-Reviewer-3-11/TeamProject_Best/pull/54) (https://github.ecodesamsung.com/Best-Reviewer-3-11/TeamProject_Best/pull/54)
  - 결과: 동일 TC 기준 3.88초에서 2.59초로 단축됨


### _**효과**_

- 대량의 Record를 처리 할 때, (1) Hash 사용, (2) Parallel 사용으로 속도를 3분 -> 3초 이내로 단축할 수 있었다.
