import { useNavigate } from "react-router-dom";
import Myheader from "../components/header";

const NotFound = () => {
  const navigate = useNavigate();
  return (
    <div>
      <Myheader />
      <section class="page_404">
        <div class="container">
          <div class="row">
            <div class="col-sm-12 ">
              <div class="col-sm-10 col-sm-offset-1  text-center">
                <div class="four_zero_four_bg">
                  <h1 class="text-center ">404</h1>
                </div>

                <div class="contant_box_404">
                  <h3 class="h2">페이지를 찾을 수 없습니다.</h3>

                  <p>
                    존재하지 않는 주소를 입력하셨거나, 요청하신페이지의 주소가
                    변경, 삭제되어 찾을 수 없습니다.
                  </p>

                  <a
                    href="/main"
                    class="link_404"
                    onClick={() => {
                      navigate("/main", { replace: true });
                    }}
                  >
                    Go to Home
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default NotFound;
