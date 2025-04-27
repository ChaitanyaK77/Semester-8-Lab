import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.decomposition import LatentDirichletAllocation
import nltk
from nltk.corpus import stopwords
from wordcloud import WordCloud

df = pd.read_csv('/Users/chaitanyakakade/PycharmProjects/PythonProject2/sentimentdataset.csv')
print(df.columns)
comments = df['Text'].astype(str).str.lower().values

nltk.download('stopwords')
stop_words = stopwords.words('english')

vectorizer = TfidfVectorizer(stop_words = stop_words,max_df=0.95,min_df=2)
lda = LatentDirichletAllocation(n_components = 5,random_state=42)
X = vectorizer.fit_transform(comments)
lda.fit(X)

words = vectorizer.get_feature_names_out()
topics = lda.components_
print(topics)
for i in range(3):
    top = words[topics[i].argsort()[:5]]
    print(top)

