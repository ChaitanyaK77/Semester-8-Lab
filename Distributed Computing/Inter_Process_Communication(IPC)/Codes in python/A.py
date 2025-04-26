from ipc2 import udp_process
udp_process('A',9876,[("hello from A to B",9877),("hello from A to C",9878)])
input("Press enter to exit\n")
