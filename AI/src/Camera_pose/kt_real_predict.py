
import tensorflow as tf
import boto3
from tensorflow import keras
import numpy as np
import matlab.engine
import time
import matplotlib.pyplot as plt
import h5py
import requests
# import detect_human

headers = {'Content-Type': 'application/json; charset : utf-8'}
# S3 클라이언트 생성
s3_client = session.client('s3')

# 모델 파일 다운로드
s3_client.download_file('a031293-bucket', 'model/camera_wifi.h5', 'StateOfArt-load-model.h5')



# Call the function to capture the webcam screen and save images every second

# Define the path to the .h5 model file
# model_path = 'cnn_wifi.h5'

# Load the model from the .h5 file using h5py
# f = h5py.File(model_path, 'r')
# model_config = f.attrs.get('cnn_wifi.h5')
# model = tf.keras.models.model_from_json(model_config)
# model.load_weights(model_path)

eng = matlab.engine.start_matlab()
#/home/kimyonghwan/2019_Final/src/LR0.0001_BATCHSIZE200_NHIDDEN200/model.ckpt.meta
# saver = tf.train.import_meta_graph('cnn_wifi.h5') #modified
my_model = keras.models.load_model('StateOfArt-load-model.h5', compile=False)
print(my_model.summary())
# graph = tf.get_default_graph()
# x = graph.get_tensor_by_name("Placeholder:0")
# pred = graph.get_tensor_by_name("add:0")
# plt.ion()

b = np.arange(0, 15000)
act = ["onfloor", "empty", "for_lay", "back_lay", "indanger"]
# tmp = 0
i = 1

#매틀랩으로 실시간 Prediction
while 1: #modified
    time.sleep(3) #making the data, need time
    # tmp+=1
    print(str(i)+'_first')
    k = 1
    t = 0
    # real time data input but now one data


    csi_trace = eng.read_bf_file(f'/home/yhk/linux-80211n-csitool-supplementary/netlink/Realtime_test/aTtest{i}') #modified
    kkk = len(csi_trace)
    if len(csi_trace) < 500: #modified
        continue #modified
    #여기서부터 시작 그 위는 있을리 없어도 컨티뉴 한다. 혹시 모르니.
    ARR_FINAL = np.empty([0, 90], float)
    xx = np.empty([1, 500, 90], float)
    xx1 = np.empty([0], float)
    yy1 = np.empty([0], float)
    zz1 = np.empty([0], float)
    try:
        while (k <= 500): #실시간 데이터는 500줄만 읽어온다. 그게 아니고 그냥 시간임. #modified
            csi_entry = csi_trace[t] #여기 t를 이해해야겠네. matlab을 이해해야 한다.
            try:
                csi = eng.get_scaled_csi(csi_entry)
                A = eng.abs(csi)
                ARR_OUT = np.empty([0], float)

                ARR_OUT = np.concatenate((ARR_OUT, A[0][0]), axis=0)
                ARR_OUT = np.concatenate((ARR_OUT, A[0][1]), axis=0)
                ARR_OUT = np.concatenate((ARR_OUT, A[0][2]), axis=0)

                xx1 = np.concatenate((xx1, A[0][0]), axis = 0)
                yy1 = np.concatenate((yy1, A[0][1]), axis = 0)
                zz1 = np.concatenate((zz1, A[0][2]), axis = 0)
                ARR_FINAL = np.vstack((ARR_FINAL, ARR_OUT))
                k = k + 1
                t = t + 1

            except matlab.engine.MatlabExecutionError:
                print('MatlabExecutionError occured!!!')
                # break #modified
            xx[0] = ARR_FINAL
            break

    except ValueError:
        print('ValueError occured!!!')
        # continue #modified
    #
    #
    xx = tf.expand_dims(xx, -1)
    w_predi = my_model.predict(xx)
    w_predi = int(tf.argmax(w_predi, -1))


    d_send = {}

    c_filename = f"/home/yhk/Camera_pose/c_predictionResult/aTtest{i}.txt"
    c_predi = -1
    with open(c_filename, 'r') as file:
        c_predi = file.readline()
    print(c_predi,":c_pred        ",w_predi,":w_pred")


    ##############
    #aws label<아이> : 0 - onfloor(낙상), 1 - backlay, 2 - indanger
    #aws label<성인> : 3 - onfloor(낙상), 4 - indanger(침입)

    #camera label : 0 - backlay, 1 - forlay, 2 - move, 3 - onfloor(낙상)
    #wifi label : 2 - backlay, 4 - forlay , 6 - indanger , 8 - onfloor , 10 - empty

    #mobilenet label : 1 - human exist , 0 - human exist,

    # # AWS load variables
    child = True
    adult = False
    d_send["serialNumber"] = "123456"

    #mobilenet openpose to detect human
    yh_filename = f"/home/yhk/Camera_pose/mobiletxt/{i}.txt"
    yh_predi = ""
    with open(yh_filename, 'r') as file:
        yh_predi = file.readline()

    if yh_predi == "1": #camera True
        if w_predi == '8' and c_predi == '3':  # onfloor
            d_send["wifi"] = "True"
            d_send["camera"] = "True"
            if child:
                d_send["label"] = 0
            elif adult:
                d_send["label"] = 3
            response = requests.post(url_sse, json=d_send, headers=headers)
            response = requests.post(url_db, json=d_send, headers=headers)

        elif w_predi == '8' and (c_predi == '2' or c_predi == '1'): #onfloor vs  ( forlay ,backlay ) in camera is not detected well. so WiFi's detect is stronger than camera.
            d_send['wifi'] = "True"
            d_send["camera"] = "False"
            if child:
                d_send["label"] = 0
            elif adult:
                d_send["label"] = 3
            response = requests.post(url_sse, json=d_send, headers=headers)
            response = requests.post(url_db, json=d_send, headers=headers)

        elif (w_predi == '2' or w_predi =='4') and c_predi == '0' and child: #(backlay, forlay) in WiFi is not detected well, so camera's detect is stronger than WiFi.
            if w_predi == '2': #backlay
                d_send['wifi'] = "True"
            elif w_predi == '4':#forlay
                d_send['wifi'] = "False"

            d_send["camera"] = "True"
            d_send["label"] = 1 # child backlay

            response = requests.post(url_sse, json=d_send, headers=headers)
            response = requests.post(url_db, json=d_send, headers=headers)

        #indanger
        elif c_predi == '2' and w_predi == '6':
            d_send['camera'] = True
            d_send['wifi'] = True
            if child:
                d_send['label'] = 2
            elif adult:
                d_send['label'] = 4
            response = requests.post(url_sse, json=d_send, headers=headers)
            response = requests.post(url_db, json=d_send, headers=headers)

    elif yh_predi == '0': #no camera's detect for human.
        d_send['camera'] = "False"
        d_send['wifi'] = "True"
        if w_predi == '6': #indanger
            if child:
                d_send['label'] = 2
            elif adult:
                d_send['label'] = 4
        response = requests.post(url_sse, json=d_send, headers=headers)
        response = requests.post(url_db, json=d_send, headers=headers)

    ####################################################

    i += 1
    print(i)
    ####################################################

    #
    # only camera
    # d_send['camera'] = "True"
    # d_send['wifi'] = "False"
    # if c_predi == '0':
    #     d_send["label"] = 1
    # elif c_predi == '3':
    #     d_send["label"] = 0
    # elif c_predi == '2':
    #     d_send["label"] = 2



