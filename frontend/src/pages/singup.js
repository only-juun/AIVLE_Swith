import { Fragment, useState, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import Myheader from "../components/header";
import { Button } from "react-bootstrap";
import Modal from "react-bootstrap/Modal";
import axios from "axios";

import "../style/singup.css";
import jaerong from "../img/jaerong.png";

const SIGNUP = () => {
  const navigate = useNavigate();
  const [isCheckOne, setIsCheckOne] = useState(false);
  const [isCheckTwo, setIsCheckTwo] = useState(false);
  const isCheck = isCheckOne && isCheckTwo;
  const termsOfService1 = `
  [S.with] IoT 홈카메라 서비스 이용약관

     이 이용약관은 [S.with]이 제공하는 IoT 홈카메라 서비스의 이용 조건을 규정합니다. 이 약관에 동의함으로써, 사용자는 [S.with]의 서비스를 이용함에 있어서 아래 기재된 약관과 정책을 준수해야 합니다.

     1. 개인 정보 수집 및 이용
        1.1. [S.with]은 서비스 제공을 위해 필요한 범위 내에서 사용자의 개인 정보를 수집할 수 있습니다. 개인
            정보 수집은 명시적 동의를 통해 이루어지며, 수집되는 정보는 개인 식별이 가능한 정보를 포함합니다.
            
        1.2. 수집한 개인 정보는 다음 목적을 위해 사용될 수 있습니다.
              ㆍ  서비스 제공 및 운영
              ㆍ  고객 지원 및 문의 응답
              ㆍ  서비스 개선 및 개발
              ㆍ  법적 의무의 준수
        
        1.3. [S.with]은 사용자의 개인 정보를 제 3자와 공유하지 않습니다. 다만, 다음과 같은 경우에는 제 3자와
            의 정보 공유가 이루어질 수 있습니다.
              ㆍ  사용자의 명시적인 동의가 있는 경우
              ㆍ  법적인 의무에 따라 제공이 요구되는 경우
    
    2. 개인 정보 보호
        2.1. [S.with]은 사용자의 개인 정보 보호를 위해 상업적으로 합리적인 보안 조치를 취하고 있습니다. 
            그러나 완전한 보안을 보장할 수는 없으며, 사용자는 개인 정보보호를 위해 추가적인 조치를 취할 책임
            이 있습니다.
        
        2.2. 사용자의 계정 정보는 사용자 본인만이 사용할 수 있으며, 사용자는 타인과 계정 정보를 공유하거나
            타인의 계정을 무단으로 사용하지 않아야 합니다.

    3. 저작권
        3.1. [S.with]이 제공하는 서비스에 포함된 모든 콘텐츠는 [S.with] 또는 해당 콘텐츠 제공자에게 저작권
            이 있습니다. 사용자는 이 콘텐츠를 복제, 배포, 수정, 전송, 판매하거나 상업적으로 이용하는 것을 금지
            합니다.
      
    4. 손해 배상 및 면책
        4.1. [S.with]은 서비스 이용으로 인해 발생하는 어떠한 손해에 대해서도 책임지지 않습니다. 사용자는 
            서비스 이용에 따른 위험과 책임을 스스로 부담해야 합니다.

        4.2. [S.with]은 합리적인 수준의 서비스 제공을 위해 노력하고 있으며, 서비스 중단, 장애 또는 기타 문제
            로 인해 발생하는 어떠한 손해에 대해서도 책임을 지지 않습니다.
      
    5. 약관 변경
        5.1. [S.with]은 이용약관을 언제든지 수정할 수 있으며, 수정된 약관은 서비스 내에서 공지됩니다. 사용
            자는 수정된 약관에 동의하지 않을 경우 서비스 이용을 중단해야 합니다.
            

  [S.with] 개인 정보 이용 동의 약관

    1. 개인 정보 수집 및 이용 목적
        1.1. [S.with]은 고객의 개인 정보를 다음의 목적을 위해 수집 및 이용합니다.
              ㆍ  홈카메라 서비스 제공을 위한 고객 식별 및 인증
              ㆍ  홈카메라로 촬영한 영상 및 사진 저장 및 관리
              ㆍ  고객의 요청에 따른 기타 서비스 제공 및 관리
        
        1.2. 개인 정보는 오직 목적을 위해 필요한 범위에서만 수집 및 이용될 것입니다.

    2. 수집하는 개인 정보의 범위
        2.1. [S.with]은 다음과 같은 개인 정보를 수집할 수 있습니다.
              ㆍ  개인 식별 정보 : 성명, 주소, 전화번호, 이메일 주소 등
              ㆍ  홈카메라로 촬영된 영상 및 사진

    3. 개인 정보 보유 및 이용 기간
        3.1. [S.with]은 개인 정보를 이용자가 회원 탈퇴를 요청하거나 별도의 이용 기간 설정이 없는 한, 서비스
            제공을 위해 필요한 기간 동안 보유 및 이용합니다.

        3.2. 개인 정보의 보유 및 이용 기간이 종료되면, 해당 정보는 안전하게 파기됩니다.
    
    4. 개인 정보의 제 3자 제공
        4.1. [S.with]은 이용자의 개인 정보를 제 3자에게 제공하지 않습니다. 단, 다음의 경우에는 제 3자에게 
            개인 정보를 제공할 수 있습니다.
              ㆍ 이용자의 동의가 있는 경우
              ㆍ 법령에 따라 제공이 요구되는 경우
      
    5. 개인 정보의 보호 및 안전 조치
        5.1. [S.with]은 이용자의 개인 정보를 보호하기 위해 관련 법령에서 요구하는 적절한 보안 조치를 취하고
            있습니다.

    6. 이용자의 권리
        6.1. 이용자는 언제든지 개인 정보에 대한 열람, 정정, 삭제를 요청할 수 있습니다. 또한, 개인 정보 이용에
            대한 동의 철회를 요청할 수 있습니다.

        6.2. 이용자는 개인 정보 이용과 관련된 불만 사항을 제기할 수 있으며, [S.with]은 신속하고 성실하게 이
            에 대응할 것입니다.

    7. 기타
        7.1. 본 약관은 [S.with]과 이용자 간의 관계를 규정합니다.

        7.2. 본 약관은 관련 법령의 변경이나 [S.with]의 정책 변동 등에 따라 수정될 수 있습니다. 이용자는 수정
            된 약관을 수시로 확인하여 준수해야 합니다.
    `;

  const termsOfService2 = `
   [S.with] 개인 정보 보호 약관

     [S.with] 계정을 생성하기 전에 [S.with]의 약관을 주의 깊게 읽어보시기 바랍니다. [S.with] 계정을 생성
    하기 위해서는 IoT 홈카메라 서비스 이용약관, 개인 정보 이용 동의 약관에 동의 해야 합니다.

     [S.with]의 서비스 약관에는 사용자와 [S.with]의 관계, [S.with] 서비스에서 제공하는 콘텐츠와 소프트웨
    어의 사용, 분쟁 해결을 포함하지만 이에 국한되지 않는 중요한 내용이 설명되어 있습니다.

     또한 계정을 만들 때 [S.with]에서는 다음과 같은 주요 사항을 포함하여 [S.with] 개인정보처리방침에 설명
    된 바와 같이 사용자의 정보를 처리합니다.

    ㆍ사용자가 [S.with]을 사용할 때 [S.with]에서 처리하는 데이터
        - [S.with] 계정을 설정할 때 제공하신 이름, 메일 주소, 전화번호, 시리얼번호와 같은 정보가 저장됩니다.
        - 사용자가 게시판에서 게시글을 작성하거나 게시글에 댓글을 다는 등의 활동을 하기 위해 [S.with] 서비
          스를 사용하면 [S.with]은 사용자가 만든 정보를 저장합니다.

    ㆍ정보의 보유기간
        - 사용자가 원할 경우 언제든지 삭제할 수 있는 데이터가 있는 반면 자동으로 삭제되는 데이터도 있으며,
          필요한 경우 [S.with]이 오랜 기간 동안 보관하는 데이터도 있습니다. 사용자가 데이터를 삭제하면
          [S.with]은 삭제 정책에 따라 데이터를 [S.with] 서버에서 안전하고 확실하게 삭제하거나 익명 상태로만
          보관합니다.
  `;
  const nameRef = useRef();
  const nicknameRef = useRef();
  const emailRef = useRef();
  const confirmPwRef = useRef();
  const ageRef = useRef();
  const serialnumberRef = useRef();
  const phonenumberRef = useRef();

  const [idInfo, setIdInfo] = useState({
    name: "",
    id: "",
    pw: "",
    checkpw: "",
    serialnumber: "",
    phone: "",
    email: "",
    agenumber: "",
  });

  const [showpw, setShowPw] = useState("password");
  const nameRegex = /^[A-Za-z가-힣]+$/;
  const pwRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,30}$/;
  const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
  const [showNameTooltip, setShowNameTooltip] = useState(false);
  const [, setNameErrorMessage] = useState("");
  const [showEmailTooltip, setShowEmailTooltip] = useState(false);
  const [emailErrorMessage, setEmailErrorMessage] = useState("");
  const [, setShowPwTooltip] = useState(false);

  const handleEmailChange = (e) => {
    const { value } = e.target;

    if (!value) {
      setEmailErrorMessage("이메일을 입력해주세요.");
      setShowEmailTooltip(true);
    } else if (!emailRegex.test(value)) {
      setEmailErrorMessage("올바른 이메일 형식이 아닙니다.(example@naver.com)");
      setShowEmailTooltip(true);
    } else {
      setEmailErrorMessage("");
      setShowEmailTooltip(false);
    }

    setIdInfo({ ...idInfo, email: value });
  };

  const handleEmailError = () => {
    setShowEmailTooltip(true);
    emailRef.current.focus({ preventScroll: true });
  };

  const handlePwError = () => {
    setShowPwTooltip(true);
  };

  const handleSignUp = (e) => {
    e.preventDefault();

    if (!emailRegex.test(idInfo.email)) {
      handleEmailError();
      setShowEmailTooltip(true);
      return;
    }
    if (!pwRegex.test(idInfo.pw)) {
      handlePwError();
      setShowPwTooltip(true);
      return;
    }
  };

  const [errors, setErrors] = useState({
    name: "",
    email: "",
    pw: "",
    checkpw: "",
  });

  const setinfo = (e) => {
    const { name, value } = e.target;
    switch (name) {
      case "name":
        if (!nameRegex.test(value)) {
          setErrors((prevErrors) => ({
            ...prevErrors,
            name: "이름에는 숫자나 특수문자를 포함할 수 없습니다.",
          }));
        } else {
          setErrors((prevErrors) => ({
            ...prevErrors,
            name: "",
          }));
        }
        break;
      case "pw":
        if (!pwRegex.test(value)) {
          setErrors((prevErrors) => ({
            ...prevErrors,
            pw: "비밀번호는 8 ~ 30자 사이, 숫자 1개 이상, 특수문자 1개 이상 포함해야 합니다.",
          }));
        } else {
          setErrors((prevErrors) => ({
            ...prevErrors,
            pw: "",
          }));
        }
        break;
      case "email":
        if (!emailRegex.test(value)) {
          setErrors((prevErrors) => ({
            ...prevErrors,
            email: "이메일 형식이 올바르지 않습니다. (example@naver.com)",
          }));
        } else {
          setErrors((prevErrors) => ({
            ...prevErrors,
            email: "",
          }));
        }
        break;
      default:
        break;
    }

    setIdInfo({ ...idInfo, [name]: value });
  };

  const handleNameChange = (e) => {
    const { value } = e.target;

    if (!value) {
      setNameErrorMessage("");
      setShowNameTooltip(false);
    } else if (!nameRegex.test(value)) {
      setNameErrorMessage("이름에는 숫자나 특수문자를 포함할 수 없습니다.");
      setShowNameTooltip(true);
    } else {
      setNameErrorMessage("");
      setShowNameTooltip(false);
    }

    setIdInfo({ ...idInfo, name: value });
  };

  const showPwBt = () => {
    showpw === "password" ? setShowPw("text") : setShowPw("password");
  };

  const newRegis = (e) => {
    e.preventDefault();
    if (!isCheckOne || !isCheckTwo) {
      alert("이용 약관에 모두 동의해주세요.");
      return;
    }

    const pwRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,30}$/;
    if (!pwRegex.test(idInfo.pw)) {
      alert(
        "비밀번호는 8 ~ 30자 사이, 숫자 1개 이상, 특수문자 1개 이상 포함해야 합니다."
      );
      return;
    }

    if (idInfo.pw !== idInfo.checkpw) {
      alert("비밀번호가 일치하지 않습니다.");
      confirmPwRef.current.focus();
      return;
    }

    const age = Number(idInfo.agenumber);
    if (isNaN(age) || age < 0 || age > 121) {
      alert("연령은 0~120 사이의 값이어야 합니다.");
      ageRef.current.focus();
      return;
    }

    const emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
    if (!emailRegex.test(idInfo.email)) {
      alert("이메일 형식이 올바르지 않습니다. (example@naver.com)");
      emailRef.current.focus();
      return;
    }

    axios({
      method: "post",
      url: "http://15.165.98.14:8080/users/signup",
      data: {
        name: idInfo.name,
        nickname: idInfo.id,
        password: idInfo.pw,
        serialNumber: idInfo.serialnumber,
        phoneNumber: idInfo.phone,
        email: idInfo.email,
      },
    })
      .then((res) => {
        alert("회원가입 성공!");
        navigate("/login");
      })
      .catch((err) => {
        if (err.response) {
          if (err.response.data.message === "Nickname already in use") {
            window.alert("중복된 닉네임입니다.");
            nicknameRef.current.focus();
          } else if (err.response.data.message === "Email address already in use") {
            window.alert("중복된 이메일입니다.");
            emailRef.current.focus();
          } else if (err.response.data.message === "Phone number already in use") {
            window.alert("중복된 전화번호입니다.");
            phonenumberRef.current.focus();
          }
        } else {
          window.alert("서버가 꺼져 있습니다.");
        }
      });
  };

  const [showModal, setShowModal] = useState(false);
  const previewImgage = jaerong;

  return (
    <div className="signup">
      <Myheader />
      <Fragment>
        <div className="signup_container">
          <div className="register_frame">
            <Link to="/login">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="30"
                height="30"
                fill="currentColor"
                className="bi bi-arrow-left Arrows"
                viewBox="0 0 16 16"
              >
                <path
                  fillRule="evenodd"
                  d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"
                />
              </svg>
            </Link>
            <div className="register_Area">
              <form onSubmit={newRegis}>
                <div
                  onClick={() =>
                    !isCheck && alert("이용 약관에 모두 동의해주세요.")
                  }
                >
                  <div className="nametooltipContainer">
                    <input
                      ref={nameRef}
                      type="text"
                      className="nameInput"
                      required
                      minLength={2}
                      placeholder="이름(2글자 이상)"
                      name="name"
                      pattern="^[A-Za-z\uAC00-\uD7A3]+$"
                      onChange={handleNameChange}
                      disabled={!isCheck}
                    />
                    {showNameTooltip && (
                      <div className="tooltiptext">
                        이름에는 숫자나 특수문자를 포함할 수 없습니다.
                      </div>
                    )}
                  </div>
                  <div className="emailtooltipContainer">
                    <input
                      ref={emailRef}
                      type="email"
                      className="emailInput"
                      required
                      placeholder="아이디(example@gmail.com)"
                      name="email"
                      onChange={handleEmailChange}
                      disabled={!isCheck}
                    />
                    {showEmailTooltip && (
                      <div className="tooltiptext">{emailErrorMessage}</div>
                    )}
                  </div>

                  <div className="pwdtooltipContainer">
                    <input
                      type={showpw}
                      className="pwInput"
                      required
                      minLength="8"
                      placeholder="비밀번호(8글자 이상)"
                      name="pw"
                      onChange={setinfo}
                      disabled={!isCheck}
                    />
                    {errors.pw && (
                      <div className="tooltiptext">{errors.pw}</div>
                    )}
                  </div>

                  <input
                    ref={confirmPwRef}
                    type={showpw}
                    className="pwInput"
                    required
                    minLength="8"
                    placeholder="비밀번호 확인"
                    name="checkpw"
                    onChange={setinfo}
                    disabled={!isCheck}
                  />

                  <input
                    ref={nicknameRef}
                    type="text"
                    className="idInput"
                    required
                    minLength="4"
                    placeholder="닉네임(4글자 이상)"
                    name="id"
                    onChange={setinfo}
                    disabled={!isCheck}
                  />

                  <input
                    ref={phonenumberRef}
                    type="text"
                    className="phoneInput"
                    required
                    pattern="\d{11}"
                    maxLength="11"
                    placeholder="전화번호('-'없이 11자리)"
                    name="phone"
                    onChange={setinfo}
                    disabled={!isCheck}
                  />
                  <hr
                    className="hr"
                    style={{ marginBottom: "0px", marginTop: "15px" }}
                  />

                  <input
                    ref={serialnumberRef}
                    type="text"
                    className="serialnumberInput"
                    required
                    placeholder="시리얼넘버"
                    name="serialnumber"
                    onChange={setinfo}
                    disabled={!isCheck}
                  />
                  <Button
                    className="btn-custom"
                    onClick={() => {
                      if (isCheck) {
                        setShowModal(true);
                      }
                    }}
                  >
                    시리얼 번호 안내
                  </Button>

                  <hr
                    className="hr"
                    style={{ marginBottom: "0px", marginTop: "15px" }}
                  />

                  <div className="tooltipContainer ageInputContainer">
                    <input
                      ref={ageRef}
                      type="number"
                      min="0"
                      max="120"
                      className="ageInput"
                      required
                      placeholder="연령(00)"
                      name="agenumber"
                      onChange={setinfo}
                      disabled={!isCheck}
                    />
                    <div className="tooltiptext">
                      영유아와 고령자한테 제공되는
                      <br />
                      알림 종류가 각각 다릅니다!
                    </div>
                  </div>

                  <div className="timeinput_area">
                    <div className="tooltipContainer timeInputContainer">
                      <input
                        type="time"
                        className="timeInput"
                        required
                        name="timenumber1"
                        onChange={setinfo}
                        disabled={!isCheck}
                      />
                      &nbsp; ~ &nbsp; &nbsp; ~ &nbsp;
                      <input
                        type="time"
                        className="timeInput"
                        required
                        name="timenumber2"
                        onChange={setinfo}
                        disabled={!isCheck}
                      />
                      <div className="tooltiptext">
                        이용 시간대를 설정해주세요.
                      </div>
                    </div>
                  </div>
                </div>

                <label className="showPw">
                  <input
                    type="checkbox"
                    className="showPw"
                    onClick={showPwBt}
                    disabled={!isCheck}
                  />{" "}
                  비밀번호 표시
                </label>
                <br />
                <span className="pwChecking">
                  <b>
                    <u>닉네임, 아이디는 회원 가입 후 변경 불가</u>
                  </b>
                </span>
                <button type="submit" className="register_sbButton">
                  회원가입
                </button>
              </form>
            </div>
          </div>
          <div className="register_terms_of_service">
            <div>
              <h1 className="h3 mb-3 fw-normal">이용 약관</h1>
              <textarea
                style={{ resize: "none", width: "86%", height: "250px" }}
                value={termsOfService1}
                readOnly
              ></textarea>
              <div className="checkbox-container">
                &nbsp;&nbsp;&nbsp;개인 정보 수집 및 이용 약관에
                동의합니다.&nbsp;
                <input
                  type="checkbox"
                  onChange={() => setIsCheckOne(!isCheckOne)}
                />
              </div>
              <br />
              <br />
              <br />
              <textarea
                style={{ resize: "none", width: "86%", height: "250px" }}
                value={termsOfService2}
                readOnly
              ></textarea>
              <div className="checkbox-container">
                &nbsp;&nbsp;&nbsp;개인 정보 보호 약관에 동의합니다.&nbsp;
                <input
                  type="checkbox"
                  onChange={() => setIsCheckTwo(!isCheckTwo)}
                />
              </div>
            </div>
          </div>
        </div>
        <Modal show={showModal} onHide={() => setShowModal(false)}>
          <Modal.Header closeButton>
            <Modal.Title>시리얼 번호 안내</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {previewImgage && (
              <img
                src={previewImgage}
                alt="Preview"
                style={{ width: "100%" }}
              />
            )}
            <p>
              시리얼 번호는 기기 고유 번호로, 특정 기기에만 사용되는 번호입니다.
              시리얼 번호를 입력하면 해당 기기에 대한 정보를 확인할 수 있습니다.
            </p>
            <p>
              시리얼 번호는 기기 상단 또는 하단에 있는 레이블 또는 포장 상자에
              기록되어 있습니다.
            </p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowModal(false)}>
              닫기
            </Button>
          </Modal.Footer>
        </Modal>
      </Fragment>
    </div>
  );
};

export default SIGNUP;