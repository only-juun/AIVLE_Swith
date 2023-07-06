import React, {
  createContext,
  useState,
  useEffect,
  useReducer,
  useRef,
} from "react";
import {
  BrowserRouter,
  Route,
  Routes,
  Navigate,
  useLocation,
} from "react-router-dom";

import HOME from "./pages/home";
import NEWPOST from "./pages/newPost";
import POSTEDIT from "./pages/postedit";
import POST from "./pages/post";
import POSTLIST from "./pages/postList";
import FAQ from "./pages/faq";
import AIVLE from "./pages/aivle";
import MYPAGE from "./pages/mypage";
import LOGIN from "./pages/login";
import SIGNUP from "./pages/singup";
import SERVICE from "./pages/service";
import FindID from "./pages/findid";
import NotFound from "./pages/notfound";

import "./App.css";

import "bootstrap/dist/css/bootstrap.min.css";
import Footer from "./components/footer";

const getStringDate = (date) => {
  return date.toISOString().slice(0, 10);
};

function postReducer(state, action) {
  let newState = [];
  switch (action.type) {
    case "INIT": {
      return action.data;
    }
    case "CREATE": {
      const newItem = {
        ...action.data,
      };
      newState = [newItem, ...state];
      break;
    }
    case "REMOVE": {
      newState = state.filter((it) => parseInt(it.id) !== parseInt(action.id));
      break;
    }
    case "EDIT": {
      newState = state.map((it) =>
        parseInt(it.id) == parseInt(action.data.id) ? { ...action.data } : it
      );
      break;
    }
    case "VIEWCOUNT": {
      newState = state.map((it) =>
        parseInt(it.id) == parseInt(action.data.id) ? { ...action.data } : it
      );
      break;
    }
    default:
      return state;
  }
  localStorage.setItem("posts", JSON.stringify(newState));
  return newState;
}

function commentReducer(state, action) {
  let newState = [];
  switch (action.type) {
    case "INIT": {
      return action.data;
    }
    case "CREATE": {
      const newItem = {
        ...action.data,
      };
      newState = [newItem, ...state];
      break;
    }
    case "REMOVE": {
      newState = state.filter(
        (it) =>
          it.create_date !== action.data.date ||
          it.comment_id !== action.data.id
      );
      break;
    }
    case "POSTREMOVE": {
      newState = state.filter(
        (it) => parseInt(it.postid) !== parseInt(action.postid)
      );
      break;
    }
    default:
      return state;
  }
  localStorage.setItem("comments", JSON.stringify(newState));
  return newState;
}

export const commentContext = createContext();
export const postContext = createContext();
export const dataContext = createContext();

function App() {
  // 게시글 Id numnber 지정
  // 서버에서 받아오는거 보고 id지정 어떻게 받아올지 결정
  const [dataId, setDataId] = useState(() => {
    return localStorage.getItem("posts")
      ? JSON.parse(localStorage.getItem("posts"))[0]?.id + 1 || 0
      : 0;
  });

  const [isLogin, setIsLogin] = useState(false);
  const [data, dispatch] = useReducer(postReducer, []);

  // 로컬에 저장된 데이터 불러오기
  useEffect(() => {
    const localData = localStorage.getItem("posts");
    if (localData) {
      const postsList = JSON.parse(localData).sort(
        (a, b) => parseInt(b.postDate) - parseInt(a.postDate)
      );
      // 시간순 정렬위해서
      dispatch({ type: "INIT", data: postsList });
    }
  }, []);

  // CREATE
  const onCreate = (title, content) => {
    dispatch({
      type: "CREATE",
      data: {
        id: dataId,
        title,
        content,
        writer: JSON.parse(localStorage.getItem("userId")),
        postDate: new Date().getTime() + 32400000,
        likes: 0,
        views: 0,
      },
    });
    setDataId(dataId + 1);
  };

  // REMOVE
  const onRemove = (targetId) => {
    dispatch({ type: "REMOVE", id: targetId });
  };
  
  // EDIT
  const onEdit = (
    data_id,
    posttitle,
    postcontent,
    postwriter,
    aapostdate,
    postlikes,
    postcount
  ) => {
    dispatch({
      type: "EDIT",
      data: {
        id: data_id,
        title: posttitle,
        content: postcontent,
        writer: postwriter,
        postDate: aapostdate,
        likes: postlikes,
        views: postcount,
      },
    });
  };

  const viewCountUpdate = (
    dataid,
    posttitle,
    postcontent,
    postwriter,
    aapostdate,
    postlikes,
    postcount
  ) => {
    dispatch({
      type: "VIEWCOUNT",
      data: {
        id: dataid,
        title: posttitle,
        content: postcontent,
        writer: postwriter,
        postDate: aapostdate,
        likes: postlikes,
        views: postcount,
      },
    });
  };

  const [commentdata, comment_dispatch] = useReducer(commentReducer, []);

  // 댓글 데이터 불러오기
  useEffect(() => {
    const localCommentData = localStorage.getItem("comments");
    if (localCommentData) {
      const comentList = JSON.parse(localCommentData).sort(
        (a, b) => parseInt(b.create_date) - parseInt(a.create_date)
      );
      // 시간순 정렬위해서
      comment_dispatch({ type: "INIT", data: comentList });
    }
  }, []);

  const commentonCreate = (id, comment) => {
    comment_dispatch({
      type: "CREATE",
      data: {
        postid: id,
        comment_id: JSON.parse(localStorage.getItem("userId")).id,
        comment: comment,
        create_date: new Date().getTime() + 32400000,
      },
    });
  };

  const commentonRemove = (targetId, targetDate) => {
    comment_dispatch({
      type: "REMOVE",
      data: {
        id: targetId,
        date: targetDate,
      },
    });
  };

  const postonRemove = (targetId) => {
    comment_dispatch({
      type: "POSTREMOVE",
      postid: targetId,
    });
  };

  return (
    <postContext.Provider
      value={{ onCreate, onRemove, viewCountUpdate, onEdit }}
    >
      <commentContext.Provider
        value={{
          commentdata,
          postonRemove,
          commentonRemove,
          commentonCreate,
        }}
      >
        <dataContext.Provider value={data}>
          <BrowserRouter>
            <div className="App">
              <Routes>
                <Route path="/" element={<Navigate to="/main" replace />} />
                <Route path="/main" element={<HOME />} />
                <Route path="/newpost" element={<NEWPOST />} />
                <Route path="/edit/:id" element={<POSTEDIT />} />
                <Route path="/post/:id" element={<POST />} />
                <Route path="/postlist" element={<POSTLIST />} />
                <Route path="/faq" element={<FAQ />} />
                <Route path="/team21" element={<AIVLE />} />
                <Route path="/mypage" element={<MYPAGE />} />
                <Route path="/login" element={<LOGIN />} />
                <Route path="/signup" element={<SIGNUP />} />
                <Route path="/findid" element={<FindID />} />
                <Route path="/service" element={<SERVICE />} />
                <Route path="/*" element={<NotFound />} />
              </Routes>
              <Footer />
            </div>
          </BrowserRouter>
        </dataContext.Provider>
      </commentContext.Provider>
    </postContext.Provider>
  );
}

export default App;
