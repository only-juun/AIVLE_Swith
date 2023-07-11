import { useEffect, useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import { Button } from "react-bootstrap";
import Modal from "react-bootstrap/Modal";
import axios from "axios";
import "../style/post.css"
import Myheader from "../components/header";

const NMEWPOST = () => {
  useEffect(() => {
    const session = localStorage.getItem("token");
    if (!session) {
      window.alert("잘못된 접근입니다!");
      navigate("/postlist", { replace: true });
    } else {
      setSessionId(true);
    }
  }, []);

  const [sessionId, setSessionId] = useState(false);
  const [postTitle, setPostTitle] = useState("");
  const [content, setContent] = useState("");
  const [editorInstance, setEditorInstance] = useState(null);

  const titleRef = useRef();
  const contentRef = useRef();

  const onChangeContent = (e) => {
    setPostTitle(e.target.value);
  };

  const navigate = useNavigate();

  const handleSubmit = () => {
    const trimmedTitle = postTitle.trim();
    if (trimmedTitle.length < 4) {
      alert("제목을 입력해주세요(공백 제외 4글자 이상)");
      titleRef.current.focus();
      return;
    }

    if (content.trim().length < 10) {
      alert("내용을 입력해주세요(3글자 이상)");
      if (contentRef.current) {
        contentRef.current.scrollIntoView({ behavior: 'smooth' });
        if (editorInstance) {
          editorInstance.editing.view.focus();
        }
      }
      return;
    }

    if (window.confirm("게시글을 저장하시겠습니까?")) {
      const formData = new FormData();
      formData.append(
        "data",
        new Blob([JSON.stringify({ title: postTitle, content: content })], {
          type: "application/json",
        })
      );

      files.slice(1).forEach((file, index) => {
        formData.append(`files`, file);
      });

      axios({
        method: "post",
        url: "http://15.165.98.14:8080/posts/new",
        data: formData,
        headers: {
          Authorization: `Bearer ${JSON.parse(localStorage.getItem("token")).accessToken
            }`,
        },
      })
        .then((res) => {
          navigate("/postlist", { replace: true });
        })
        .catch((err) => {
          if (err.response && err.response.status === 500) {
            if (err.response.data && err.response.data.message) {
              alert(err.response.data.message);
            } else {
              alert("1MB 이하의 사진을 업로드 해주세요.");
            }
          } else {
          }
        });
    }
  };

  const handlCancel = () => {
    if (
      window.confirm(
        "게시글 작성을 취소하시겠습니까? 작성된 내용은 내용은 저장되지 않습니다."
      )
    ) {
      navigate("/postlist", { replace: true });
    }
  };

  const [att_num, setAttNum] = useState(0);

  const att_plus = () => {
    setAttNum(att_num + 1);
  };
  const att_minus = () => {
    setAttNum(att_num - 1);
  };

  if (att_num > 3) {
    window.alert("이미지는 최대 3개까지 업로드 가능합니다.");
    setAttNum(3);
  }
  if (att_num < 0) {
    setAttNum(0);
  }

  const renderFileInputs = () => {
    const fileInputs = [];
    for (let i = 1; i <= att_num; i++) {
      fileInputs.push(
        <div key={i} className="att_wrapper">
          <input
            type="file"
            accept="image/*"
            onChange={(e) => handleFileChange(e, i)}
            className="attach_btn"
          />
          <Button
            variant="secondary"
            className="preview_button"
            onClick={() => handlePreview(i)}
          >
            미리보기
          </Button>
        </div>
      );
    }
    return fileInputs;
  };

  // 이미지 미리보기를 위한 모달 처리
  const [selectedImage, setSelectedImage] = useState(null);
  const [selectedImages, setSelectedImages] = useState([]);

  const [showModal, setShowModal] = useState(false);

  const handlePreview = (index) => {
    setShowModal(true);
    setSelectedImage(selectedImages[index]);
  };

  // 파일 업로드 처리를 위한 상태
  const [files, setFiles] = useState([]);

  const handleFileChange = (e, index) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        setSelectedImages((prevSelectedImages) => {
          const newSelectedImages = [...prevSelectedImages];
          newSelectedImages[index] = reader.result;
          return newSelectedImages;
        });
      };
      reader.readAsDataURL(file);
      setFiles((prevFiles) => {
        const newFiles = [...prevFiles];
        newFiles[index] = file;
        return newFiles;
      });
    }
  };

  return (
    <div className="newpost">
      <Myheader />
      <div className="newPostArea">
        <div className="postController">
          <div className="title_wrapper">
            <span className="publicSection">Title {postTitle.length} / 30</span>
            <input
              className="title-input"
              type="text"
              maxLength={30}
              placeholder="제목을 입력해주세요(4글자 이상)"
              value={postTitle}
              onChange={onChangeContent}
              name="title"
              style={{ width: 200 }}
              ref={titleRef}
            />
          </div>
        </div>
        <div ref={contentRef} className="newpostcontent">
          <CKEditor
            editor={ClassicEditor}
            config={{
              placeholder: "내용을 입력하세요(3글자 이상)",
            }}
            onReady={(editor) => {
              setEditorInstance(editor);
            }}
            onChange={(event, editor) => {
              const data = editor.getData();
              setContent(data);
            }}
          />
        </div>
        <div className="attach_area">
          <div className="attach_num">
            <h5
              style={{
                marginRight: 20,
                marginLeft: 10,
                fontWeight: "bold",
                fontSize: 14,
                color: "rgb(41, 41, 41)",
              }}
            >
              이미지 업로드하기
            </h5>
            <Button variant="outline-dark" className="a_btn" onClick={att_plus}>
              +
            </Button>
            <Button
              variant="outline-dark"
              className="a_btn"
              onClick={att_minus}
            >
              -
            </Button>
          </div>
          <div className="file_list">{renderFileInputs()}</div>
        </div>
        <Modal
          show={showModal}
          onHide={() => {
            setShowModal(false);
            setSelectedImage(null);
          }}
        >
          <Modal.Header closeButton>
            <Modal.Title style={{ fontSize: "17px", fontWeight: "bold" }}>
              이미지 미리보기
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {selectedImage && (
              <img
                src={selectedImage}
                alt="Preview"
                style={{ width: "100%" }}
              />
            )}
          </Modal.Body>
          <Modal.Footer>
            <Button
              className="close_btn"
              variant="secondary"
              onClick={() => setShowModal(false)}
            >
              닫기
            </Button>
          </Modal.Footer>
        </Modal>
        <div style={{ marginTop: "35px", marginBottom: "10px" }}>
          <div className="newpost_btn_wrapper" style={{ display: 'flex', justifyContent: 'space-between' }}>
            <Button variant="dark" className="postviewbutton_cancel"
              onClick={handlCancel} style={{ marginLeft: '2px', width: "10%", height: "30px", fontSize: "15px" }}>
              Cancel
            </Button>

            <Button
              variant="dark"
              className="postviewbutton_save"
              onClick={handleSubmit}
              style={{ width: "10%", marginRight: "1px", fontSize: "15px" }}
            >
              Save
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default NMEWPOST;
