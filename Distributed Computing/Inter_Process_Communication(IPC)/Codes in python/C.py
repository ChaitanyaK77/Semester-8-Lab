from ipc2 import udp_process
udp_process('C',9878,[("hello from C to A",9876),("hello from C to B",9877)])
input("Press enter to exit\n")
