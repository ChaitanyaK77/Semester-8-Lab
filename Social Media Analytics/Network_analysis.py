import networkx as nx
from networkx.algorithms.community import girvan_newman
import pandas as pd
import matplotlib.pyplot as plt
df = pd.read_csv('/Users/chaitanyakakade/PycharmProjects/PythonProject2/ga_edgelist.csv')

'''G = nx.from_pandas_edgelist(df,source="from",target = "to")
nx.draw(G,with_labels=True)'''
edges = [
    ("Alice","Bob"),
    ("Alice","Chaitanya"),
    ("Chaitanya","Bob"),
    ("Chaitanya","Kevin"),
    ("Rabbit","Pet")
]
G = nx.Graph()
G.add_edges_from(edges)
nx.draw(G)

plt.show()
'''
G = nx.from_pandas_edgelist(df,source='from',target='to')
nx.draw(G,with_labels=True)
plt.show()

communities = next(girvan_newman(G))
for community in communities:
    print("Community {}".format(community))

bw = nx.betweenness_centrality(G)
print(bw)'''
