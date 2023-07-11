import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../style/findid.css";
import Myheader from "../components/header";
import axios from "axios";

const FindID = () => {
  const navigate = useNavigate();
  const [serialNumber, setSerialNumber] = useState("");
  const [email, setEmail] = useState("");
  const [pwserial, setPwSerial] = useState("");

  const findEmail = (event, serial) => {
    event.preventDefault();

    axios({
      method: "get",
      url: `http://15.165.98.14:8080/users/find?serialNumber=${serial}`,
    })
      .then((res) => {
        window.alert("고객님 정보와 일치하는 아이디는 다음과 같습니다.\n" + res.data);
        setEmail(res.data);
        setSerialNumber("");
      })
      .catch(() => {
        window.alert("일치하는 아이디를 찾을 수 없습니다!");
      });
  };

  const resetPW = (e, email, pwserial) => {
    e.preventDefault();

    axios({
      method: "post",
      url: `http://15.165.98.14:8080/users/sendEmail`,
      data: {
        email: email,
        serialNumber: pwserial,
      },
    })
      .then((res) => {
        window.alert("임시 비밀번호를 이메일로 전송하였습니다.");
        setEmail("");
        setPwSerial("");
        navigate("/login");
      })
      .catch(() => {
        window.alert("일치하는 아이디를 찾을 수 없습니다!");
      });
  };

  return (
    <div className="findpassword">
      <Myheader />
      <div className="find_pwd">
        <div>
          <center style={{ marginTop: "-30px" }}>
            <Link to="/login">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="26"
                height="26"
                fill="currentColor"
                className="bi bi-arrow-left Arrows_1"
                viewBox="0 0 16 16"
              >
                <path
                  fillRule="evenodd"
                  d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"
                />
              </svg>
            </Link>
            <div className="find_id">
              <h1>아이디 찾기</h1>
              <div className="findEmailArea">
                <form onSubmit={(e) => findEmail(e, serialNumber)}>
                  <input
                    type="text"
                    className="emailInput"
                    required
                    placeholder="시리얼번호 입력"
                    value={serialNumber}
                    onChange={(e) => setSerialNumber(e.target.value)}
                    key="serialNumber"
                  />
                  <button
                    type="submit"
                    className="findid_button"
                    style={{ marginTop: "1px" }}
                  >
                    아이디 찾기
                  </button>
                </form>
              </div>
            </div>
            <div style={{ marginTop: "100px" }}>
              <h1>비밀번호 찾기</h1>
              <div className="verifyArea">
                <form
                  onSubmit={(e) => {
                    resetPW(e, email, pwserial);
                  }}
                >
                  <input
                    type="email"
                    className="emailInput"
                    required
                    placeholder="아이디(example@gmail.com)"
                    name="email"
                    onChange={(e) => {
                      setEmail(e.target.value);
                    }}
                    value={email}
                    key="email"
                  />
                  <input
                    type="text"
                    className="serialnumberInput"
                    required
                    placeholder="시리얼번호"
                    name="serialnumber"
                    onChange={(e) => {
                      setPwSerial(e.target.value);
                    }}
                    value={pwserial}
                    key="serialnumber"
                  />
                  <button
                    type="submit"
                    className="findid_button"
                    style={{ marginTop: "1px" }}
                  >
                    비밀번호 초기화
                  </button>
                </form>
              </div>
            </div>
          </center>
        </div>
      </div>
    </div>
  );
};

export default FindID;
