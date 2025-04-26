from ipc2 import udp_process
udp_process('B',9877,[("hello from B to A",9876),("hello from B to C",9878)])
input("Press enter to exit\n")
