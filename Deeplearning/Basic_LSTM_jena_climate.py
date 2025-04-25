import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense,LSTM
import matplotlib.pyplot as plt

df = pd.read_csv('/Users/chaitanyakakade/PycharmProjects/PythonProject2/.venv/jena_climate_2009_2016.csv')
print(df.head())
df1 = df['T (degC)'].to_list()



X,y = [],[]

for i in range(len(df1)):
    end_ix = i+24
    if end_ix>len(df1)-1:
        break
    seq_x,seq_y = df1[i:end_ix],df1[end_ix]
    X.append(seq_x)
    y.append(seq_y)
X = np.array(X)
y = np.array(y)
print(X)

X_train,X_test,y_train,y_test = train_test_split(X,y,test_size=0.2)
print(X_train.shape,y_train.shape)

model = Sequential()
model.add(LSTM(50,activation='relu',return_sequences=True,input_shape=(24,1)))
model.add(LSTM(50,activation='relu'))
model.add(Dense(1))
model.summary()


model.compile(optimizer='adam',loss='mse')
model.fit(X_train,y_train,batch_size=32,epochs=2)

pred = model.predict(X_test[10])
plt.figure(figsize=(20,6))
plt.plot(pred[:300],color='red')
