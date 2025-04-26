import socket,threading
s,c = socket.socket(),[]
s.bind(('',1234));
s.listen()

def h(x):
    n = x.recv(99).decode().strip()
    b(f"{n} joined\n")
    while 1: b(f"{n}:{x.recv(99).decode()}")
def b(m):
    [i.send(m.encode()) for i in c]
print("On 1234")
while 1:
    x,_ = s.accept()
    c+=[x]
    x.send(b'Name\n')
    threading.Thread(target=h,args=(x,),daemon=True).start()
