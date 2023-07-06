import numpy as np,numpy
import csv
import glob
import os

window_size = 1000
threshold = 60
slide_size = 200 #less than window_size!!!

def dataimport(path1, path2):
	xx = np.empty([0,window_size,90],float)
	yy = np.empty([0, 6], float) # given matrix is a number of class

	###Input data###
	#data import from csv
	input_csv_files = sorted(glob.glob(path1))
	for f in input_csv_files:
		print("input_file_name=",f)
		data = [[ float(elm) for elm in v] for v in csv.reader(open(f, "r"))]
		tmp1 = np.array(data)
		x2 =np.empty([0,window_size,90],float)

		#data import by slide window
		k = 0
		while k <= (len(tmp1) + 1 - 2 * window_size):
			x = np.dstack(np.array(tmp1[k:k+window_size, 0:90]).T)
			x2 = np.concatenate((x2, x),axis=0)
			k += slide_size

		xx = np.concatenate((xx,x2),axis=0)
	xx = xx.reshape(len(xx),-1)
	#푸리에 변환
	# f = np.fft.fft2(xx)
	# fshift = np.fft.fftshift(f)
	# xx = 20 * np.log(fshift)

	###Annotation data###
	#data import from csv
	annotation_csv_files = sorted(glob.glob(path2))
	for ff in annotation_csv_files:
		print("annotation_file_name=",ff)
		ano_data = [[str(elm) for elm in v] for v in csv.reader(open(ff,"r"))]
		tmp2 = np.array(ano_data)

		#data import by slide window
		y = np.zeros(((len(tmp2) + 1 - 2 * window_size)//slide_size+1,6)) # the last parameter should be the number of class
		k = 0
		while k <= (len(tmp2) + 1 - 2 * window_size):
			y_pre = np.stack(np.array(tmp2[k:k+window_size]))
			aTwalk =0 #modified
			aTstand = 0
			aTsit = 0
			aTlay = 0
			stn = 0
			for j in range(window_size):
				if y_pre[j] == "backlay": #modified
					aTwalk += 1
				elif y_pre[j] == "forlay": #modified
					aTstand += 1
				elif y_pre[j] == "indanger": #modified
					aTsit += 1
				elif y_pre[j] == "onfloor": #modified
					aTlay += 1
				elif y_pre[j] == "empty": #modified - stn might need to be obmitted in the future
					stn += 1

			if aTwalk > window_size * threshold / 100: #modified
				y[k // slide_size, :] = np.array([0, 1, 0, 0, 0, 0])
			elif aTstand > window_size * threshold / 100: #modified
				y[k // slide_size, :] = np.array([0, 0, 1, 0, 0, 0])
			elif aTsit > window_size * threshold / 100: #modified
				y[k // slide_size, :] = np.array([0, 0, 0, 1, 0, 0])
			elif aTlay > window_size * threshold / 100: #modified
				y[k // slide_size, :] = np.array([0, 0, 0, 0, 1, 0])
			elif stn > window_size * threshold / 100: #modified - stn might need to be obmitted in the future
				y[k // slide_size, :] = np.array([0, 0, 0, 0, 0, 1])
			else:
				y[k//slide_size,:] = np.array([2,0,0,0,0,0]) # should not be deleted
			k += slide_size
		yy = np.concatenate((yy, y),axis=0)
	print(xx.shape,yy.shape)
	return (xx, yy)


#### Main ####
if not os.path.exists("input_files_kt_yr/"):
	os.makedirs("input_files_kt_yr/")
	# print("../")
for i, label in enumerate (["backlay", "forlay", "indanger", "onfloor", "empty"]): #modified - stn might need to be obmitted in the future
	filepath1 = "C:\\MyPaperWork_YH\\src\\matlab_converter\\20230620_KT\\20230620_data\\20230620_data\\2306_"+ str(label) + "_yr\\2306_"+ str(label)+"_yr\\" + str(label)  + "*.csv"
	filepath2 = "C:\\MyPaperWork_YH\\src\\matlab_converter\\20230620_KT\\20230620_data\\20230620_data\\2306_"+ str(label) + "_yr\\2306_"+ str(label)+"_yr\\annotation\\annotation_" + str(label)  + "*.csv"

	outputfilename1 = "./input_files_kt_yr/xx_" + str(window_size) + "_" + str(threshold) + "_" + label + ".csv"
	outputfilename2 = "./input_files_kt_yr/yy_" + str(window_size) + "_" + str(threshold) + "_" + label + ".csv"
	print(filepath1,filepath2)
	x, y = dataimport(filepath1, filepath2)
	with open(outputfilename1, "w") as f:
		writer = csv.writer(f, lineterminator="\n")
		writer.writerows(x)
	with open(outputfilename2, "w") as f:
		writer = csv.writer(f, lineterminator="\n")
		writer.writerows(y)
	print(label + "finish!")