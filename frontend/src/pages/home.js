import React, { useEffect, useState, useRef } from "react";
import Myheader from "../components/header";
import Carousel from "react-bootstrap/Carousel";
import "bootstrap/dist/css/bootstrap.min.css";
import service2 from "../img/home_service2.png";
import box from "../img/main.gif";
import homecamera from "../img/homecamera.png";
import skecamera from "../img/camera.gif";
import family from "../img/hramonius_family.gif";
import wifi_s from "../img/wifi_summary.png";
import wifi from "../img/wifi.png";
import camera_detec from "../img/camera_detec.png";
import for_who from "../img/for.png";
import "../style/home.css";
import ScrollToTopButton from "../components/ScrollToTopButton";

const UncontrolledExample = () => {
  return (
    <center>
      <Carousel style={{ width: "100%" }}>
        <Carousel.Item>
          <img className="d-bloc" src={service2} height="500px" width="75%" />
          <Carousel.Caption
            style={{ color: "white", backgroundColor: "rgba(0, 0, 0, 0.8)" }}
            className="caption"
          >
            <h3>
              S.with 서비스는 WIFI 신호와 카메라를 활용하여
              <br />
              카메라에 보이지 않는 사각 지대까지 감지가 가능합니다.
            </h3>
            <p>
              AI를 통한 실시간 위험감지가 가능하고 실시간 행동 추정으로 위험
              상황 감지가 가능한 AI 서비스를 이용해서 집에서도 안심하고
              지내세요!
            </p>
          </Carousel.Caption>
        </Carousel.Item>

        <Carousel.Item>
          <div className="image_container_1">
            <img src={family} alt="image1" />
          </div>
          <Carousel.Caption
            style={{ color: "white", backgroundColor: "rgba(0, 0, 0, 0.8)" }}
            className="caption"
          >
            <h3>영유아부터 어르신까지</h3>
            <p>
              {" "}
              나이에 따른 사용자 맞춤형 서비스를 제공합니다.
              <br /> S.with 서비스를 이용하는 것은 어떠신가요?
            </p>
          </Carousel.Caption>
        </Carousel.Item>

        <Carousel.Item>
          <img src={box} alt="image1" height="500" />
          <Carousel.Caption
            style={{
              color: "white",
              backgroundColor: "rgba(0, 0, 0, 0.8)",
              marginBottom: "370px",
              width: "500px",
              margin: "345px auto",
            }}
            className="caption"
          >
            <h3>안전사고가 일어나도 걱정마세요!</h3>
            <p>
              {" "}
              S.with 서비스는 위험 상황 발생 즉시 알림을 보내 골든 타임을
              확보하고,
              <br />
              빠른 대처를 통해 2차 사고 예방이 가능합니다.
            </p>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>
    </center>
  );
};

