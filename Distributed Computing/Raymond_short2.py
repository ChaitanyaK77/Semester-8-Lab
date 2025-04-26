adj = [[0,0,0,0,0,0],
       [1,0,0,0,0,0],
       [1,0,0,0,0,0],
       [0,1,0,0,0,0],
       [0,1,0,0,0,0],
       [0,0,1,0,0,0]]
holder = 0
q = [[] for _ in range(6)]

def parent(p): 
     return next((i for i in range(6) if adj[p][i]))

def print_queues():
    print("Request Queues:")
    for i in range(6):
        print(f" P{i}: {q[i]}")
    print()


def request_cs(p):
    global holder
    q[p].append(p)
    print_queues()
    if holder != p:
        pr = parent(p)
        print(f"Process {p} → Request → Parent {pr}")
        q[pr].append(p)
        request_cs(pr)
        print(f"Parent {pr} → Token → Process {p}")
        holder = p
        q[pr].remove(p)
    q[p].remove(p)
    print(f"Process {p} enters and exits CS\n")
    print_queues()

while True:
        p = int(input("Enter process (-1 to exit): "))
        if p == -1: break
        if 0 <= p < 6: request_cs(p)
        else: print("Invalid process.")



