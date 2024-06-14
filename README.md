# 🫱🏼‍🫲🏽 withfriend
SNS 친구들과  하는 중고거래 (1인 개발)
- 개발 동기 :  중고거래의 가장 큰 문제점인 익명의 사용자와의 거래를 해결하기 위해
- 진행 기간 : 2023.08. - 2023.10.
- 사용 기술 : SpringBoot(Java), MySQL, JPA, Thyemleaf
- 주요 기능 : 소셜 로그인, 결제 기능, 중고 거래

## 서비스 요약
- 소셜 로그인을 통해 서비스를 이용할 때, 사용자가 매번 새로운 계정을 생성해야 하는 불편함을 해소했습니다
- OAuth 2.0 기반으로 사용자의 인증, 인가를 설계하고, 사용자의 정보에 접근하였습니다. 이를 통해 사용자는 SNS 친구들과 중고거래를 할 수 있습니다.
- Portone(구 Iamport)에서 가맹점을 생성해 결제 기능을 구현했습니다.
- 사용자는 서비스 내부 Point를 통해 SNS 친구들과 거래를 할 수 있습니다.
- Thyemleaf 라이브러리를 활용해 동적 페이지를 구성하였습니다.
<br>

## 프로젝트 회고
[서비스 흐름도](https://foreveryoung97.tistory.com/125) <br>
[개발 이슈](https://foreveryoung97.tistory.com/category/withfriend/%EA%B0%9C%EB%B0%9C%20%EC%9D%B4%EC%8A%88) <br>
[회고록](https://foreveryoung97.tistory.com/category/withfriend/%ED%9A%8C%EA%B3%A0)

## **<좋은 git 커밋 메시지의 7가지 규칙>**

1. 제목과 본문을 **빈 행으로 구분**한다.
2. 제목은 **50글자** 이내로 제한한다.
3. 제목의 **첫 글자는 대문자**로 작성한다.
4. 제목 끝에는 **마침표를 넣지 않는다**.
5. 제목은 **명령문**으로 사용하며 **과거형을 사용하지 않는다**.
6. 본문의 **각 행은 72글자 내**로 제한한다.
7. 어떻게 보다는 **무엇과 왜를 설명**한다.

## **<타입>**

| 타입 이름 | 내용 |
| --- | --- |
| feat | 새로운 기능에 대한 커밋 |
| fix | 버그 수정에 대한 커밋 |
| build | 빌드 관련 파일 수정 / 모듈 설치 또는 삭제에 대한 커밋 |
| chore | 그 외 자잘한 수정에 대한 커밋 |
| ci | ci 관련 설정 수정에 대한 커밋 |
| docs | 문서 수정에 대한 커밋 |
| style | 코드 스타일 혹은 포맷 등에 관한 커밋 |
| refactor | 코드 리팩토링에 대한 커밋 |
| test | 테스트 코드 수정에 대한 커밋 |
| perf | 성능 개선에 대한 커밋 |
| design | CSS 등 UI 디자인 변경 |
| rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업 |
| remove  | 파일을 삭제하는 작업 수행 |
| revert | 되돌리기 |
| comment | 주석 추가하거나 변경하는 작업 |
