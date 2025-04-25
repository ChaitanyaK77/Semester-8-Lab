# i have used the Delta Learning rule here.. with sigmoid activation function\

import numpy as np


def sigmoid(z):
    return (1 / (1 + np.exp(-z)))


def derivative_sig(z):
    return (z * (1 - z))


X = np.array([
    [1, -2, 0, -1],
    [0, 1.5, -0.5, -1],
    [-1, 1, 0.5, -1]
])
d = np.array([-1, -1, 1])
w = np.array([1, -1, 0, 0.5])
c = 1

for i in range(3):
    net = np.dot(X[i], w)
    f_net = sigmoid(net)
    o = f_net
    print(o)
    f_dash_net = derivative_sig(f_net)
    if d[i] != o:
        w = w + c * (d[i] - o) * f_dash_net * X[i]
print(w)
