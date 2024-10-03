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

# 📌 ERD 설계와 이유

<img width="574" alt="스크린샷 2024-10-03 오후 7 45 28" src="https://github.com/user-attachments/assets/b27c358b-3c09-4760-b59b-986a3828d966">

### ⚙️ lecture
강의를 관리하는 테이블 입니다.

- id(PK)
- name: 강의명
- instructor: 강사명
- registerCnt: 신청 인원
    - 현재 강의를 신청한 사람
- capacity: 정원
    - 강의 최대 수용 인원
- type: 강의 타입
    - 특강이라는 말이 써있어서 강의에 대한 타입이 구분된다고 생각되어 타입을 따로 갖도록 생성 했습니다.
    - COMMON, SPECIAL
- status: 강의 상태
    - 강의 신청 상태가 여러개 존재할 수 있다고 생각이 들어서 여러가지를 가정해서 만들었습니다.
    - REGISTER, PENDING, CANCELED, FINISHED
- reg_at: 등록일
- upt_at: 수정일

### ⚙️ member
유저를 관리하는 테이블 입니다.

- id(PK)
- name
- reg_at
- upt_at

### ⚙️ lecture_history
유저와 강의 관계를 매핑하는 중계 테이블 입니다.
유저가 강의를 신청하면 해당 강의와 유저를 매핑하여 신청 이력을 남길 때 사용 합니다.

- id(PK)
- member_id(FK)
- lecture_id(FK)
- reg_at
- upt_at

#### ※ 아쉬운 점
요구 사항 구현을 최우선으로 한 결과 위와 같은 테이블 관계를 형성 했었습니다.
하지만 멘토링 이 후 같은 강사가 같은 강의를 각각을 다른 시간에 개강할 수 있음을 고려하지 못 했습니다.
