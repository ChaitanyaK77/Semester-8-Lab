p = int(input("Enter total processes: "))
n = int(input("Enter total no of resource instances: "))

available = list(map(int,input("Enter available resources : ").split()))
allocated = [list(map(int,input("Enter the allocated resources to P{} ".format(i)).split())) for i in range(p)]
maximum_required = [list(map(int,input("Enter the max required resources to P{} ".format(i)).split())) for i in range(p)]

need = []

for i in range(p):
    row = [maximum_required[i][j]-allocated[i][j] for j in range(n)]
    need.append(row)

print(need)

finish = [False]*p
safe = []

while len(safe)<p:
    for i in range(p):
        if(not finish[i] and all(need[i][j]<=available[j] for j in range(n))):
            available = [available[j]+allocated[i][j] for j in range(n)]
            finish[i] = True
            safe.append(i)
print(safe)
