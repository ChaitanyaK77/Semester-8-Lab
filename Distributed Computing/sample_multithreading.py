import threading
import time


def add(a, b):
    time.sleep(1)
    print("{} + {} = {}".format(a, b, a + b))


t1 = threading.Thread(target=add, args=(10, 5))
t2 = threading.Thread(target=add, args=(20, 30))

#
t1.start()
t2.start()

t1.join()
t2.join()

print("Done!")
