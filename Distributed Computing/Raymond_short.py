# Raymond’s Tree‑Based Mutual Exclusion (nodes 0–5)

# parent[i] = parent of node i
parent = [0, 0, 0, 1, 1, 2]  # now covers 0 through 5

token = 0
queues = [[] for _ in parent]

while True:
    s = input("Enter process (0–5) for CS or 'exit': ")
    if s == 'exit':
        break
    req = int(s)
    if not (0 <= req < len(parent)):
        print("Please enter a valid process ID (0–5).")
        continue
    # enqueue and forward up to token holder
    queues[req].append(req)
    cur = req
    while cur != token:
        print(f"Process {cur} sending Request to parent {parent[cur]}")
        cur = parent[cur]
        queues[cur].append(req)
    # pass token
    print("\nRequest Queue:", queues)
    print(f"Process {token} sends token to {req}")
    queues[token].remove(req)
    token = req

    # enter & release CS
    print("\nRequest Queue:", queues)
    print(f"Process {req} enters CS")
    print(f"Release CS — holder is now {token}\n")
