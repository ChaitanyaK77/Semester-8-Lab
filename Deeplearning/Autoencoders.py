import pandas as pd
import numpy as np
from tensorflow.keras.datasets import mnist
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense,Dropout,MaxPooling2D,Flatten,Conv2D,BatchNormalization,UpSampling2D

import matplotlib.pyplot as plt

(X_train,y_train),(X_test,y_test) = mnist.load_data()

X_train = X_train/255.0
X_test = X_test/255.0
X_train = X_train.reshape(-1,28,28,1)
X_test = X_test.reshape(-1,28,28,1)

autoencoder = Sequential()
autoencoder.add(Conv2D(32,(3,3),activation='relu',padding='same',input_shape=(28,28,1)))


autoencoder.add(MaxPooling2D(pool_size=(2,2),padding='same'))
autoencoder.add(Conv2D(16,(3,3),activation ='relu',padding='same'))
autoencoder.add(MaxPooling2D(pool_size=(2,2),padding='same'))

autoencoder.add(Conv2D(16,(3,3),activation='relu',padding='same'))
autoencoder.add(UpSampling2D(size=(2,2)))
autoencoder.add(Conv2D(32,(3,3),activation='relu',padding='same'))
autoencoder.add(UpSampling2D(size= (2,2)))

autoencoder.add(Conv2D(1,(3,3),activation='sigmoid',padding='same'))
autoencoder.summary()
autoencoder.compile(optimizer='adam',loss='binary_crossentropy')
autoencoder.fit(X_train,X_train,epochs=1,batch_size=32,validation_data=(X_test,X_test))

reconstructed_im = autoencoder.predict(X_test)
plt.figure(figsize=(20,4))
plt.imshow(reconstructed_im[0].reshape(28,28,1))
plt.show()


