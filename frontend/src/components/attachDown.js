import { useEffect, useState } from "react";
import { AiOutlineDownload } from "react-icons/ai";
import Modal from "react-bootstrap/Modal";
import { Button } from "react-bootstrap";

const ATTDOWN = (attach) => {
  const attchData = attach.attach;

  const [showModals, setShowModals] = useState(
    Array(attchData.length).fill(false)
  );

  const downlink = (fileName, filePath) => {
    const encodedFileName = encodeURIComponent(fileName);
    const encodedFilePath = encodeURIComponent(filePath);

    return `http://15.165.98.14:8080/file/download?uploadFileName=${encodedFileName}&uploadFilePath=${encodedFilePath}`;
  };

  return attchData.map((it, index) => {
    return (
      <div key={it.id}>
        <div className="download">
          <span className="filename">{it.originalFileName}</span>
          <Button
            variant="dark"
            className="down_btn"
            onClick={() => {
              setShowModals((prev) => {
                const updatedModals = [...prev];
                updatedModals[index] = true;
                return updatedModals;
              });
            }}
          >
            미리보기
          </Button>
          <div>
            <a
              href={downlink(it.uploadFileName, it.uploadFilePath)}
              rel="noopener noreferrer"
              onClick={() => {}}
            >
              <AiOutlineDownload style={{marginRight: "7px"}} size="35" />
            </a>
          </div>
          <Modal
            show={showModals[index]}
            onHide={() => {
              setShowModals((prev) => {
                const updatedModals = [...prev];
                updatedModals[index] = false;
                return updatedModals;
              });
            }}
          >
            <Modal.Header closeButton>
              <Modal.Title style={{ fontSize: "17px", fontWeight: "bold" }}>
                이미지 미리보기
              </Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <img
                src={it.uploadFileUrl}
                alt="Preview"
                style={{ width: "100%" }}
              />
            </Modal.Body>
            <Modal.Footer>
              <Button
                className="close_btn"
                variant="secondary"
                onClick={() => {
                  setShowModals((prev) => {
                    const updatedModals = [...prev];
                    updatedModals[index] = false;
                    return updatedModals;
                  });
                }}
              >
                닫기
              </Button>
            </Modal.Footer>
          </Modal>
        </div>
      </div>
    );
  });
};

export default ATTDOWN;