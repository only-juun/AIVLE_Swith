import { useEffect, useState, useRef } from "react";
import Myheader from "../components/header";
import "../style/service.css";
import { useNavigate } from "react-router-dom";
import LOG from "../components/logComponent";
import Webcam from "react-webcam";

const SERVICE = () => {
  const session = localStorage.getItem("token");
  const navigate = useNavigate();

  // 세션 체크
  if (!session || session === "null") {
    navigate("/main", { replace: true });
  }

  const [logData, setLogData] = useState([]);

  useEffect(() => {
    if (Notification.permission !== "granted") {
      Notification.requestPermission();
    }
  }, []);

  useEffect(() => {
    const eventSource = new EventSource(
      `http://15.165.98.14:8080/notifications/subscribe/123456`
    );

    eventSource.addEventListener(
      "sse",
      function (e) {
        const res = e.data;
        if (res !== "EventStream Created. [userId=123456]") {
          const jsonres = JSON.parse(res);
          const now = new Date().getTime() + 32400000;
          const logTime = new Date(parseInt(now))
            .toISOString()
            .replace("T", " ")
            .split(".")[0]
            .slice(2, 19);

          if (jsonres !== "EventStream Created. [userId=123456]") {
            const parseD = jsonres;
            parseD.time = logTime;
            setLogData((prevLogData) => [parseD, ...prevLogData]);
            if (Notification.permission === "granted") {
            }
          }
        }
      },
      false
    );

    eventSource.onerror = (e) => {
      // 종료 또는 에러 발생 시 할 일
      eventSource.close();
    };
    return () => {
      eventSource.close(); // EventSource 연결 종료
    };
  }, []);

  // camera setting
  const webcamRef = useRef(null);
  const [webcamStream, setWebcamStream] = useState(null);

  useEffect(() => {
    const enableWebcam = async () => {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: true,
        });
        setWebcamStream(stream);
      } catch (error) {
        window.alert("Error accessing webcam:", error);
      }
    };

    enableWebcam();

    // 컴포넌트 언마운트 시 웹캠 스트림 정리
    return () => {
      if (webcamStream) {
        webcamStream.getTracks().forEach((track) => track.stop());
      }
    };
  }, [webcamStream]);

  return (
    <div className="service">
      <Myheader isLogin={session} />
      <div className="container">
        <div style={{ display: "flex", flexDirection: "column" }}>
          <div className="livecam">
            {" "}
            {webcamStream ? (
              <Webcam
                audio={false}
                ref={webcamRef}
                videoConstraints={{
                  width: 610,
                  height: 360,
                  facingMode: "user",
                }}
              />
            ) : (
              <div>No webcam available</div>
            )}
          </div>
        </div>
        <div className="verticalContainer">
          <div className="logContainer">
            <div className="log_record">로그 기록</div>
            <div className="log_list">
              <span id="listcHead">날짜 및 시간</span>
              <span id="listcHead">로그 메세지</span>
              <span id="listcHead">Wi-fi</span>
              <span id="listcHead">CAM</span>
            </div>
            <LOG logData={logData} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default SERVICE;