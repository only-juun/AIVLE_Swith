# -*- coding=utf-8 -*-
# Kalman filter example demo in Python

Q = 1e-5  # process variance
R = 0.1 **  3.2# estimate of measurement variance, change to see effect
import numpy
import pylab
import scipy.io as spio
import numpy as np
import pandas as pd
import csv

class kalmanfilter():
    def __init__(
            self,
            sequence):
        self.sequence = sequence
        self.n_iter = len(sequence)
        self.sz = (self.n_iter,)  # size of array
        self.noise = numpy.random.normal(self.sequence, 0.1, size=self.sz)  # observations (normal about x, sigma=0.1)
    # allocate space for arrays
        self.filterOperation()
        # self.display()

    def filterOperation(self):
        self.xhat = numpy.zeros(self.sz)  # a posteri estimate of x
        self.P = numpy.zeros(self.sz)  # a posteri error estimate
        self.xhatminus = numpy.zeros(self.sz)  # a priori estimate of x
        self.Pminus = numpy.zeros(self.sz)  # a priori error estimate
        self.KalmanGain = numpy.zeros(self.sz)  # gain or blending factor
        # intial guesses
        self.xhat[0] = self.sequence[0]
        self.P[0] = 10

        for k in range(1, self.n_iter):
            # time update
            self.xhatminus[k] = self.xhat[k - 1]  # X(k|k-1) = AX(k-1|k-1) + BU(k) + W(k),A=1,BU(k) = 0
            self.Pminus[k] = self.P[k - 1] + Q  # P(k|k-1) = AP(k-1|k-1)A' + Q(k) ,A=1
            # measurement update
            self.KalmanGain[k] = self.Pminus[k] / (self.Pminus[k] + R)  # Kg(k)=P(k|k-1)H'/[HP(k|k-1)H' + R],H=1
            self.xhat[k] = self.xhatminus[k] + self.KalmanGain[k] * (self.noise[k] - self.xhatminus[k])  # X(k|k) = X(k|k-1) + Kg(k)[Z(k) - HX(k|k-1)], H=1
            self.P[k] = (1 - self.KalmanGain[k]) * self.Pminus[k]  # P(k|k) = (1 - Kg(k)H)P(k|k-1), H=1

    def display(self):
        pylab.figure()
        pylab.plot(self.noise, 'k+', label='noisy measurements')  #Measurements
        pylab.plot(self.xhat, 'b-', label='kalman-based results')  # Filterd Value
        # pylab.plot(self.sequence, color='g', label='original value')  # System value
        pylab.legend(loc = 'best')
        pylab.xlabel('Iteration')
        pylab.ylabel('Voltage')
        pylab.ylim(0, 40)
        pylab.show()
        print('Done')

    def feedback(self):
        return self.xhat

if __name__ == "__main__":
    print(1)
    filename = '../CSI_LSTM/input_files_school22/xx_1000_60_aTs.csv'  #p
    # mat = spio.loadmat('test.mat', squeeze_me=True)
    # mat = mat['test_data']
    # print(type(mat))
    # print(mat)
    print(2)
    mat = np.array(pd.read_csv(filename, header=None))
    print(3)
    print(mat.shape)
    print(4)
    print("+--------------------------+")
    mat1 = mat.T # Transpose
    mat1 = numpy.transpose(mat1)
    # print(type(mat1))
    # print(mat1[0][:]) # read row by row
    # b = mat[0][:]
    c = mat1[0][:]
    k = kalmanfilter(c)
    # matTemp3 = []
    # matTemp3 = pd.DataFrame()
    matTemp3 =[]
    # k.feedback()
    # matS = spio.loadmat('test.mat', squeeze_me=True)
    # matFiltered = matS

    # matTemp = []
    # for data in mat:
    #     matTemp.append(np.absolute(data))
    # matTemp = np.array(matTemp)
    print(5)

    matTemp2 = []
    for i in range(1,750): #29->90
   # b=mat[i][:]
        c=mat1[i][:]
        t = kalmanfilter(c)
        ################################################################
        # matTemp2.append(t.feedback())
        matTemp3.append(t.feedback())
    matTemp3 = np.array(matTemp3)


        ################################################################
        # matTemp.append(t.feedback())
        # matTemp  = pd.concat([matTemp, t.feedback()]).reset_index(drop=True)
        #
        # matTemp2 = t.feedback()
        # matTemp=np.hstack((matTemp, matTemp2))
        #
        # matTemp = np.array(matTemp)
    # print(matTemp)

    df = pd.DataFrame(matTemp3)
    df.to_csv("xx_1000_60_aTs.csv",header=None,index=None)

    # with open('xx_1000_60_go_sleep_kalman.csv', "w") as f:
        # writer = csv.writer(f, lineterminator="\n")
        # writer.writerows(matTemp3)

        #matTemp = np.r_[matTemp, t.feedback()]
    #print(t.feedback())
    #print(matTemp)

        #matTemp[i][:] = t.feedback()
    #print(type(matTemp))
    #print(matTemp)
#kalmanfilter
#kalmanfilter(c)
#x.display()

# matlab import
# transpose
# original data's x axis is frequency rate. y axis is time
# if you transpose this matrix, now each row is time serial amplitude data per different frequency

# origianlly tried to read altogether and process, but couldnt figure out how.

