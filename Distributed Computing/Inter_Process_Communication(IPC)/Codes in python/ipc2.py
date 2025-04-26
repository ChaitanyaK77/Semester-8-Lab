import threading,socket
def udp_process(name,recv_port,targets):
    def receive():
        s = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
        s.bind(('localhost',recv_port))
        while 1:
            data,_ = s.recvfrom(1024)
            print(name+"received"+data.decode())
    threading.Thread(target=receive,daemon=True).start()
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    for msg,port in targets:
        s.sendto(msg.encode(),('localhost',port))
    s.close()
