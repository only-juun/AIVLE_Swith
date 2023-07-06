import React, { useEffect, useState, useRef } from "react";
import emailjs from "@emailjs/browser";

import Myheader from "../components/header";
import "bootstrap/dist/css/bootstrap.min.css";
import { Accordion } from "react-bootstrap";
import "../style/accordion.css";

const FAQ = () => {
  const session = localStorage.getItem("token");
  const [sessionId, setSessionId] = useState(false);

  useEffect(() => {
    if (session && session !== "null") {
      setSessionId(true);
    }
  }, [session]);

  const form = useRef();
  const sendEmail = (e) => {
    e.preventDefault();

    if (window.confirm("1:1 문의를 접수하시겠습니까?")) {
      emailjs
        .sendForm(
          "cs.s.with@gmail.com",
          "template_2md8tjf",
          form.current,
          "DvatRb3CY3WIvoXSp"
        )
        .then(
          (result) => {
            alert(
              "성공적으로 접수되었습니다. \n(주말/ 공휴일/ 평일 19시 이후는 답변이 늦어질 수 있습니다.)"
            );
            form.current.reset();
          },
          (error) => {
            alert("전송이 실패되었습니다.");
          }
        );
    }
  };

  return (
    <div>
      <Myheader isLogin={sessionId} />
      <center>
        <div style={{ width: "65%", marginTop: "100px", marginBottom: "50px" }}>
          <h1>F A Q</h1>
        </div>
      </center>
      <div className="question">
        <div
          className="faq"
          style={{
            width: "50%",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center"
          }}
        >
          <h2 style={{ fontWeight: "bold", marginTop: "25px" }}>자주묻는질문</h2>
          <div className="faq_accordion" style={{ width: "80%", marginTop: "-48px" }}>
            <Accordion defaultActiveKey="0" className="mt-5 p-3">
              <Accordion.Item eventKey="0" className="item">
                <Accordion.Header>
                  카메라 찰영 관련해서 사생활 침해 문제는 없을까요?
                </Accordion.Header>
                <Accordion.Body>
                  저희 서비스는 고객의 개인 정보와 사생활을 보호하기 위해 영상을
                  어디에도 저장하지 않습니다. 카메라가 작동하는 동안 촬영된
                  영상은 실시간으로만 전송되고 저장되지 않습니다. 로그에서 볼 수
                  있는 사진은 이상 현상을 감지할 때만 기록이 생성됩니다. 이
                  경우, 해당 이벤트가 발생한 시간의 사진 또는 기록만이 생성되며,
                  그 외에는 아무런 정보도 기록되지 않습니다.
                </Accordion.Body>
              </Accordion.Item>
              <Accordion.Item eventKey="2" className="item">
                <Accordion.Header>화면이 멈췄어요.</Accordion.Header>
                <Accordion.Body>
                  인터넷 연결이 불안정 하거나 카메라 전원에 이상이 있는 경우
                  화면이 멈춰 있을수도 있습니다. 카메라와 연결된 와이파이 연결
                  또는 카메라 전원이 정상적으로 연결되어 있는지 확인
                  부탁드립니다. 이후에도 정상작동하지 않는 경우 1:1 문의를
                  주시면 최대한 빠르게 연락드리겠습니다. 감사합니다.
                </Accordion.Body>
              </Accordion.Item>
              <Accordion.Item eventKey="3" className="item">
                <Accordion.Header>서비스 성능이 궁금해요!</Accordion.Header>
                <Accordion.Body>
                  S.with는 직접 개발한 딥러닝 알고리즘을 통하여 다양한
                  위험패턴과 상항을 식별할 수 있도록 학습되어 있습니다. 저희
                  AI모델은 지속적인 학습과 개선을 통해 성능을 향상시키고
                  있습니다. 다른 서비스와 달리 wifi데이터를 카메라 데이터와 함께
                  사용하여 더욱더 정확한 서비스를 제공합니다.
                </Accordion.Body>
              </Accordion.Item>
              <Accordion.Item eventKey="4" className="item">
                <Accordion.Header>
                  카메라가 고장나서 새로운 카메라를 받는 경우에는 설정해야 하는
                  부분이 있나요?
                </Accordion.Header>
                <Accordion.Body>
                  기기마다 고유의 시리얼 넘버를 가지고 있기 때문에 기기가
                  변경되는 경우 Mypage에서 새로 받은 기기의 시리얼번호를
                  등록해야 기존의 서비스를 계속해서 사용하실 수 있습니다. 추가
                  문의 사항이 있으시면 1:1 문의로 연락드리면 자세히
                  설명드리겠습니다.
                </Accordion.Body>
              </Accordion.Item>
              <Accordion.Item eventKey="5" className="item">
                <Accordion.Header>
                  연령대와 카메라 이용 시간대는 왜 입력해야 하나요?
                </Accordion.Header>
                <Accordion.Body>
                  연령대와 카메라 이용 시간대에 따라 AI가 감지해야할 위험 행동과
                  비상상황 주의 시간대가 달라집니다. 이는 각 사용자에 최적화된
                  안전 솔루션을 제공하기 위함이며 이러한 정보를 입력할 경우 보다
                  높은 성능의 AI 서비스를 제공받으실 수 있습니다.
                </Accordion.Body>
              </Accordion.Item>
            </Accordion>
          </div>
        </div>
        <div className="email_contact">
          <h2 className="em_title">1:1 문의</h2>
          <form ref={form} onSubmit={sendEmail}>
            <label>연락 받을 Email</label>
            <br />
            <input
              type="email"
              name="from_email"
              placeholder="ex)abcd@gmail.com"
              required
              className="faq_input"
            />
            <br />
            <label>문의 제목</label>
            <br />
            <input
              type="text"
              name="mail_title"
              maxLength={25}
              placeholder="제목을 입력해주세요. (25자 이내)"
              className="faq_input"
            />
            <br />
            <label>문의 내용</label>
            <br />
            <textarea
              name="mail_content"
              required
              maxLength={3000}
              placeholder="문의 내용을 입력해주세요 (3000자 이내)"
            />
            <br />
            <input
              type="submit"
              value="Send"
              style={{ marginTop: "10px", marginLeft: "20px" }}
              className="submitEmail"
            />
          </form>
        </div>
      </div>
    </div>
  );
};

export default FAQ;
