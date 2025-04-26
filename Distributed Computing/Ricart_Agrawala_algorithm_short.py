request = [(1,1),(2,2),(3,3)]
cs_duration = 3
cs_end_time = request[0][1]+cs_duration-1
deferredQ = []
import time

for pid,t in request:
    if pid==1:
        print(f"Current Time is {t}")
        print(f"Process {pid} Requests for CS")
        print(f"Process {pid} Enters CS")

    elif t<=cs_end_time:
        print("Current Time = {}".format(t))
        print(f"Process {pid} is requesting for CS but gets Deferred")
        deferredQ.append(pid)
        print(deferredQ)
    else:
        print(f"Process{pid} Enters CS")
reply_time = cs_duration+1

for pid in deferredQ:
    print(f"Current Time = {reply_time}")
    print(f"Process {pid} gets requests and then enters CS")
    reply_time+=cs_duration
    print(f"Process {pid} Now leaves CS at time {reply_time+1}")
