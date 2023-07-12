# S.WITH(Stay With)
### Wi-Fi와 카메라 데이터를 활용한 사각지대 없는 사고 감지 서비스
<div align="center">
<p><img src="/doc/images/thumbnail.jpg"></p>
</div>
<h3>
<p align="center">
<strong>
<a href = https://d1t41u00w18ofo.cloudfront.net/main">Swith 서비스 바로가기</a></strong><br>
</p>
</h3>

📢 <Strong>본 서비스는 2023.07.11까지 배포할 예정입니다.<Strong> <br>

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
>  S.WITH 팀은 WiFi와 카메라를 활용하여 사람의 위험 행동을 감지하는 프로젝트를 수행했습니다. <br>
> 이 프로젝트를 선택한 배경은 안전 사고가 아동과 고령자 사이에서 증가하고 있음을 관찰하면서, 이를 예방할 수 있는 서비스의 필요성을 느낀 것에서 출발하였습니다. <br>
> 이에 따라 영유아와 고령자에 초점을 맞추어 조사한 결과, 영유아의 사고 발생 대부분이 가정 내에서 발생하는 안전 사고임을 확인했습니다. <br>
> 또한 고령자의 안전 사고는 연간 증가하고 있으며, 그 중 절반 이상이 가정 내에서 발생하는 낙상 사고임을 발견했습니다.<br>
> <br>
>  따라서, 저희는 영유아와 고령자의 안전 사고를 예방하기 위해 가정에서 사용할 수 있는 서비스를 고안하게 되었고, <br>
> 이를 바탕으로 홈 IoT 기기를 활용한 AI 행동 감지 플랫폼을 구현하였습니다. <br>
> 이 플랫폼을 통해 가정 내에서 발생하는 안전 사고를 예방할 수 있으며, 사고 발생 시 보호자에게 알림을 전송하고 자동으로 신고할 수 있습니다. <br>
> 또한, 연령에 관계없이 비상 상황을 감지하고 종합적으로 관리할 수 있는 통합적인 케어 플랫폼을 구현하였습니다.<br>

<br>

### 📅개발 기간
<Strong>2023.05.31-2023.07.06<Strong><br/>
<br/>

### 개발 환경

| 개발 환경	| |
| -- | -- |
| 운영체제|<img src="https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white"><img src="https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white"><img src="https://img.shields.io/badge/macOS-000000?style=for-the-badge&logo=macos&logoColor=white">|
| 버전 관리|<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">|
| 개발 도구|<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"><img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=for-the-badge&logo=Visual Studio Code&logoColor=white"><img src="https://img.shields.io/badge/PyCharm-000000?style=for-the-badge&logo=PyCharm&logoColor=white">|
| 개발 언어 및 프레임워크|	<img src="https://img.shields.io/badge/Java-6DB33G?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"><br><img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white"><img src="https://img.shields.io/badge/tensorflow-FF6F00?style=for-the-badge&logo=tensorflow&logoColor=white"><br><img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"><img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white">|
| 데이터베이스|<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">|
| 웹 서버 |	<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white">|
| 저장소 | <img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"> |
<br/>

### 팀 구성
| [<img src="https://github.com/only-juun.png" width="96px;"/><br><sup>남환준</sup>](https://github.com/only-juun)<br><sup>BE</sup> | [<img src="https://github.com/leehanjun506.png" width="96px;"/><br><sup>이한준</sup>](https://github.com/leehanjun506)<br><sup>BE</sup> |[<img src="https://github.com/brightface.png" width="96px;"/><br><sup>김용환</sup>](https://github.com/brightface)<br> <sup> AI </sup>| [<img src="https://github.com/kj021.png" width="96px;"/><br><sup>장규진</sup>](https://github.com/kj021)<br><sup>AI</sup> | [<img src="https://github.com/CYeryeong.png" width="96px;"/><br><sup>조예령</sup>](https://github.com/CYeryeong)<br><sup>AI</sup>   | [<img src="https://github.com/Yjason-K.png" width="96px;"/><br><sup>김영재</sup>](https://github.com/Yjason-K)<br><sup>FE</sup> | [<img src="https://github.com/18-12847.png" width="96px;"/><br><sup>오승재</sup>](https://github.com/18-12847)<br><sup>FE</sup>   | 
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |

<br/>

## Service Flow
﻿<div align="center">
<p><img src="/doc/images/serviceflow.png"></p>
</div>
<br/>

## Web
﻿<div align="center">
<p><img src="/doc/web/main.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/second.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/team.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/board.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/post.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/read.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/update.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/inquiry.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/mypage.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/signup.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/regex.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/find.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/web/signin.png"></p>
</div>

## AI 기술
﻿<div align="center">
<p><img src="/doc/images/architect.png"></p>
</div>

## 인프라
>  HTTPS 적용과 DNS를 위하여 라우트53과 클라우드프론트, alb를 도입하였고 리액트 앱을 AWS S3에 배포하였습니다. <br/>
> 스프링 서버의 경우 EC2에 배포를 하였고 데이터베이스의 경우 아마존 RDS를 이용하여 연동하였습니다. <br/>
> 상용화시 최신 모델로 항상 업데이트 할수 있게 AWS S3 storage에서 AI 모델을 다운받은 후 실행하도록 하였습니다 <br/>
<div align="center">
<p><img src="/doc/images/infra.png"></p>
</div>

## 기대효과
﻿<div align="center">
<p><img src="/doc/images/expect.png"></p>
</div>

## 부록
### DB 스키마
﻿<div align="center">
<p><img src="/doc/db_schemapng.png"></p>
</div>

### API Docs
﻿<div align="center">
<p><img src="/doc/api_docs.png"></p>
</div>
