
n = int(input("Enter number of processes: "))
crashed = int(input("Enter crashed process IDs (space-separated): "))
initiator = int(input("Enter the process that initiates the election: "))

current = initiator
print(current)
print(crashed)
count=0
while True:
    if crashed == current:
        current+=1
        continue
    print("\nProcess {} starts election...".format(current))
    higher_process_found = False
    for i in range(current + 1, n):
        print("  Election message sent from {} to {}".format(current, i))
        if i!=crashed:
            print("  OKAY message received from {}".format(i))
            higher_process_found = True
        else:
            print("{} not responding".format(i))
    
    if not higher_process_found:
        print("\nProcess {} becomes the COORDINATOR.".format(current))
        for i in range(n):
            if i != current and i != crashed:
                print("  Coordinator message sent to {}".format(i))
        break
    else:
        current += 1  # The next higher process takes over the election
print(count)

