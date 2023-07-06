# -*- coding: UTF-8 -*-
import cv2 as cv
import argparse
import numpy as np
import time
from utils import choose_run_mode, load_pretrain_model, set_video_writer
from Pose.pose_visualizer import TfPoseVisualizer
from Action.recognizer import load_action_premodel, framewise_recognize

def detect_h():
    parser = argparse.ArgumentParser(description='Action Recognition by OpenPose')
    parser.add_argument('--video', help='Path to video file.')
    args = parser.parse_args()
   
    estimator = load_pretrain_model('mobilenet_thin')
    # action_classifier = load_action_premodel('Action/framewise_recognition.h5')


    realtime_fps = '0.0000'
    start_time = time.time()
    fps_interval = 1
    fps_count = 0
    run_timer = 0
    frame_count = 0

    cap = choose_run_mode(args)
    video_writer = set_video_writer(cap, write_fps=int(7.0))




    humans = -1
    while cv.waitKey(1) < 0:
        has_frame, show = cap.read()
        if has_frame:
            fps_count += 1
            frame_count += 1

            # pose estimation
            humans = estimator.inference(show)
            if len(humans) > 0:
                video_writer.release()
                cap.release()
                return 1
            else:
                video_writer.release()
                cap.release()
                return 0

print(detect_h())
