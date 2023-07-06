import { useEffect, useState, useRef } from "react";
import Myheader from "../components/header";
import "../style/mypage.css";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import axios from "axios";

const MYPAGE = () => {
  const [sessionId, setSessionId] = useState(false);
  const [password, setPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [newPasswordConfirmation, setNewPasswordConfirmation] = useState(null);
  const [phoneNumber, setPhoneNumber] = useState("");
  const [name, setName] = useState("");
  const [serialNumber, setSerialNumber] = useState("");
  const [, setAgeNumber] = useState("");
  const [verified, setVerified] = useState(false);
  const [, setCurrentUser] = useState(null);
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const [nickname, setNickname] = useState("");
  const [userEmail, setUserEmail] = useState("");

  useEffect(() => {
    axios({
      method: "get",
      url: `http://15.165.98.14:8080/users/user`,
      headers: {
        Authorization: `Bearer ${JSON.parse(localStorage.getItem("token")).accessToken
          }`,
      },
    }).then((res) => {
      setUserEmail(res.data.email);
      setNickname(res.data.nickname);
      setSerialNumber(res.data.serialNumber);
      setName(res.data.name);
      setPhoneNumber(res.data.phoneNumber);
      setSessionId(true);
    });
  }, []);

  const verifyPassword = (e) => {
    e.preventDefault();

    axios({
      method: "post",
      url: "http://15.165.98.14:8080/users/check",
      data: {
        email: userEmail,
        password: password,
      },
      headers: {
        Authorization: `Bearer ${JSON.parse(localStorage.getItem("token")).accessToken
          }`,
      },
    })
      .then((res) => {
        setVerified(true);
        setCurrentUser(true);
      })
      .catch((err) => {
        window.alert("비밀번호가 일치하지 않습니다!");
      });
  };

  const confirmPwRef = useRef();
  const ageRef = useRef();
  const nicknameRef = useRef();

  const updateUserInfo = (e) => {
    e.preventDefault();


    if (newPassword !== newPasswordConfirmation) {
      window.alert("비밀번호도 같이 변경해주세요!");
      return;
    }


    const pwRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,30}$/;
    if (newPassword && !pwRegex.test(newPassword)) {
      window.alert(
        "비밀번호는 특수문자, 숫자를 포함한 8글자 이상이어야 합니다!"
      );
      return;
    }

    if (nickname.length < 4) {
      window.alert("닉네임은 4글자 이상이어야 합니다!");
      return;
    }


    axios({
      method: "put",
      url: `http://15.165.98.14:8080/users/edit`,
      data: {
        nickname: nickname,
        password: newPasswordConfirmation,
        serialNumber: serialNumber,
      },
      headers: {
        Authorization: `Bearer ${JSON.parse(localStorage.getItem("token")).accessToken
          }`,
      },
    })
      .then((res) => {
        window.alert("회원정보 수정 완료!");
        navigate("/service", { replace: true });
      })
      .catch((err) => {
        window.alert("회원정보 수정 실패");
      });
  };

  const renderContent = () => {
    if (sessionId) {
      if (verified) {
        return (
          <div className="mypage_main">
            <center>
              <div className="mypageFrame">
                <Link to="/service">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="25"
                    height="25"
                    fill="currentColor"
                    style={{
                      position: "absolute",
                      left: "37px",
                      top: "30px",
                      cursor: "pointer",
                      backgroundColor: "rgb(242, 242, 242)",
                      padding: "4px",
                      borderRadius: "10px",
                      textDecoration: "none",
                      fill: "black",
                      transition: "all ease-in-out 0.15s",
                    }}
                    viewBox="0 0 16 16"
                  >
                    <path
                      fillRule="evenodd"
                      d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"
                    />
                  </svg>
                </Link>
                <div className="editArea">
                  <form onSubmit={updateUserInfo}>
                    <div>
                      <input
                        ref={nicknameRef}
                        type="text"
                        className="idInput"
                        value={nickname}
                        placeholder="닉네임: (4글자 이상)"
                        onChange={(e) => setNickname(e.target.value)}
                        minLength="4"
                      />
                      <input
                        type="email"
                        className="emailInput"
                        placeholder={`이메일: ${userEmail}`}
                        disabled
                      />
                      <input
                        type={showPassword ? "text" : "password"}
                        className="pwInput"
                        placeholder="비밀번호 변경(특수문자, 영어 소문자, 숫자 포함 8글자 이상)"
                        onChange={(e) => setNewPassword(e.target.value)}
                        minLength="8"
                      />
                      <input
                        ref={confirmPwRef}
                        type={showPassword ? "text" : "password"}
                        className="pwInput"
                        placeholder="비밀번호 변경(특수문자, 영어 소문자, 숫자 포함 8글자 이상)"
                        onChange={(e) =>
                          setNewPasswordConfirmation(e.target.value)
                        }
                        minLength="8"
                      />
                      <hr
                        className="hr"
                        style={{ marginBottom: "10px", marginTop: "25px" }}
                      />
                      <input
                        type="text"
                        className="nameInput"
                        placeholder={`이름: ${name}`}
                        disabled
                      />
                      <input
                        type="text"
                        className="serialnumberInput"
                        placeholder={`시리얼넘버: ${serialNumber}`}
                        disabled
                      />
                      <input
                        type="text"
                        className="phoneInput"
                        placeholder={`전화번호: ${phoneNumber}`}
                        disabled
                      />
                      <hr
                        className="hr"
                        style={{ marginBottom: "10px", marginTop: "25px" }}
                      />
                      <input
                        ref={ageRef}
                        type="text"
                        className="ageInput"
                        placeholder={`연령: ${80}`}
                        onChange={(e) => setAgeNumber(e.target.value)}
                      />
                      <div>
                        <input type="time" className="timeInput" />
                        &nbsp; ~ &nbsp;
                        <input type="time" className="timeInput" />
                      </div>
                      <hr
                        className="hr"
                        style={{ marginBottom: "10px", marginTop: "25px" }}
                      />
                    </div>
                    <label className="showPw">
                      <input
                        type="checkbox"
                        className=""
                        checked={showPassword}
                        onChange={() => setShowPassword(!showPassword)}
                      />{" "}
                      비밀번호 표시
                    </label>
                    <div style={{ marginTop: "-20px", marginBottom: "-20px" }}>
                      <br />
                      수정 할 부분을 입력하세요.
                    </div>
                    <button type="submit" className="mypage_button">
                      회원정보수정
                    </button>
                  </form>
                  <Button
                    variant="outline-danger"
                    className="withdraw_button"
                    onClick={() => {
                      if (window.confirm("정말로 탈퇴하시겠습니까?")) {
                        axios({
                          method: "delete",
                          url: `http://15.165.98.14:8080/users/withdraw/${serialNumber}`,
                          headers: {
                            Authorization: `Bearer ${JSON.parse(localStorage.getItem("token"))
                              .accessToken
                              }`,
                          },
                        }).then(() => {
                          window.alert("회원탈퇴 성공!")
                          localStorage.removeItem("token");
                          navigate("/main", { replace: true });
                        });
                      }
                    }}
                  >
                    회원탈퇴
                  </Button>
                </div>
              </div>
            </center>
          </div>
        );
      }
      return (
        <div className="mypage_pwcheck">
          <h1>회원정보수정</h1>
          <form onSubmit={verifyPassword}>
            <input
              type="password"
              style={{ textAlign: "center" }}
              className="mypagepwInput"
              placeholder="비밀번호 확인"
              onChange={(e) => setPassword(e.target.value)}
            />
            <input type="submit" className="mypage_button" value="확인" />
          </form>
        </div>
      );
    }
  };

  return (
    <div className="mypage">
      <Myheader isLogin={sessionId} />
      {renderContent()}
    </div>
  );
};

export default MYPAGE;
