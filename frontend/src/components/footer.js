import './footer.scss';
import logo from '../img/logo-1.png';
import git_log from '../img/github-mark.png';

const Footer = () => {
  return (
    <div className="footer-wrapper">
      <div className="footer-container">
        <div className="footer-left">
          <img src={logo} alt="Logo" className="footer-logo" />
          <p>&copy; 2023 S.with. All rights reserved.</p>
        </div>
        <div className="footer-right">
          <div className="footer-right-one">
            <div className="right-one-top">
              <b>사업자등록번호 : 102-81-42945</b>
              <br />
              <b>경기도 성남시 분당구 불정로 90 (정자동)</b>
            </div>
            <div className="right-one-bottom">
              <div>
                <b>TEL</b> : 010-1234-5678
              </div>
              <div>
                <b>FAX</b> : 010-1234-5678
              </div>
            </div>
          </div>
          <div className="footer-right-two">
            <div className="team-team">
              <b>TEAM 21(Contact Us)</b>
            </div>
            <div className="team">
              AI :{' '}
              <a
                href="https://github.com/brightface"
                target="_blank"
                rel="noopener noreferrer"
              >
                김용환
              </a>{' '}
              <a
                href="https://github.com/kj021"
                target="_blank"
                rel="noopener noreferrer"
              >
                장규진
              </a>{' '}
              <a
                href="https://github.com/CYeryeong"
                target="_blank"
                rel="noopener noreferrer"
              >
                조예령
              </a>
            </div>
            <div className="team">
              FE :{' '}
              <a
                href="https://github.com/Yjason-K"
                target="_blank"
                rel="noopener noreferrer"
              >
                김영재
              </a>{' '}
              <a
                href="https://github.com/18-12847"
                target="_blank"
                rel="noopener noreferrer"
              >
                오승재
              </a>
            </div>
            <div className="team">
              BE :{' '}
              <a
                href="https://github.com/only-juun"
                target="_blank"
                rel="noopener noreferrer"
              >
                남환준
              </a>{' '}
              <a
                href="https://github.com/leehanjun506"
                target="_blank"
                rel="noopener noreferrer"
              >
                이한준
              </a>
            </div>
          </div>
          <div className="footer-right-three">
            <a
              href="https://github.com/AIVLE-School-Third-Big-Project/AIVLE_BigProject_team21"
              target="_blank"
              rel="noopener noreferrer"
            >
              <img src={git_log} alt="Github" className="github-logo" />
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Footer;
