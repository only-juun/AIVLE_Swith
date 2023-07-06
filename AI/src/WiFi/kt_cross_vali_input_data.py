"""Functions for downloading and reading MNIST data."""
from __future__ import print_function
import gzip
import os
import numpy as np, numpy
import csv
import glob
import pandas as pd


class DataSet(object):
    def __init__(self, images, labels, fake_data=False):
        assert images.shape[0] == labels.shape[0], (
                "images.shape: %s labels.shape: %s" % (images.shape,
                                                       labels.shape))
        self._num_examples = images.shape[0]
        images = images.reshape(images.shape[0],
                                images.shape[1] * images.shape[2])
        self._images = images
        self._labels = labels
        self._epochs_completed = 0
        self._index_in_epoch = 0

    @property
    def images(self):
        return self._images

    @property
    def labels(self):
        return self._labels

    @property
    def num_examples(self):
        return self._num_examples

    @property
    def epochs_completed(self):
        return self._epochs_completed

    def next_batch(self, batch_size, fake_data=False):
        start = self._index_in_epoch
        self._index_in_epoch += batch_size
        if self._index_in_epoch > self._num_examples:
            # Finished epoch
            self._epochs_completed += 1
            # Shuffle the data
            perm = numpy.arange(self._num_examples)
            numpy.random.shuffle(perm)
            self._images = self._images[perm]
            self._labels = self._labels[perm]
            # Start next epoch
            start = 0
            self._index_in_epoch = batch_size
            assert batch_size <= self._num_examples
        end = self._index_in_epoch
        return self._images[start:end], self._labels[start:end]


def csv_import():
    x_dic = {}
    y_dic = {}
    print("csv file importing...")

    for i in ["backlay", "forlay", "empty", "onfloor", "indanger"]:  # modified

        # modified
        SKIPROW = 2  # Skip every 2 rows -> overlap 800ms to 600ms  (To avoid memory error)

        num_lines = sum(1 for l in open("Raw_data/input_files_kt_yh/xx_1000_60_" + str(i) + ".csv"))

        # modified
        skip_idx = [x for x in range(1, num_lines) if x % SKIPROW != 0]

        # 여기 skip_idx 없애보자
        xx = np.array(pd.read_csv("Raw_data/input_files_kt_yh/xx_1000_60_" + str(i) + ".csv", header=None,
                                  skiprows=None))  # skip_idx 여기 잘해야함. window size 1000에서 500으로 줌

        yy = np.array(pd.read_csv("Raw_data/input_files_kt_yh/yy_1000_60_" + str(i) + ".csv", header=None, skiprows=None))#skiprows==none

        xx = xx.reshape(len(xx), 1000, 90)

        # modified
        # 1000 Hz to 500 Hz (To avoid memory error)
        # 메모리 에러 안났으니까 그냥해보자
        xx = xx[:, ::2, :90]

        x_dic[str(i)] = xx
        y_dic[str(i)] = yy

        print(str(i), "finished...", "xx=", xx.shape, "yy=", yy.shape)

    return x_dic["backlay"], x_dic["forlay"], x_dic["empty"], x_dic["onfloor"], x_dic["indanger"], \
           y_dic["backlay"], y_dic["forlay"], y_dic["empty"], y_dic["onfloor"], y_dic["indanger"]
    # modified - might need to obmit stn in the future
