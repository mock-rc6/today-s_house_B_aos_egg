# todayhome
오늘의 집 clone (완성률 : 3%)

프로젝트 기간 : 22-06-25 ~ 22-07-08 

<details>
<summary>1차 팀원 회의 내용 </summary>
팀원 : 데이지(서버), 마누(android), 에그(android), 루시(android)<br>
다음회의 : 6월 28일 예정

구현한 것은 ★ 표시

📌구현할 내용
<li>★스플래시 화면 구현</li> 
<li>★로그인[소셜, 이메일]</li> 
<li>회원가입[카카오, 이메일] </li> 
<li>홈화면 구현10% </li> 
<li>네비게이션 바 구현 80% </li> 
<li>상품 상세 페이지 </li> 
<li>카테고리 </li> 
<li>스토어 상세 </li> 
<li>리뷰 내역,상세 </li> 
<li>배너 리스트</li> 
<li>아이디비번 찾기</li>
<li>스크랩</li> 
<li>리뷰 작성</li> 
<li>비회원 주문 조회 [이메일, 주문번호] -> 우선 순위 아래로</li> 
<li>장바구니 -> 클라이언트 [휴대전화 인증 번호 발송(지정된 숫자만큼) / 주소]</li>
<li>쿠폰</li>

1주차는 1주차 계획 (27일 까지)
- Splash 로그인 화면 구현<br> ★
- 오늘의집 레이아웃 구성<br>
- 로그인api (카카오, 이메일 로그인) ☆ 이메일은 x 



</details>
<details>
<summary>2차 팀원 회의 내용 </summary>
팀원 : 데이지(서버), 마누(android), 에그(android), 루시(android)<br>
2022-06-29

- 비회원 주문 조회하기 뷰 만 그리기로 결정
- 회원가입 부분 맞춤설정 안 하기로 결정
- 로그아웃 기능 요청


</details>
<details>
<summary>📌6월 25일 개발 일지</summary>
📌6월 25일 개발 일지

1. 앱 아이콘 모두의 집으로 변경


   <img src = "https://user-images.githubusercontent.com/76811495/175778576-11935c77-d80c-4b09-b709-11fdbbe40e68.png" width="width 30px" height="height 30%">






2. 소셜 로그인 완료(카카오 API)


   <img src = "https://user-images.githubusercontent.com/76811495/175778773-b9cae0cf-a14b-4e80-8e31-1d5f88d4b812.png" width="width 30%" height="height 30%">


3. 로그인 페이지 UI 99% (약간 다듬어야 합니다.)


   <img src = "https://user-images.githubusercontent.com/76811495/175778842-6182194b-2c07-4b5a-a71e-b7ad9ee3c79d.png" width="width 30%" height="height 30%">

4. 이메일 로그인 + 이메일로 가입 80% (이메일 + 비밀번호 다 입력했을 때 로그인하기 버튼 이벤트 구현해야 합니다. )

   <img src = "https://user-images.githubusercontent.com/76811495/175778889-31c4b2e9-6553-4ee3-afd2-b9b5b4c8aaae.png" width="width 30%" height="height 30%">

   <img src = "https://user-images.githubusercontent.com/76811495/175779085-0bc8e6a7-6448-430f-ab84-a6f912a93bdb.png" width="width 30%" height="height 30%">


5. 비회원 주문조회하기 레이아웃 구현 90% (버튼만 넣으면 됩니다.)
   <img src = "https://user-images.githubusercontent.com/76811495/175778978-a6498731-e83d-434b-95a8-45f6b5ba9d98.png" width="width 30%" height="height 30%">

6. 바텀네비게이션 20% ( Fragment 5개 추가를 하였고 4개는 (홈, 스토어, 홈서비스,마이페이지), 1개는 + 버튼인데 클릭하면 아래에서 위로 올라오는 데 구현해보지 않아서 내일 구현해야 할 거 같습니다. )


   <img src = "https://user-images.githubusercontent.com/76811495/175778954-81ae4344-5464-4e6f-8841-f540bd8260e0.png" width="width 30%" height="height 30%">
