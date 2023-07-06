<div align="center">
<p><img src="/doc/images/main.png"></p>
</div>
<br/>
https://www.swith.kr/team21 <br/>

# 1. 프로젝트 팀 소개
 S.WITH 팀은 WiFi와 카메라를 이용하여 사람의 위험 행동 감지하는 프로젝트를 진행하였습니다.<br/>
저희가 해당 과제를 선정하게 된 배경은 아동과 고령에 대해서 부쩍 많아진 안전 사고를 보며 <br/>
사고를 예방할 수 있는 서비스는 없을까 라는 생각에서 시작하게 되었습니다.<br/>
선정 배경을 토대로 영유아 고령자에게 포커스를 맞추고 조사해본 결과 영유아 의 경우 사고 발생의<br/> 
약 90% 이상이 집 안에서 발생하는 안전 사고임을 파악하였습니다.<br/>
또한 고령자의 가정내 안전 사고는 매년 증가 하는 주세이며 이 중 절반 이상이<br/> 
가정 내에서 발생하는 낙상 사고임도 파악할 수 있었습니다.<br/>
따라서 저희는 영유아와 고령자의 안전 사고를 막기 위해 집안에서 사용할 수 있는 서비스를 고안하게 되었고<br/>
이를 바탕으로 홈 IOT 기기를 이용한 AI 행동 감지 플램폼를 구현하게 되었습니다.<br/>
홈 IOT 기기를 통해 가정내 안전 사고를 예방할 수 있고 보호자에게 알리고 자동으로 신고를 해 주며 <br/>
이외에도 비상 상황을 감지해주는 연령과 무관하게 통합적으로 케어할 수 있는 플랫폼을 구현였습니다.<br/>

# 2. 팀원 소개
﻿<div align="center">
<p><img src="/doc/images/introduce.png"></p>
</div>
<br/>

# 3. 서비스 FLOW
﻿<div align="center">
<p><img src="/doc/images/serviceflow.png"></p>
</div>

# 4. 핵심기능
﻿<div align="center">
<p><img src="/doc/images/wifi_camera.png"></p>
</div>
﻿<div align="center">
<p><img src="/doc/images/service.png"></p>
</div>

# 5. AI 기술
﻿<div align="center">
<p><img src="/doc/images/architect.png"></p>
</div>

# 6. 아키텍처
﻿<div align="center">
<p><img src="/doc/images/3tier.png"></p>
</div>

# 7. 인프라
﻿<div align="center">
<p><img src="/doc/images/infra.png"></p>
</div>
Https 적용과 dns를 위하여 라우트53과 클라우드프론드,alb를 도입하였고 리액트 앱을 s3에 배포하였습니다. <br/>
스프링 서버의 경우 ec2에 배포를 하였고 데이터베이스의 경우 아마존 rds를 이용하여 연동을 하였습니다. <br/>
상용화시 최신 모델로 항상 업데이트 할수 있게 AWS S3 storage에서 AI 모델을 다운받은 후 실행하도록 하였습니다

# 8. 기대효과
﻿<div align="center">
<p><img src="/doc/images/expect.png"></p>
</div>


## commit convention

add - 새로운 기능 혹은 코드를 추가할 경우  
style - 스타일 추가 혹은 변경  
fix - 기존코드에 버그수정 및 수정 된 기능이 있을경우  
rename - 파일이름 및 명시된 이름이 변경 되었을 경우  
remove - 코드가 삭제 된 경우사용  
delete - 파일이 삭제된 경우  
refactor - 코드를 전면수정한 경우  
move - 파일이 다른 디렉토리로 이동했을 경우  
test - 테스트 콛, 리펙토링 테스트 코드 추가  
chore - 빌드 업무 수정, 패키지 업무 수정  

