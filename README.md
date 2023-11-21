# 🫱🏼‍🫲🏽 withfriend

## 개요
- 개발 인원 : 개인 프로젝트
- 진행 기간 : 23.08.17 - 진행 중
<br>

## 서비스 요약
- 카카오 친구들과 신뢰를 바탕으로 거래를 하도록 온라인 플리마켓 서비스를 제공하는 웹 플랫폼입니다
- 기존 중고 거래 서비스에서 익명의 사용자로부터 오는 불안감을 해소하는 것이 서비스의 목적입니다
- 소셜 로그인을 통해 서비스를 이용할 때, 사용자가 매번 회원가입을 해야하는 불편함을 해결했습니다
- OAuth를 통해 사용자의 인증, 인가를 설계하고, 사용자의 정보에 접근하였습니다.
- 사용자 간 거래를 구현하기 위해 [결제 요청 → 서비스 point 전환 → 상품 거래 → point 반환] 의 방식을 설계하였습니다.
- api를 RESTful하게 설계하였습니다.
<br>

## 프로젝트 회고
withfriend 프로젝트의 [회고, 개발 이슈, 서비스 흐름도](https://foreveryoung97.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/withfriend)를 남긴 글들을 모아 놓았습니다.
<br>

## 프로젝트 아키텍쳐
[프로젝트 아키텍쳐 구조에 관한 회고입니다.](https://foreveryoung97.tistory.com/109)
<img src="https://github.com/Jiggy97/withfriend/assets/79949843/cd283664-3a73-49e9-b3b0-4dbfc74c161b" width="1000" height="700">
<br><br>

## 적용 기술
- Java
- HTML
- CSS
- JavaScript
- SpringBoot
- Spring Data JPA
- Hibernate
- MySQL
- Thyemleaf

## Entity Diagram
[entity 설계 및 테이블 설계에 관한 회고입니다.](https://foreveryoung97.tistory.com/110)<br>
본 프로젝트에서 테이블은 User, Payment, Goods, Trade 4개의 테이블로 구성되어 있으며 각 테이블의 역할은 다음과 같습니다.

- user table → 소셜 로그인을 통해 서비스에 접근한 사용자의 정보를 저장해 줍니다.
<img src="https://github.com/Jiggy97/withfriend/assets/79949843/e9e4f421-5240-4633-84c2-466761c92f8a" width="300" height="200">
<br><br>

- payment table → 상품 거래를 위해 point를 충전할 때 성공한 결제 요청에 대한 정보를 담고 있습니다.
<img src="https://github.com/Jiggy97/withfriend/assets/79949843/269efcdc-bb3a-4661-a7c0-638903e39c68" width="300" height="150">
<br><br>

- goods table → 사용자의 등록한 상품에 대한 정보를 담고 있고, 클라이언트를 통해 사용자들에게 테이블 내의 데이터를 기반으로 정보가 제공됩니다.
<img src="https://github.com/Jiggy97/withfriend/assets/79949843/e56fdaef-c897-4766-9e59-cd5023817f31" width="300" height="300">
<br><br>

- trade table → 사용자가 point를 통해 거래한 거래 내역에 대한 정보를 갖고 있습니다.
<img src="https://github.com/Jiggy97/withfriend/assets/79949843/68deb4da-be76-427b-8db9-f22e55ca6031" width="300" height="300">
<br>

### 앞으로의 진행
- 프로젝트를 배포하고, CI/CD를 통한 안정적인 서비스 만드는 작업을 진행 중에 있습니다.
- 본 프로젝트의 프론트엔드 작업이 spring 프로젝트 내부에 존재해 파일 관리에 어려움을 느끼고, 프론트 개발에 크고 작은 어려움이 존재함을 느꼈습니다. 이러한 불편함을 덜어내고 더 세밀한 작업을 위해 프론트를 react로 변경할 계획에 있습니다.
- 카카오 소셜 메시지 기능을 통해 자신의 제품을 친구들에게 홍보하고, 서비스의 방문율을 높이도록 설계할 계획이 있습니다.

