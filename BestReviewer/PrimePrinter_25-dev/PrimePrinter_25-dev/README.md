# Clean Code - PrimePrinter

Observation

- Study PrimePrinterTest class
- create test based on output of program


Activity

- M to numberOfPrimes - introduce field, 
initialize into field declaration
- RR to linesPerPage, introduce field, 
init in field declaration
- CC to columns, introduce field, 
               init in field declaration
- P to primes, introduce field, 
               init in field declaration
- PAGENUMBER to pagenumber, introduce field, 
               init in field declaration
- PAGEOFFSET to pageoffset, introduce field, 
               init in field declaration
- ROWOFSET to rowoffset, introduce field, 
               init in field declaration
- C to column, introduce field, 
               init in field declaration
- J to candidate, introduce field, 
               init in field declaration
- K to primeIndex, introduce field, 
               init in field declaration
- JPRIME to possiblyPrime, introduce field, 
               init in field declaration
- ORD to ord, introduce field, 
               init in field declaration
- SQUARE to square, introduce field, 
               init in field declaration
- N to n, introduce field, 
               init in field declaration
- MULT to multiples, introduce field, 
               init in field declaration

### 실습 #2 코드의 문제점
- 의미를 알기 어려운 변수가 많다.
- 함수의 크기가 크다.
- 하나의 함수에서 여러 가지 일을 처리한다.

### To-Do
- 큰 함수의 일부를 작은 함수로 추출
- 변수와 함수의 응집력을 고려하여 새로운 클래스 생성
- 클래스의 추상화 수준, 가독성 좋게 수정
 
- 소수 목록 생성 부분과 행과 열에 맞춰 페이지 출력하는 부분을 각각의 책임에 맞게 클래스 생성

클래스 강의 내용 참고
