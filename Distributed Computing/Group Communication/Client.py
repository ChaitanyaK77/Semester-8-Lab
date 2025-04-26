import socket,threading
s = socket.socket()
s.connect(('localhost',1234))
threading.Thread(target=lambda:[print(s.recv(99).decode(),end='') for _ in iter(int,1)]).start()
while 1: s.send((input()+'\n').encode())