7. splash 화면 구현(크기 조절 필요)


   <img src = "https://user-images.githubusercontent.com/76811495/175778935-ad2ee378-9bf0-4858-b68a-70f902af2a3d.png" width="width 30%" height="height 30%">

</details>
<details>
<summary>1차 피드백 내용 </summary>

- 아이콘 누끼따서 오늘의 집과 똑같이 만들기 
- 마진, 패딩 미흡
- 서버 파트분이랑 커뮤니케이션 미흡 -> 소설 로그인 논의 필요
- 삽질x -> 모르는 거 있으면 바로 질문하고 다른 일 처리하기
- 다음주 월요일까지 api 주요 기능 15개 추가해보기


</details>
<details>
<summary>📌6월 26일 개발 일지</summary>

### 1. 파이어베이스 Authentication 이용해 페이스북 로그인 구현<br>
구현하는 데 시간이 조금 걸렸지만 무사히 구현을 하였습니다. <br>
하지만 로그인 버튼이 com.facebook.login.widget.LoginButton 요렇게 쓰면 사진이 안 넣어져서 이 부분에서 시간이 많이 소요하였습니다.<br>
<br>
구글링 + Stack Overflow 검색 후 한 블로그에서 `FrameLayout` 으로 묶고 그 안에서 `com.facebook.login.widget.LoginButton` 는 `visibility="gone"` 으로 보이지 않게 해준 후 `ImageButton` 에 `onClick`  속성을 달아서 `com.facebook.login.widget.LoginButton`
가 수행하도록 한 결과 무사히 성공하였습니다.<br>
<br>
<br>



<img src = "https://user-images.githubusercontent.com/76811495/175814847-0765c879-1c74-4c88-aca7-b56809d1ccd2.png" width="width 30px" height="height 30%">
<br>
<br>


### 2. HomeFragment + StoreFragment 에 TabLayout 를 넣어줬습니다.<br>
이 부분에서는 큰 issue 가 없었습니다.<br>

<img src = "https://user-images.githubusercontent.com/76811495/175815566-5b50d47e-4ad8-40cf-8273-8e20aad3d4ae.png" width="width 30px" height="height 30%">
<br>
<br>


### 3. StoreFragment 에서 스토어홈 탭에서 ViewPage2를 넣어줬습니다.<br>
ViewPage2가 TabLayout 쪽 ViewPager2와 스크롤 방향이 같아서 베너가 움직이질 않았습니다.
그래서 자식 뷰가 우선적으로 스크롤을 인식할 수 있도록 `NestedScrollableHost ` 사용해 issue를 
해결하였습니다.
<br>
<img src = "https://user-images.githubusercontent.com/76811495/175815821-7a3ab0ad-e900-4cf8-a166-2a350ea7e428.png" width="width 30px" height="height 30%">
<br>
<br>

### 해결 못 한 issue<br><br>
아래 스크린샷을 보면 바텀네비게이션 마지막에 플러스 버튼이 크기가 작은걸 고치고 싶었는데 결국 오늘 못 고쳤습니다.
단서만 찾았을 뿐 어떻게 저걸 해결해야 할지 더 해봐야 할 거 같습니다.

플로팅 버튼을 저 위에다 놓고 사용하려고 시도를 해봤지만 플로팅 버튼안에 크기를 키우면 이미지가 밖으로 나가는 현상이
발생해 실패를 하였습니다.



<img src = "https://user-images.githubusercontent.com/76811495/175815821-7a3ab0ad-e900-4cf8-a166-2a350ea7e428.png" width="width 30px" height="height 30%">



</details>


<details>
<summary>📌6월 27일 개발 일지</summary>

