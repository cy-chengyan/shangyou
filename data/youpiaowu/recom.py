#!/usr/bin/python
#encoding: utf-8

import sys
import json
from bs4 import BeautifulSoup
import jieba
import gensim

SRC_FILE = './stamp.json'

doc_map = {}
stop_words = {}
docs = []
corpus = []
dictionary = {}


def load_stopwords():
    global stop_words
    stop_words = {line.strip().decode('utf-8') for line in open('./stopwords.lst').readlines()}
    stop_words.add(' ')


def add_doc(line, i):
    global docs
    global doc_map

    data = json.loads(line)
    doc_map[i] = data

    #stid = data['stid']
    words = []

    name = data['name']
    if name:
        name_words = jieba.cut(name, cut_all=False)
        ws = [x for x in name_words if x not in stop_words]
        words.extend(ws)
    else:
        name_words = None

    background = data['background']
    if background:
        soup = BeautifulSoup(background)
        background = soup.get_text()
        background_words = jieba.cut(background, cut_all=False)
        ws = [x for x in background_words if x not in stop_words]
        words.extend(ws)
    else:
        background_words = None

    docs.append(words)


def make_corpus():
    global corpus
    global dictionary

    with open(SRC_FILE) as f:
        i = 0
        line = f.readline()
        while line:
            # 解析一行数据
            line = line.strip()
            add_doc(line, i)
            # 读取下一行
            line = f.readline()
            i = i + 1

    dictionary = gensim.corpora.Dictionary(docs)
    corpus = [dictionary.doc2bow(doc) for doc in docs]    


def cmp_doc_score(d1, d2):
    if d2['score'] - d1['score'] > 0.0:
        return 1
    else:
        return -1


# 加载停用词
load_stopwords()
# 加载语料
make_corpus()


# 使用tfidf计算每个文档的相似度（距离）
# 取最相近的前10个文档
tfidf = gensim.models.TfidfModel(corpus)
index = gensim.similarities.SparseMatrixSimilarity(tfidf[corpus], num_features = len(dictionary.keys()))

for d in range(len(docs)):
    doc = docs[d]
    words = dictionary.doc2bow(doc)
    sim = index[tfidf[words]]

    results = []
    for i in range(len(sim)):
        data = doc_map[i]
        r = {
            "score": sim[i],
            "stid": data['stid'],
            "name": data['name']
        }
        results.append(r)

    results.sort(cmp = cmp_doc_score)

    cur_data = doc_map[d]
    stid = cur_data['stid']
    name = cur_data['name']
    print("= %s, %s" % (stid, name))
    value = []
    for i in range(11):
        result = results[i]
        if result['stid'] == stid:
            continue
        print("> %s, %s, %f" % (result['stid'], result['name'], result['score']))
        value.append(result['stid'] + '|' + str(result['score']))
    print("REDIS:\tSET %s %s" % ('RE_' + stid, ','.join(value)))

