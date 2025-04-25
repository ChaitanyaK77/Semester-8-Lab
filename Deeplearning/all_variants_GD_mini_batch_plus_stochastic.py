import numpy as np
import matplotlib.pyplot as plt

# Generate random ground truth and predictions
y = np.random.randint(0, 100, 100)  # Random integers between 0 and 100
y_hat = np.random.randint(0, 100, 100)

# Mean Squared Error Loss
def loss(y, y_hat):
    return (1 / len(y)) * np.sum((y - y_hat) ** 2)

# Gradient w.r.t. predictions
def grad(y, y_hat):
    return (2 / len(y)) * (y_hat - y)

# Gradient Descent on y_hat
def gradient_descent(y, y_hat, lr=0.01, steps=10000):
    losses = []
    for _ in range(steps):
        g = grad(y, y_hat)              # Gradient of MSE wrt predictions
        y_hat = y_hat - lr * g          # Update predictions
        losses.append(loss(y, y_hat))   # Track loss
    return losses

# Run
losses = gradient_descent(y, y_hat)
print(losses)

# Plot
plt.plot(losses)
plt.xlabel("Steps")
plt.ylabel("Loss")
plt.title("Gradient Descent on MSE")
plt.grid(True)
plt.show()



# below is stochastic

import numpy as np
import matplotlib.pyplot as plt

# Generate random ground truth and predictions
y = np.random.randint(0, 100, 100)  # Random integers between 0 and 100
y_hat = np.random.randint(0, 100, 100)

# Mean Squared Error Loss
def loss(y, y_hat):
    return (1 / len(y)) * np.sum((y - y_hat) ** 2)

# Gradient w.r.t. predictions
def grad(y, y_hat):
    return 2 * (y_hat - y)

# Gradient Descent on y_hat
def gradient_descent(y, y_hat, lr=0.01, steps=1000):
    n = len(y)
    losses = []
    for _ in range(steps):
        rnd = np.random.randint(0,n)
        g = grad(y[rnd], y_hat[rnd])              # Gradient of MSE wrt predictions
        y_hat[rnd] = y_hat[rnd] - lr * g          # Update predictions
        losses.append(loss(y, y_hat))   # Track loss
    return losses

# Run
losses = gradient_descent(y, y_hat)
print(losses)

# Plot
plt.plot(losses)
plt.xlabel("Steps")
plt.ylabel("Loss")
plt.title("Gradient Descent on MSE")
plt.grid(True)
plt.show()


# below is mini batch
import numpy as np
import matplotlib.pyplot as plt

# Generate random ground truth and predictions
y = np.random.randint(0, 100, 100)  # Random integers between 0 and 100
y_hat = np.random.randint(0, 100, 100)

# Mean Squared Error Loss
def loss(y, y_hat):
    return (1 / len(y)) * np.sum((y - y_hat) ** 2)

# Gradient w.r.t. predictions
def grad(y, y_hat):
    return (2 / len(y)) * (y_hat - y)

# Gradient Descent on y_hat
def gradient_descent(y, y_hat, lr=0.01, steps=10000,batch_size=10,beta=0.99):
    v=0
    n = len(y)
    losses = []
    for _ in range(steps):

        batch = np.random.choice(n,batch_size,replace=False)
        print(batch)
        g = grad(y[batch], y_hat[batch])
        wla = y_hat[batch] - beta*(v)
        v = beta * v + lr * wla
        y_hat[batch] = y_hat[batch] -  v          # Update predictions
        losses.append(loss(y, y_hat))   # Track loss
    return losses

# Run
losses = gradient_descent(y, y_hat)
print(losses)

# Plot
plt.plot(losses)
plt.xlabel("Steps")
plt.ylabel("Loss")
plt.title("Gradient Descent on MSE")
plt.grid(True)
plt.show()
