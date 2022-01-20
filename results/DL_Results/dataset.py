import os
import cv2
import glob
from sklearn.utils import shuffle
import numpy as np
import pandas as pd
from sklearn import preprocessing

def load_train(train_path, classes, image_size, reshape):
    images = []
    labels = []
    attributes = ['file','midisoundbank','midisequencer','rmi','serialize','rhino','sms','atomic','jlist','colorconvertopfilter','sqldriver','storeimagearray','suntracing','mbeanserverintrospector','mbeaninstantiator','alphacomposite','findstaticsetter','lookupbytebi','alphacompositecompose','execute','det']
    data = pd.DataFrame(columns=attributes)

    tmp_df = pd.read_csv('binary_attr_jarhead.csv')

    print('Going to read training data')
    for fields in classes:
        index = classes.index(fields)

        print('Now going to read {} files (Index: {})'.format(fields, index))
        path = os.path.join(train_path, fields, '*g')
        files = glob.glob(path)
        for fl in files:
            if not fl:
                print('No file!',files)
                continue
            image = cv2.imread(fl)
            image = cv2.resize(image, (image_size, image_size),0,0, cv2.INTER_LINEAR)
            image = image.astype(np.float32)
            image = np.multiply(image, 1.0 / 255.0)
            images.append(image)
            label = np.zeros(len(classes))
            label[index] = 1
            labels.append(label)

            head, tail = os.path.split(fl)
            string_ = tail.split("_")
            #index_list = tmp_df[(tmp_df['file']== (string_[0]+'.jar'))].index.tolist()
            index_list = tmp_df[tmp_df['file'].str.contains(string_[0])==True].index.tolist()
           
            if not index_list:
                print('Not in jarhead',fields,' ',index_list,' fl ',fl,' t ',tail)
                raise SystemExit()

            data = data.append((tmp_df.loc[index_list]).iloc[0,:],ignore_index=True)

    images = np.array(images)
    labels = np.array(labels)

    height, width, channels = reshape

    data_set= data.iloc[:, 1:].values
    data_set = data_set.reshape(len(data_set), height, width, channels)

    return data_set, images, labels


class DataSet(object):

  def __init__(self, data, images, labels):
    self._num_examples = data.shape[0]

    self._data = data
    self._images = images
    self._labels = labels

    self._epochs_done = 0
    self._index_in_epoch = 0

  @property
  def data(self):
    return self._data

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
  def epochs_done(self):
    return self._epochs_done

  def next_batch(self, batch_size):
    """Return the next `batch_size` examples from this data set."""
    start = self._index_in_epoch
    self._index_in_epoch += batch_size

    if self._index_in_epoch > self._num_examples:
      # After each epoch we update this
      self._epochs_done += 1
      start = 0
      self._index_in_epoch = batch_size
      assert batch_size <= self._num_examples
    end = self._index_in_epoch

    return self._data[start:end], self._images[start:end], self._labels[start:end]


def read_train_sets(train_path, classes, image_size, validation_size, reshape):
    class DataSets(object):
        pass
    data_sets = DataSets()
    
    data, images, labels = load_train(train_path, classes, image_size, reshape)
    data, images, labels = shuffle(data, images, labels)
    
    data_sets.validation_option = True
    if not validation_size==0:
        if isinstance(validation_size, float):
            validation_size = int(validation_size * data.shape[0])
    else:
        data_sets.validation_option = False
        validation_size = int(0)
            
    if data_sets.validation_option:
        validation_data = data[:validation_size]
        validation_images = images[:validation_size]
        validation_labels = labels[:validation_size]
        data_sets.valid = DataSet(validation_data, validation_images, validation_labels)
        
    train_data = data[validation_size:]
    train_images = images[validation_size:]
    train_labels = labels[validation_size:]
        
    data_sets.train = DataSet(train_data, train_images, train_labels)

    return data_sets