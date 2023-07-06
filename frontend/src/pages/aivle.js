import { useEffect, useState } from "react";
import Myheader from "../components/header";
import "../style/aivle.css";
import younghwan from "../img/yonghwan.png";
import gyujin from "../img/gyujin.png";
import yeryeong from "../img/yeryeong.png";
import youngjae from "../img/youngjae.png";
import seungjae from "../img/seungjae.png";
import hwanjun from "../img/hwanjun.png";
import hanjoon from "../img/hanjoon.png";

const teamMembers = [
  {
    name: "김용환",
    description: "AI 모델링",
    image: younghwan,
    git: "https://github.com/brightface",
  },
  {
    name: "장규진",
    description: "AI 모델링",
    image: gyujin,
    git: "https://github.com/kj021",
  },
  {
    name: "조예령",
    description: "AI 모델링",
    image: yeryeong,
    git: "https://github.com/CYeryeong",
  },
  {
    name: "김영재",
    description: "Frontend",
    image: youngjae,
    git: "https://github.com/Yjason-K",
  },
  {
    name: "오승재",
    description: "Frontend",
    image: seungjae,
    git: "https://github.com/18-12847",
  },
  {
    name: "남환준",
    description: "Backend",
    image: hwanjun,
    git: "https://github.com/only-juun",
  },
  {
    name: "이한준",
    description: "Backend",
    image: hanjoon,
    git: "https://github.com/leehanjun506",
  },
];

const AIVLE = () => {
  const session = localStorage.getItem("toekn");
  const [sessionId, setSessionId] = useState(false);

  useEffect(() => {
    if (session && session !== "null") {
      setSessionId(true);
    }
  }, []);

  return (
    <div className="aivle">
      <Myheader isLogin={sessionId} />
      <div style={{ marginTop: "100px" }}>
        <div>
          <h1>
            <center>팀원 소개</center>
          </h1>
        </div>
        <div className="team_wrapper">
          <div className="team-row">
            {teamMembers.slice(0, 3).map((member, index) => (
              <div key={index} className="team-card">
                <div className="card">
                  <div className="card-inner">
                    <div className="card-front">
                      <img
                        src={member.image}
                        className="card-img-top"
                        alt="team member"
                      />
                      <div className="card-body">
                        <h5 className="card-title">{member.name}</h5>
                        <p className="card-text">{member.description}</p>
                      </div>
                    </div>
                    <div className="card-back">
                      <div className="card-back-content">
                        <a
                          href={member.git}
                          target="_blank"
                          rel="noopener noreferrer"
                          className="github-link"
                        >
                          GitHub
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
          <div className="team-row" style={{ marginTop: "50px" }}>
            {teamMembers.slice(3).map((member, index) => (
              <div key={index} className="team-card">
                <div className="card">
                  <div className="card-inner">
                    <div className="card-front">
                      <img
                        src={member.image}
                        className="card-img-top"
                        alt="team member"
                      />
                      <div className="card-body">
                        <h5 className="card-title">{member.name}</h5>
                        <p className="card-text">{member.description}</p>
                      </div>
                    </div>
                    <div className="card-back">
                      <div className="card-back-content">
                        <a
                          href={member.git}
                          target="_blank"
                          rel="noopener noreferrer"
                          className="github-link"
                        >
                          GitHub
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default AIVLE;