#!/usr/bin/python
#encoding: utf-8

import sys
import json
import requests

def add_index(line):
    data = json.loads(line)
    stid = data['stid']
    url = 'http://192.168.7.7:9200/stamp/_doc/' + stid
    r = requests.put(url, json = data)
    print(url, r.json())

with open('../stamp.json') as f:
    line = f.readline()
    while line:
        # 解析一行数据
        line = line.strip()
        add_index(line)
        # 读取下一行
        line = f.readline()
