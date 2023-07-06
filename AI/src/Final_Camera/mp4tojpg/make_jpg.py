import cv2
import os

filepath = ['empty.mp4', 'empty2.mp4']

for i in range(len(filepath)):
    video = cv2.VideoCapture(filepath[i])
    filename = filepath[i]

    if not video.isOpened():
        print("Could not Open :", filepath[i])
        exit(0)
        
    length = int(video.get(cv2.CAP_PROP_FRAME_COUNT))
    width = int(video.get(cv2.CAP_PROP_FRAME_WIDTH))
    height = int(video.get(cv2.CAP_PROP_FRAME_HEIGHT))
    fps = int(video.get(cv2.CAP_PROP_FPS))

    print("length :", length)
    print("width :", width)
    print("height :", height)
    print("fps :", fps)

    #프레임을 저장할 디렉토리를 생성
    try:
        if not os.path.exists(filename[:-4]):
            os.makedirs(filename[:-4])
    except OSError:
        print ('Error: Creating directory. ' +  filename)
        

    count = 0

    # while(video.isOpened()):
        
    #     ret, image = video.read()
    #     if(int(video.get(1)) % fps == 0): #앞서 불러온 fps 값을 사용하여 1초마다 추출
    #         cv2.imwrite(filepath[:-4] + "/frame%d.jpg" % count, image)
    #         print(filepath[:-4] + "/frame%d.jpg")
    #         print('Saved frame number :', str(int(video.get(1))))
    #         count += 1
            
    # video.release()

    while(video.isOpened()):
        
        ret, image = video.read()
        if(int(video.get(1)) % fps == 0): 
            #앞서 불러온 fps 값을 사용하여 1초마다 추출
            # 앞서 불러온 fps값을 사용하면 계산 안됨. 정수가 아니라 그런 오류가 생겻음.
            cv2.imwrite(filename[:-4] + "/" + str(i) + "%04d.jpg" % count, image)
            # 01, 02, 03을 앞에 붙여 사람 구별
            print('Saved frame number :', str(int(video.get(1))))
            print('Rest frame number :', str(int(length) - int(video.get(1))))
            count += 1
        
        elif int(length) - int(video.get(1)) < fps :
            break
    video.release()
            
    

