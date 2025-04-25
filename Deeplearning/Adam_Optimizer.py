import numpy as np
import matplotlib.pyplot as plt

w = np.array([34,65,45,33,22,9,8,9,4,8])
def loss(w):
    return np.sum((w-3)**2)
def grad(w):
    return (2*(w-3))

def adam(w,lr=0.1,b1=0.9,b2=0.98,iter=1000,eta = 0.01):
    v = 0
    m = 0
    t = 0
    losses = []
    for _ in range(iter):
        t+=1
        g = grad(w)
        m = b1*m + (1-b1) *g
        v = b2*v + (1-b2) * g**2
        m_hat = m/(1-(b1**t))
        v_hat = v/(1-(b2**t))
        w = w - ((lr/np.sqrt(v_hat+eta))*m_hat)
        print("Loss = {} and weights = {}".format(loss(w),w))
        losses.append(loss(w))
    return losses
losses = adam(w)
plt.figure(figsize=(10,4))
plt.plot(losses)
plt.xlabel=('steps')
plt.ylabel=('loss')
plt.title('loss curve')
plt.show()

