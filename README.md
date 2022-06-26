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