### 1. 어제 실패한 bottomNavigation UI 개선 및 ViewPage2 indicator 추가
<br>
<br>
bottomNavigation 부분은 제가 너무 어렵게 생각해서 오래 걸린 듯 합니다.<br>
XML 부분에서 bottomNavigation 부분에서 자식뷰에 버튼을 넣어서 배치를 해줘서 BottomSheetDialog가 나오도록 구현을 하였습니다.
<br>
<br>
ViewPage2 indicator는 라이브러리를 이용해 넣어줬습니다.


<br>
<img src = "https://user-images.githubusercontent.com/76811495/175862656-78aa511d-247d-44a5-9bb6-27e0893c76e3.png" width="width 30px" height="height 30%">




</details>

<details>
<summary>📌6월 28일 개발 일지 </summary>
오늘은 서버분이 만들어 놓은 API를 엮어 볼려고 했는데 실패를 했습니다.
어제 조이님이 삽질하지 말라고 했는데 데이터가 불러와 져서 조금만 하면 될 거 같았는데 결국 삽질해서 오늘 시간 분배를 실패했습니다.
제가 Retrofit 다루는 기술이 많이 미흡한 거 같습니다.
Retrofit 할때 @GET만 해보고 넘어간 게 이렇게 스노우볼이 굴러온 거 같습니다. 오늘은 결과적으로 1개의 API(/app/store 스토어 메인화면)만 연동하는데 성공 하였습니다.
<br>
<br>
<br>
<img src = "https://user-images.githubusercontent.com/76811495/176194129-b2b6d384-4c97-4776-bd75-963855ae488f.PNG" width="width 30px" height="height 30%">

★22-06-29 로그인,회원가입 구현
하루종일 로그인, 회원가입을 붙잡고 있었는데 아무리 해도 구현이 안되서 의기소침해 있었는데<br>
Log를 하나씩 찍던 중 뭔가 이상한점이 발견했습니다.
<br>
<br>
<img src = "https://user-images.githubusercontent.com/76811495/176215260-2c6f2680-6e92-4381-b77f-aacf2e56ab6a.PNG" width="width 30px" height="height 30%">
<br>
<br>
바로 회원가입 하는 부분에서 패스워드가 로그에 찍혀야 하는데 닉네임이 로그에 찍혀있었습니다.
<br> 그래서 바로 data class에서 name과 password 위치를 바꿔주었더니 정상적으로 되었습니다.
retrofit에 대한 공부와 API명세서를 제대로 안보고 무지성으로 코딩한 제 잘못입니다.



</details>

<details>
<summary>📌6월 29일 개발 일지 </summary>
오늘은 기능적인 부분보다는 누끼를 따고 오늘의 집과 똑같이 만들려고 뷰를 그리는데 집중하였습니다.
1차 피드백 내용중에 마진,패딩 부분이 많이 미흡하다는 지적이 있었는데 정말 비교해보니 정말 별로인거 같아
차라리 UI를 다 그리고 API를 다 붙여야겠다 생각하고 있습니다.<br>



</details>

<details>
<summary>📌6월 30일 개발 일지 </summary>
오늘은 스트리홈에 오늘의 딜, 최근 본 상품, 내가 본 상품의 연관 상품, ~님을 위한 상품 부분을 서버분이 만들어 주신<br>
데이터로 viewPage2를 만들었습니다.

<br>
상품을 클릭을 하고 상세페이지까지 가는거 까지 구현을 하였고 내일 상세페이지를 구현을 하겠습니다.
<br>
<br>
<br>
<img src = "https://user-images.githubusercontent.com/76811495/176689209-75c57dca-957b-4997-a90a-bc0155e8a685.PNG" width="width 30px" height="height 30%">
<br>
<br>
<br>
<img src = "https://user-images.githubusercontent.com/76811495/176689509-6329e5d1-2ba1-42fd-a108-c69c2fa81d00.PNG" width="width 30px" height="height 30%">


</details>