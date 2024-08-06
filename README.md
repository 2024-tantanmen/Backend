# **탄수 발자국**

<br/>
<div align="center"> <img src ="https://carbofootprint-bucket.s3.ap-northeast-2.amazonaws.com/docs/tantanmen_main.png" alt="탄탄멘" width="40%"></div>
<div align="center">학습 모델을 활용한 푸드렌즈와 AI 챗봇 기반 솔루션<br></vr><b>탄수 발자국</b></div>
<br/>

---

## **팀 소개**

### **- 팀 이름**

<b>학습 모델을 활용한 푸드렌즈와 AI 챗봇 기반 솔루션, 탄수 발자국</b>

### **- 팀원 소개**
<br>
<div align="center"> <img src ="https://carbofootprint-bucket.s3.ap-northeast-2.amazonaws.com/docs/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2024-08-06+21.22.48.png" alt="늘보미" width="40%"></div>
<br>

| 이름  | 역할        | 담당                                                     |
|-----|-----------|--------------------------------------------------------|
| 장민정 | PM, Design |  전반적인 서비스 기획 및 UI 디자인  |
| 김건우 | Frontend  | 일정 캘린더, 채팅 및 식단 추천 페이지 UI 및 기능 구현                      |
| 이세은 | Frontend  | 로그인/회원가입, 탄수화물 중독 테스트, 탄수화물 함량 카운팅, Ai챗봇 페이지 UI 및 기능 구현 |
| 김진하 | Backend   | 로그인/회원가입, chatGPT를 이용한 챗봇 기능 구현                        |
| 김준하 | Backend   | AWS 서버 통신, AI 모델 개발 및 연동, 웹 소켓 채팅 기능 구현                |

<br/>

---

## **프로젝트 소개**

### **1️⃣ 주제**

- 서비스 내용

  ```
  학습 모델을 활용한 푸드렌즈와 AI 챗봇 기반 솔루션, 탄수 발자국

  건강한 식단은 맛 없어요.내 취향에 맞는 맛있는 건강 식단을 원해요!
  - 알레르기,식습관을 고려한 맞춤형 식단 추천 서비스

  건강 식단인 줄 알았는데 생각보다 숨은 탄수화물이 많아요!
  - 학습 모델을 활용한 자체제작 푸드렌즈로 제공하는 탄수화물 함량 카운팅

  궁금한 것이 있을 때마다 쉽게 답해줄 수 있는 전문가가 필요해요!
  - 탄수화물에 대한 모든 것! AI 챗봇 기반 솔루션  

  혼자 관리하기엔 의지가 부족해요!
  - 함께 건강한 식습관을 형성해요! 탄수 발자국 커뮤니티

  외식이 있을 땐 건강한 식단을 지키기 어려워요!
  - 건강한 외식 메뉴 카톡으로 받아보기

  내 상태가 어떤지 진단 받아보고 싶어요!
  - 정기적인 탄수화물 중독 테스트로 건강 지키기

  ```

### **2️⃣ 프로젝트 규칙**

### ✅ Commit Convention

| 태그 이름      | 설명                                 |
|------------|------------------------------------|
| [chore]    | 코드 수정, 내부 파일 수정                    |
| [feat]     | 새로운 기능 구현                          |
| [add]      | FEAT 이외의 부수적인 코드 추가, 라이브러리 추가, 새로운 파일 생성 |
| [hotfix]   | issue나 QA에서 급한 버그 수정에 사용           |
| [fix]      | 버그, 오류 해결                          |
| [del]      | 쓸모 없는 코드 삭제                        |
| [docs]     | README나 WIKI 등의 문서 개정              |
| [correct]  | 주로 문법의 오류나 타입의 변경, 이름 변경에 사용       |
| [move]     | 프로젝트 내 파일이나 코드의 이동                 |
| [rename]   | 파일 이름 변경이 있을 때 사용                  |
| [improve]  | 향상이 있을 때 사용                        |
| [refactor] | 전면 수정이 있을 때 사용                     |
| [test]     | 테스트 코드 추가 시 사용                     |


- 커밋 규칙 -> FEAT. 해당 기능 설명
- `ex) FEAT. 로그인 기능 추가`


### 🔸Git Working Process
1. issue를 등록한다.
2. 깃 컨벤션에 맞게 Branch를 생성한다.
3. Add - Commit - Push - Pull Request 의 과정을 거친다.
3. 작업이 완료되었다면, Remote Repository(Github)에 해당 브랜치를 올린다.
4. Github에서 PR을 생성한다.
5. CI 테스트를 잘 통과했다면, 해당 PR에 관한 리뷰를 요청한다.
6. 리뷰에서 Approve를 받지 못했다면, 수정 사항을 처리해서 다시 올린다.
7. Approve를 받았다면, Merge를 진행한다.
8. merge된 Branch는 삭제한다.
9. 종료된 Issue와 Pull Request의 Label과 Project를 관리한다.

🌴 브랜치
---
#### 📌 브랜치 단위
- 브랜치 단위 = 이슈 단위 = PR단위

#### 📌 브랜치명
- 브랜치는 뷰 단위로 생성합니다.
- 브랜치 규칙 → feature/#이슈번호-기능간략설명
- `ex) feature/#1-login`


### **3️⃣ 기술 스택**

<br>
<img src ="https://carbofootprint-bucket.s3.ap-northeast-2.amazonaws.com/docs/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2024-08-06+22.30.50.png" alt="아키텍쳐" width="60%">
<br>

- `FrontEnd` :<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white"> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"> ![Vercel](https://img.shields.io/badge/vercel-%23000000.svg?style=for-the-badge&logo=vercel&logoColor=white)<br>

- `BackEnd` : <img src="https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/Flask-000000?style=for-the-badge&logo=Flask&logoColor=white"> <br>

- `DevOps` : <img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=for-the-badge&logo=Amazon AWS&logoColor=white"><img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"><br>


### **4️⃣ AI 모델**

- [AI Hub](https://aihub.or.kr/aidata/30747)에서 제공하는 라벨링 데이터를 이용하여 Yolo 모델 학습

### **5️⃣ 프로젝트 산출물**

## **1. ERD**
<img src="https://carbofootprint-bucket.s3.ap-northeast-2.amazonaws.com/docs/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2024-08-06+22.04.26.png">

## **2. 와이어 프레임 및 디자인**
[Figma 링크](https://www.figma.com/design/xn9MIcS0nSTCpHXPZziJaO/2024%EC%A4%91%EC%95%99%ED%86%A4_%ED%83%84%ED%83%84%EB%A9%98?node-id=290-13748&t=pk4R78GHkYkSxT8R-1)


## **3. 발표 자료**

  [발표 PPT](https://carbofootprint-bucket.s3.ap-northeast-2.amazonaws.com/docs/%E1%84%90%E1%85%A1%E1%86%AB%E1%84%89%E1%85%AE+%E1%84%87%E1%85%A1%E1%86%AF%E1%84%8C%E1%85%A1%E1%84%80%E1%85%AE%E1%86%A8_PPT_%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%B2%E1%84%8B%E1%85%AD%E1%86%BC.pdf)

