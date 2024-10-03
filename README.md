# ✨ 항해 백엔드 플러스 6기 - Week02 과제

<details><summary>프로젝트 구조</summary>
<p>

```
📦src
 ┣ 📂main
 ┃ ┣ 📂generated
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂hhplus
 ┃ ┃ ┃ ┃ ┗ 📂week02
 ┃ ┃ ┃ ┃ ┃ ┣ 📂api
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂lecture
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AvailableLectureResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureHistoryResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RegisterLectureRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LectureController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂application
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureFacade.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureHistoryInfo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LectureInfo.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Timestamp.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜GlobalException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂lecture
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Lecture.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureHistory.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LectureValidator.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Member.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberValidator.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂infra
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂lecture
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureHistoryRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LectureRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Week02Application.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┗ 📜application.yml
 ┣ 📂test
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂hhplus
 ┃ ┃ ┃ ┃ ┗ 📂week02
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂lecture
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureServiceIntegrationTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureServiceUnitTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LectureValidatorIntegrationTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LectureValidatorUnitTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberValidatorIntegrationTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberValidatorUnitTest.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Week02ApplicationTests.java
 ┃ ┗ 📂resources
 ┗ 📜.DS_Store
```

</p>
</details> 

# 📌 과제 설명

### **`Default`**

- 아키텍처 준수를 위한 애플리케이션 패키지 설계
- 특강 도메인 테이블 설계 및 목록/신청 등 기본 기능 구현
- 각 기능에 대한 **단위 테스트** 작성

> 사용자 회원가입/로그인 기능은 구현하지 않습니다.

### **`STEP 3`**

- 설계한 테이블에 대한 **ERD** 및 이유를 설명하는 **README** 작성
- 선착순 30명 이후의 신청자의 경우 실패하도록 개선
- 동시에 동일한 특강에 대해 40명이 신청했을 때, 30명만 성공하는 것을 검증하는 **통합 테스트** 작성

### **`STEP 4`**

- 같은 사용자가 동일한 특강에 대해 신청 성공하지 못하도록 개선
- 동일한 유저 정보로 같은 특강을 5번 신청했을 때, 1번만 성공하는 것을 검증하는 **통합 테스트** 작성
