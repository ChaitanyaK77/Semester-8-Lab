import numpy as np
import matplotlib.pyplot as plt
w = np.array([1,3,4,5,7,5,4,5,9])

def loss(w):
    return np.sum((w-3)**2)

def grad(w):
    return 2*(w-3)

def grad_descent(w,lr = 0.01,epoch=10):
    losses = []
    for i in range(epoch):
        g = grad(w)
        w = w - lr*(g)
        print("W = {} and Loss = {}".format(w,loss(w)))
        losses.append(loss(w))
    return losses

losses = grad_descent(w)
print(losses)
plt.figure(figsize=(10,4))
plt.plot(losses)
plt.xlabel('steps')
plt.ylabel('loss')
plt.title('loss curve')
plt.show()
