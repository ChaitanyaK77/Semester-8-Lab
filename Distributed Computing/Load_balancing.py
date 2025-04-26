node = {0:4,1:2,2:3,3:1}
node[4] = 1
print(node)

'''
1--add a process
2--add a node
3-- delete a node
4-- remove a process
'''

while True:
    inp = input("Enter 1,2,3,4,exit")
    if inp=='1':
        nid = min(node,key = node.get)
        node[nid]+=1
        print(node)
    elif inp=='2':
        nid = max(node)
        nid+=1
        node[nid] = 0
        print(node)
    elif inp=='3':
        nid = int(input("Node id to delete: "))
        load = node[nid]
        del node[nid]
        for i in range(load):
            n = min(node,key = node.get)
            node[n] +=1
        print(node)
    elif inp=='4':
        nid = int(input("Remove process from? "))
        node[nid]-=1
        print(node)
    else: break

