<!-- [![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

[contributors-shield]: https://img.shields.io/github/contributors/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21.svg?style=for-the-badge
[contributors-url]: https://github.com/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21.svg?style=for-the-badge
[forks-url]: https://github.com/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21/network/members
[stars-shield]: https://img.shields.io/github/stars/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21.svg?style=for-the-badge
[stars-url]: https://github.com/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21/stargazers
[issues-shield]: https://img.shields.io/github/issues/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21.svg?style=for-the-badge
[issues-url]: https://github.com/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21/issues
[license-shield]: https://img.shields.io/github/license/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21.svg?style=for-the-badge
[license-url]: https://github.com/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew -->

# 프로젝트 이름
<div align="center">
<p><img src="/doc/images/main.png"></p>
</div>
<a href = "https://www.swith.kr">Swith: Stay With 웹페이지 바로가기</a>
<br/>
<br/>
<!-- TABLE OF CONTENTS -->
<details>
<summary>목차</summary>
<ol>
<li>
 <a href="#프로젝트-소개">프로젝트 소개</a>
 <ul>
  <li><a href="#개발-기간">개발 기간</a></li>
 </ul>
 <ul>
  <li><a href="#개발-환경">개발 환경</a></li>
 </ul>
 <ul>
  <li><a href="#팀-구성">팀 구성</a></li>
 </ul>
</li>
<li><a href="#서비스-플로우">서비스 플로우</a></li>
<li><a href="#핵심-기능">핵심 기능</a></li>
<li><a href="#ai-기술">AI 기술</a></li>
<li><a href="#아키텍처">아키텍처</a></li>
<li><a href="#인프라">인프라</a></li>
<li><a href="#기대효과">기대효과</a></li>
</ol>
</details>


## 프로젝트 소개
<details>
<summary>Wi-Fi와 카메라 데이터를 활용한 사각지대 없는 사고 감지 서비스</summary>
<ul>
&nbsp S.WITH 팀은 WiFi와 카메라를 활용하여 사람의 위험 행동을 감지하는 프로젝트를 수행했습니다. <br/>이 프로젝트를 선택한 배경은 안전 사고가 아동과 고령자 사이에서 증가하고 있음을 관찰하면서, 이를 예방할 수 있는 서비스의 필요성을 느낀 것에서 출발하였습니다. <br/>이에 따라 영유아와 고령자에 초점을 맞추어 조사한 결과, 영유아의 사고 발생 대부분이 가정 내에서 발생하는 안전 사고임을 확인했습니다. <br/>또한 고령자의 안전 사고는 연간 증가하고 있으며, 그 중 절반 이상이 가정 내에서 발생하는 낙상 사고임을 발견했습니다.<br/><br/>

&nbsp 따라서, 저희는 영유아와 고령자의 안전 사고를 예방하기 위해 가정에서 사용할 수 있는 서비스를 고안하게 되었고, 이를 바탕으로 홈 IoT 기기를 활용한 AI 행동 감지 플랫폼을 구현하였습니다. <br/>이 플랫폼을 통해 가정 내에서 발생하는 안전 사고를 예방할 수 있으며, 사고 발생 시 보호자에게 알림을 전송하고 자동으로 신고할 수 있습니다. <br/>또한, 연령에 관계없이 비상 상황을 감지하고 종합적으로 관리할 수 있는 통합적인 케어 플랫폼을 구현하였습니다.<br/>
 
<!--  S.WITH 팀은 WiFi와 카메라를 이용하여 사람의 위험 행동 감지하는 프로젝트를 진행하였습니다.<br/>
저희가 해당 과제를 선정하게 된 배경은 아동과 고령에 대해서 부쩍 많아진 안전 사고를 보며 <br/>
사고를 예방할 수 있는 서비스는 없을까 라는 생각에서 시작하게 되었습니다.<br/>
선정 배경을 토대로 영유아 고령자에게 포커스를 맞추고 조사해본 결과 영유아의 경우 사고 발생의<br/> 
약 90% 이상이 집 안에서 발생하는 안전 사고임을 파악하였습니다.<br/>
또한 고령자의 가정내 안전 사고는 매년 증가 하는 주세이며 이 중 절반 이상이<br/> 
가정 내에서 발생하는 낙상 사고임도 파악할 수 있었습니다.<br/>
따라서 저희는 영유아와 고령자의 안전 사고를 막기 위해 집안에서 사용할 수 있는 서비스를 고안하게 되었고<br/>
이를 바탕으로 홈 IOT 기기를 이용한 AI 행동 감지 플램폼를 구현하게 되었습니다.<br/>
홈 IOT 기기를 통해 가정내 안전 사고를 예방할 수 있고 보호자에게 알리고 자동으로 신고를 해 주며 <br/>
이외에도 비상 상황을 감지해주는 연령과 무관하게 통합적으로 케어할 수 있는 플랫폼을 구현였습니다.<br/> -->
</ul>
</details>


### 개발 기간
2023.05.31-2023.07.06<br/>

### 개발 환경
| 개발 환경	| |
| -- | -- |
| 운영체제|	Window 10, Ubuntu(AWS), Mac |
| 버전 관리|	Git, Github|
| 개발 도구|	IntelliJ IDEA, Visual Studio Code, PyCharm|
| 개발 언어 및 프레임워크|	Java - Spring<br>Python3 - Tensorflow<br>JavaScript - React <br> HTML & CSS - Bootstrap <br>|
| 데이터베이스|	AWS - RDS(MySQL)<br>H2|
| 웹 서버 |	AWS - EC2 |
| 저장소 | AWS - S3 |
| 로드 밸런서 | AWS - ELB|
<br/>

### 팀 구성
|이름|github|역할|
|--|--|--|
|김용환|<a href = "https://github.com/brightface">brightface</a>|AI|
|장규진|<a href = "https://github.com/kj021">kj021</a>|AI|
|조예령|<a href = "https://github.com/CYeryeong">CYeryeong</a>|AI|
|김영재|<a href = "https://github.com/Yjason-K">Yjason-K</a>|FE|
|오승재|<a href = "https://github.com/18-12847">18-12847</a>|FE|
|남환준|<a href = "https://github.com/only-juun">only-juun</a>|BE|
|이한준|<a href = "https://github.com/leehanjun506">leehanjun506</a>|BE|


<br/>

## 서비스 플로우
﻿<div align="center">
<p><img src="/doc/images/serviceflow.png"></p>
</div>
<br/>

## 핵심 기능
﻿<div align="center">
<p><img src="/doc/images/wifi_camera.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/images/service.png"></p>
</div>

## AI 기술
﻿<div align="center">
<p><img src="/doc/images/architect.png"></p>
</div>

## 아키텍처
﻿<div align="center">
<p><img src="/doc/images/3tier.png"></p>
</div>

## 인프라
﻿<div align="center">
<p><img src="/doc/images/infra.png"></p>
</div>
Https 적용과 dns를 위하여 라우트53과 클라우드프론드,alb를 도입하였고 리액트 앱을 s3에 배포하였습니다. <br/>
스프링 서버의 경우 ec2에 배포를 하였고 데이터베이스의 경우 아마존 rds를 이용하여 연동을 하였습니다. <br/>
상용화시 최신 모델로 항상 업데이트 할수 있게 AWS S3 storage에서 AI 모델을 다운받은 후 실행하도록 하였습니다

## 기대효과
﻿<div align="center">
<p><img src="/doc/images/expect.png"></p>
</div>

## 세부 내용
