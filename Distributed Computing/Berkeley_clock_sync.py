server = int(input("Enter server time in minutes "))
client_1 = int(input("Enter client1 time "))
client_2 = int(input("Enter client2 time "))

print("Server Time --> {}:{}".format(server//60,server%60))
print("Client1 Time --> {}:{}".format(client_1//60,client_1%60))
print("Client2 Time --> {}:{}".format(client_2//60,client_2%60))

average = (server+client_1+client_2)//3
print(average)

client1_adjust = average-client_1
client_2_adjust = average-client_2
server_adjust = average-server

print("{},{},{}".format(client1_adjust,client_2_adjust,server_adjust))

print("Server Synchronized time = {}".format(server+server_adjust))
print("client_1 Synchronized time = {}".format(client_1+client1_adjust))
print("client_2 Synchronized time = {}".format(client_2+client_2_adjust))
sync_time = server+server_adjust
print("time on all clocks is = {}:{}".format((sync_time)//60,(sync_time%60)))
