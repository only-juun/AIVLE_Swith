import time
import csv
import cv2
import itertools
import numpy as np
import pandas as pd
import os
import sys
import tempfile
import tqdm
from matplotlib import pyplot as plt
from matplotlib.collections import LineCollection
import tensorflow as tf
import tensorflow_hub as hub
from tensorflow import keras

from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix

image_path = "C:\\MyPaperWork_YH\\Camera_pose\\data\\"
csv_out_path = "/content/drive/MyDrive/Wi-Fi/camera_preprocessing/output.csv"
images_out_folder = "/content/drive/MyDrive/Wi-Fi/camera_preprocessing"
model_path = "/content/drive/MyDrive/Wi-Fi/camera_preprocessing/movenet_thunder.tflite"

import os
import csv
import cv2
import numpy as np
import pandas as pd
import tensorflow as tf
import tempfile
from enum import Enum
from tqdm import tqdm

class BodyPart(Enum):
    """Body part names for each keypoint."""
    NOSE = 0
    LEFT_EYE = 1
    RIGHT_EYE = 2
    LEFT_EAR = 3
    RIGHT_EAR = 4
    LEFT_SHOULDER = 5
    RIGHT_SHOULDER = 6
    LEFT_ELBOW = 7
    RIGHT_ELBOW = 8
    LEFT_WRIST = 9
    RIGHT_WRIST = 10
    LEFT_HIP = 11
    RIGHT_HIP = 12
    LEFT_KNEE = 13
    RIGHT_KNEE = 14
    LEFT_ANKLE = 15
    RIGHT_ANKLE = 16

def load_movenet_model(model_path):
    """Loads the MoveNet model from the given .tflite file.

    Args:
        model_path: Path to the MoveNet .tflite file.

    Returns:
        Loaded MoveNet interpreter.
    """
    interpreter = tf.lite.Interpreter(model_path=model_path)
    interpreter.allocate_tensors()
    return interpreter

def detect_landmarks(image, interpreter):
    """Detects landmarks in the given image using the MoveNet model.

    Args:
        image: Input image in RGB format.
        interpreter: MoveNet interpreter.

    Returns:
        Detected landmarks.
    """
    input_details = interpreter.get_input_details()
    output_details = interpreter.get_output_details()

    input_shape = input_details[0]['shape']
    input_tensor = np.expand_dims(image, axis=0)
    input_tensor = tf.image.resize(input_tensor, (input_shape[1], input_shape[2]))
    input_tensor = tf.cast(input_tensor, dtype=input_details[0]['dtype'])

    interpreter.set_tensor(input_details[0]['index'], input_tensor)
    interpreter.invoke()

    output_tensor = interpreter.get_tensor(output_details[0]['index'])
    landmarks = output_tensor.squeeze()

    return landmarks

def preprocess_single_image(image_path, csv_out_path, images_out_folder, model_path, detection_threshold=0.1):
    """Preprocesses a single image and saves the detected landmarks in a CSV file.

    Args:
        image_path: Path to the input image.
        csv_out_path: Path to write the CSV file containing the detected landmark
            coordinates.
        images_out_folder: Path to write the image overlay with detected
            landmarks.
        model_path: Path to the MoveNet .tflite file.
        detection_threshold: Only keep the image if all landmark confidence scores
            are above this threshold.
    """
    # Load the image
    try:
        image = tf.io.read_file(image_path)
        image = tf.io.decode_jpeg(image)
    except:
        raise ValueError(f"Invalid image: {image_path}")

    image_height, image_width, channel = image.shape

    # Skip images that aren't RGB because MoveNet requires RGB images
    if channel != 3:
        raise ValueError(f"Image isn't in RGB format: {image_path}")


    # Load the MoveNet model
    interpreter = load_movenet_model(model_path)

    # Detect landmarks using MoveNet
    landmarks = detect_landmarks(image, interpreter)

    # Write the landmark coordinates to the CSV file
    with open(csv_out_path, "w") as csv_out_file:
        csv_out_writer = csv.writer(csv_out_file, delimiter=",", quoting=csv.QUOTE_MINIMAL)
        coordinates = landmarks.flatten().astype(str).tolist()
        csv_out_writer.writerow([os.path.basename(image_path)] + coordinates)

def classifier(cmodel_path, input_data_path):

    # TFLite 인터프리터 초기화
    interpreter = tf.lite.Interpreter(model_path=cmodel_path)
    interpreter.allocate_tensors()

    # 입력 텐서 정보 가져오기
    input_details = interpreter.get_input_details()
    output_details = interpreter.get_output_details()

    # 입력 데이터 준비 (예시로 랜덤한 입력 데이터 생성)
    input_shape = input_details[0]['shape']


    # input_data = np.random.random_sample(input_shape).astype(np.float32)
    input_data = pd.read_csv(input_data_path, header=None)
    input_data = input_data.drop(input_data.columns[0], axis=1)

    arr = np.array(input_data)
    arr = tf.cast(arr, tf.float32)

    # # 입력 데이터 설정
    interpreter.set_tensor(input_details[0]['index'], arr)

    # # 추론 실행
    interpreter.invoke()

    # # 결과 받아오기
    output_data = interpreter.get_tensor(output_details[0]['index'])

    # # 결과 출력
    return np.argmax(output_data, -1)
        # print(output_data)

def capture_webcam_screen(output_folder, model_path,cmodel_path):
    cap = cv2.VideoCapture(0)  # Open the webcam (0 indicates the default webcam)
    i = 1

    while True:
        ret, frame = cap.read()  # Read a frame from the webcam
        if ret:
            image_name = f'{output_folder}/frame_{i}.jpg'  # Generate the image name
            cv2.imwrite(image_name, frame)  # Save the frame as an image

            ##웹캠 데이터 가지고 무브넷 돌려서 전처리 나오게 하는 코드
            csv_out_path = f'{output_folder}/csv_data/test{i}.csv'
            preprocess_single_image(image_name, csv_out_path, images_out_folder, model_path)
            predi = classifier(cmodel_path, csv_out_path)
            print(predi) ##########여기가 예측된 결과 출력하는 부분
            #AWS 에 저 코드 됩니다.

            ################################################
            print(f'Image captured: {image_name}')
            i += 1
        time.sleep(1)  # Wait for 1 second

        # Press 'q' to stop capturing
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    cap.release()  # Release the webcam
    cv2.destroyAllWindows()  # Close all OpenCV windows

# Output folder to save the captured images
output_folder = 'C:\\MyPaperWork_YH\\Camera_pose\\data'
model_path = "C:\\MyPaperWork_YH\\Camera_pose\\model\\movenet_thunder.tflite.tflite"
cmodel_path = "C:\\MyPaperWork_YH\\Camera_pose\\model\\pose_classifier.tflite"
# Call the function to capture the webcam screen and save images every second
capture_webcam_screen(output_folder,model_path,cmodel_path)