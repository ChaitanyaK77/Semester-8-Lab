import matplotlib.pyplot as plt
import pandas as pd
from textblob import TextBlob
import seaborn as sns
from wordcloud import WordCloud
df = pd.read_csv('/Users/chaitanyakakade/PycharmProjects/PythonProject2/sentimentdataset.csv')
print(df.head())
print(df.columns)

def get_sentiment(comment):
    return TextBlob(comment).sentiment.polarity
def sentiment_category(score):
    if score>0:
        return 'positive'
    elif score<0:
        return 'negative'
    else:
        return 'neutral'
df['new_sentiment'] = df['Text'].apply(get_sentiment)
df['Sentiment_category'] = df['new_sentiment'].apply(sentiment_category)
text = ' '.join(df['Text'])
wc = WordCloud(width=1000,height=1000).generate(text)
plt.imshow(wc)
plt.figure(figsize=(20,10))
#df['Sentiment_category'].value_counts().plot(kind='bar')
#plt.show()
df.drop(columns=['Unnamed: 0.1','Unnamed: 0','Text','Sentiment','Timestamp','Country','User','Platform','Hashtags','Sentiment_category'],axis=1,inplace=True)
print(df.dtypes)

print(df.corr())
#sns.heatmap(df.corr())
sns.scatterplot(x=df['Retweets'],y=df['Likes'])
plt.show()

def like_rate(val):
    if val>35:
        return "High"
    else:
        return "Low"
df['Likes'] = df['Likes'].apply(like_rate)
print(df['Likes'][:20])

df['Likes'].value_counts().plot(kind='barh')
plt.show()
