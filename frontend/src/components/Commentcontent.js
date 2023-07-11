import { useState, useEffect } from "react";
import Button from "react-bootstrap/Button";
import axios from "axios";

const Commentcontent = ({ post_id, commentdata }) => {
  const getStringDate = (date) => {
    return new Date(date).toISOString().replace("T", " ").split(".")[0];
  };
  const [comment, setComment] = useState([]);

  const today = new Date(new Date().getTime() + 32400000)
    .toISOString()
    .split("T")[0];

  const [loginId, setLoginId] = useState();
  const [userNickname, setUserNickname] = useState("");

  useEffect(() => {
    const sessionId = localStorage.getItem("toekn");
    if (sessionId) {
      setLoginId(JSON.parse(localStorage.getItem("token")));
    }

    if (localStorage.getItem("token") !== null) {
      axios({
        method: "get",
        url: `http://15.165.98.14:8080/users/user`,
        headers: {
          Authorization: `Bearer ${
            JSON.parse(localStorage.getItem("token")).accessToken
          }`,
        },
      }).then((res) => {
        setUserNickname(res.data.nickname);
      });
    }
  }, []);

  useEffect(() => {
    setComment(commentdata);
  }, [commentdata]);

  const deleteHandler = (a) => {
    if (window.confirm("댓글을 삭제하시겠습니까?")) {
      axios({
        method: "delete",
        url: `http://15.165.98.14:8080/comments/${a}`,
        headers: {
          Authorization: `Bearer ${
            JSON.parse(localStorage.getItem("token")).accessToken
          }`,
        },
      })
        .then((res) => {
          setTimeout(() => {
            axios
              .get(`http://15.165.98.14:8080/posts/post/${post_id}`)
              .then((res) => {
                setComment(res.data.commentInfoDtoList);
              })
              .catch((err) => {
              });
          }, 300);
        })
        .catch((err) => {
          alert("댓글 삭제 불가!");
        });
    }
  };

  return (
    <>
      {comment.map((it) => (
        <div className="commentlist">
          <div className="first_row">
            <span className="comment_id">{it.writerDto.nickname} </span>
            <span className="comment_date">
              {getStringDate(it.createdDate).split("T")[0] === today
                ? getStringDate(it.createdDate)
                : getStringDate(it.createdDate).split(" ")[0]}
            </span>
          </div>
          <div className="second_row">
            <span
              style={{ flexGrow: 2, alignItems: "center", fontSize: "12px" }}
            >
              {it.content}
            </span>
            {userNickname === it.writerDto.nickname && (
              <Button
                variant="link"
                onClick={() => deleteHandler(it.commentId)}
                className="commment_delete_button"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="icon icon-tabler icon-tabler-trash"
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  stroke-width="1.5"
                  stroke="#2c3e50"
                  fill="none"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                >
                  <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                  <path d="M4 7l16 0" />
                  <path d="M10 11l0 6" />
                  <path d="M14 11l0 6" />
                  <path d="M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12" />
                  <path d="M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3" />
                </svg>
              </Button>
            )}
          </div>
        </div>
      ))}
    </>
  );
};

export default Commentcontent;