const HOME = () => {
  const session = localStorage.getItem("toekn");
  const [sessionId, setSessionId] = useState(false);
  const featureDetail1Ref = useRef(null);
  const featureDetail2Ref = useRef(null);

  const handleScrollToFeatureDetail1 = () => {
    if (featureDetail1Ref.current) {
      featureDetail1Ref.current.scrollIntoView({ behavior: "smooth" });
    }
  };

  const handleScrollToFeatureDetail2 = () => {
    if (featureDetail2Ref.current) {
      featureDetail2Ref.current.scrollIntoView({ behavior: "smooth" });
    }
  };

  useEffect(() => {
    if (session && session !== "null") {
      setSessionId(true);
    }
  }, []);

  return (
    <div className="main_page" style={{ width: "100%", height: "100%" }}>
      <div style={{ marginBottom: "100px" }}>
        <Myheader isLogin={sessionId} />
      </div>

      <div style={{ marginBottom: "50px" }}>
        <UncontrolledExample />
      </div>

      <div className="main_feature">
        <div style={{ flex: 1 }}>
          <div className="feature_name">
            <center>
              <b>WIFI Signal</b>
            </center>
          </div>
          <div
            class=""
            style={{
              display: "flex",
              "flex-direction": "column",
              "align-items": "center",
              "justify-content": "center",
              height: "200px",
            }}
          >
            <svg
              viewBox="-4.5 0 99 99"
              xmlns="http://www.w3.org/2000/svg"
              fill="#000000"
            >
              <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                stroke-linecap="round"
                stroke-linejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                {" "}
                <g
                  id="Wifi_Modem"
                  data-name="Wifi Modem"
                  transform="translate(-715.03 -211.142)"
                >
                  {" "}
                  <path
                    id="Path_61"
                    data-name="Path 61"
                    d="M715.03,256.144a45,45,0,1,1,45,45A45,45,0,0,1,715.03,256.144Z"
                    fill="#b6c6ad"
                  ></path>{" "}
                  <g id="Group_25" data-name="Group 25">
                    {" "}
                    <g id="Group_22" data-name="Group 22">
                      {" "}
                      <path
                        id="Path_62"
                        data-name="Path 62"
                        d="M750.607,242.788a13.224,13.224,0,0,1,9.415-3.9h.005a13.25,13.25,0,0,1,9.427,3.909,3,3,0,0,0,4.243-4.241,19.2,19.2,0,0,0-13.668-5.668h-.007a19.177,19.177,0,0,0-13.659,5.661,3,3,0,0,0,4.244,4.24Z"
                        fill="#293a56"
                      ></path>{" "}
                    </g>{" "}
                    <g id="Group_23" data-name="Group 23">
                      {" "}
                      <path
                        id="Path_63"
                        data-name="Path 63"
                        d="M760.066,244.117a13.161,13.161,0,0,0-9.376,3.89,3,3,0,1,0,4.247,4.238,7.2,7.2,0,0,1,5.129-2.128h0a7.242,7.242,0,0,1,5.153,2.143,3,3,0,0,0,4.248-4.237,13.2,13.2,0,0,0-9.4-3.906Z"
                        fill="#293a56"
                      ></path>{" "}
                    </g>{" "}
                    <g id="Group_24" data-name="Group 24">
                      {" "}
                      <path
                        id="Path_64"
                        data-name="Path 64"
                        d="M798.227,258.39a3,3,0,0,0-3.948,1.555l-9.65,22.2h-21.6v-23a3,3,0,0,0-6,0v23h-21.6l-9.651-22.2a3,3,0,1,0-5.5,2.393l8.614,19.81a8.381,8.381,0,0,0-8.307,9.241l.913,10.049a9.765,9.765,0,0,0,9.531,8.7h58a9.764,9.764,0,0,0,9.531-8.7l.914-10.049a8.381,8.381,0,0,0-8.307-9.241l8.613-19.81A3,3,0,0,0,798.227,258.39ZM793.5,290.846l-.913,10.048a3.74,3.74,0,0,1-3.556,3.248h-58a3.739,3.739,0,0,1-3.555-3.248l-.914-10.048a2.4,2.4,0,0,1,2.469-2.7h14.5v5a3,3,0,0,0,6,0v-5h3v5a3,3,0,0,0,6,0v-5h3v5a3,3,0,0,0,6,0v-5h3v5a3,3,0,0,0,6,0v-5h14.5a2.395,2.395,0,0,1,2.469,2.7Z"
                        fill="#293a56"
                      ></path>{" "}
                    </g>{" "}
                  </g>{" "}
                </g>{" "}
              </g>
            </svg>
          </div>
        </div>
        <div className="plus_icon">
          <svg
            width="120px"
            height="120px"
            viewBox="0 0 24 24"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
            <g
              id="SVGRepo_tracerCarrier"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></g>
            <g id="SVGRepo_iconCarrier">
              {" "}
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M1 12C1 5.92487 5.92487 1 12 1C18.0751 1 23 5.92487 23 12C23 18.0751 18.0751 23 12 23C5.92487 23 1 18.0751 1 12ZM12.5 5.5C13.0523 5.5 13.5 5.94772 13.5 6.5V10.5H17.5C18.0523 10.5 18.5 10.9477 18.5 11.5V12.5C18.5 13.0523 18.0523 13.5 17.5 13.5H13.5V17.5C13.5 18.0523 13.0523 18.5 12.5 18.5H11.5C10.9477 18.5 10.5 18.0523 10.5 17.5V13.5H6.5C5.94772 13.5 5.5 13.0523 5.5 12.5V11.5C5.5 10.9477 5.94772 10.5 6.5 10.5H10.5V6.5C10.5 5.94772 10.9477 5.5 11.5 5.5H12.5Z"
                fill="#000000"
              ></path>{" "}
            </g>
          </svg>
        </div>
        <div style={{ flex: 1 }}>
          <div className="feature_name">
            <center>
              <b>Home Camera</b>
            </center>
          </div>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "center",
              height: "200px",
            }}
          >
            <img src={homecamera} width={"50%"} height={"100%"} alt="Image 3" />
          </div>
        </div>
      </div>

      <div className="hr_line">
        <center>
          <hr style={{ width: "80%" }} />
        </center>
      </div>
      <div className="for_WHO">
        <center>
          <img src={for_who} style={{ marginBottom: "20px" }} />
        </center>
      </div>
      <div>
        <div
          className="feature_detail"
          id="feature_detail1"
          ref={featureDetail1Ref}
        >
          <div className="feature_detail1">
            <img
              src={wifi_s}
              style={{ height: "85%", width: "90%", marginLeft: "30px" }}
            />
          </div>
          <div className="feature_detail1_picture">
            <img src={wifi} style={{ height: "400px" }} alt="asdf" />
          </div>
        </div>
      </div>

      <div className="hr_line">
        <center>
          <hr style={{ width: "80%" }} />
        </center>
      </div>
      <div>
        <div
          className="feature_detail"
          id="feature_detail2"
          ref={featureDetail2Ref}
        >
          <div className="feature_detail2_picture">
            <img src={skecamera} style={{ height: "400px" }} alt="asdf" />
          </div>
          <div className="feature_detail2">
            <img src={camera_detec} style={{ height: "90%" }} />
          </div>
        </div>
      </div>

      <div className="hr_line">
        <center>
          <hr style={{ width: "80%" }} />
        </center>
      </div>
      <ScrollToTopButton />
    </div>
  );
};
export default HOME;
