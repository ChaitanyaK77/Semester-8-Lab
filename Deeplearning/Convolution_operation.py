import numpy as np
import math
input = np.array([
    [1,2,0,3,1],
    [4,5,1,1,2],
    [0,1,3,2,1],
    [3,4,2,6,9],
    [3,4,1,2,1]
])

filter = np.array([
    [1,0,-1],
    [1,0,-1],
    [1,0,-1]
])

padding = np.zeros((7,7))
print(padding)
h = input.shape[0]
p=1
padding[1:6,1:6] = input
print(padding)
input=padding

w = input.shape[1]
fh = filter.shape[0]
fw = filter.shape[1]
stride = 3
output_size = int(math.floor(((h-fh+2*p)/stride)+1))
print(output_size)
output = np.zeros((output_size,output_size))
print(output)
print()




for i in range(0,output_size):
    for j in range(0,output_size):
        row = i*stride
        col = j*stride
        region = input[row:row+fh,col:col+fh]
        output[i,j] = np.sum(region*filter)
        print(region)

print(output)


