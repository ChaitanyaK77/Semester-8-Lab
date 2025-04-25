# I have used the standard learning rule with signum function as the activation function...


import numpy as np
def signum(x):
    if x>0:
        return 1
    elif x<0:
        return -1
    else:
        return 0

X = np.array([
    [1,1,1],
    [0,0,1],
    [1,0,1],
    [0,1,1]
])

w = np.array([0,1,-1.5])
d = np.array([1,1,0,0])

for _ in range(10):
    for i in range(4):
        net = np.dot(X[i],w)
        f_net = signum(net)
        o = f_net

        if o!=d[i]:
            w = w+(d[i]-o)*X[i]
print("Final weights after 10 iterations is {}".format(w))


