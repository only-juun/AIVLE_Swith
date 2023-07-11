import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const Postcontent = ({ currentPage, bySearch, searchTerm, searchBy }) => {
  const [pageData, setPageData] = useState([]);

  console.log(bySearch);
  console.log(searchTerm);
  console.log(searchBy);

  useEffect(() => {
    if (!bySearch) {
      axios({
        method: "get",
        url: `http://15.165.98.14:8080/posts/postList?page=${currentPage - 1}`,
      })
        .then((res) => {
          setPageData(res.data.content);
          console.log(res);
        })
        .catch((err) => {
          console.log(err.data);
        });
    } else {
      axios({
        method: "get",
        url: `http://15.165.98.14:8080/posts/search?type=${searchBy}&content=${searchTerm}&page=${
          currentPage - 1
        }`,
      })
        .then((res) => {
          setPageData(res.data.data);
        })
        .catch((err) => {
          console.log(err.data);
        });
    }
  }, [currentPage, bySearch, searchTerm]);

  const getStringDate = (date) => {
    return date.slice(0, 10);
  };

  function countCommentsByPostId(id) {
    const comments = JSON.parse(localStorage.getItem("comments"));
    if (!comments) {
      return 0;
    }

    const matchingComments = comments.filter(
      (comment) => parseInt(comment.postid) === parseInt(id)
    );
    return matchingComments.length;
  }

  // 게시글 번호처리를 위한 array
  let a = [];
  for (let i = 1; i <= 10; i++) {
    a.push((currentPage - 1) * 10 + i);
  }

  return pageData.map((post, idx) => (
    <Link to={`/post/${post.postId}`} className="linktopost" key={post.numbers}>
      <div className="noticedescription userPost">
        <span>{a[idx]}</span>
        <span>
          {post.title}{" "}
        </span>
        <span>{post.writer}</span>
        <span>{getStringDate(post.createTime)}</span>
        <span>{post.likeCount}</span>
        <span>{post.searchCount}</span>
      </div>
    </Link>
  ));
};

export default React.memo(Postcontent);
