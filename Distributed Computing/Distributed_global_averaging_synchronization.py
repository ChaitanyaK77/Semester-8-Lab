node_A = int(input("Enter Node A time"))
node_B = int(input("Enter Node B time"))
node_C = int(input("Enter Node C time"))
threshold = 50
node_a_skew = [node_B-node_A,node_C-node_A]
node_b_skew = [node_A-node_B,node_C-node_B]
print(node_a_skew)
node_a_skew = [s for s in node_a_skew if abs(s)<=threshold]
node_b_skew = [s for s in node_b_skew if abs(s)<=threshold]
print(node_a_skew)
print(node_b_skew)

avg_skew_a = sum(node_a_skew)//len(node_a_skew)
avg_skew_b = sum(node_b_skew)//len(node_b_skew)
print(avg_skew_a)
print(avg_skew_b)
print("Corrected time for A = {}".format(node_A+avg_skew_a))
print("Corrected time for B = {}".format(node_B+avg_skew_b))


