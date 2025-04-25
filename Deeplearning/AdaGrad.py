import numpy as np
import matplotlib.pyplot as plt

w = np.array([34,65,45,33,22,9,8,9,4,8])
def loss(w):
    return np.sum((w-3)**2)
def grad(w):
    return (2*(w-3))

def Adagrad(w,lr=0.1,beta=0.9,iter=10000,eta = 0.01):
    v = 0
    losses = []
    for _ in range(iter):
        g = grad(w)
        v = v + g**2
        w = w - (lr/np.sqrt(v+eta))*g
        print("Loss = {} and weights = {}".format(loss(w),w))
        losses.append(loss(w))
    return losses
losses = Adagrad(w)
plt.figure(figsize=(10,4))
plt.plot(losses)
plt.xlabel=('steps')
plt.ylabel=('loss')
plt.title('loss curve')
plt.show()

