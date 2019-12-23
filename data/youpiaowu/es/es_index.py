#!/usr/bin/python
#encoding: utf-8

import sys
import json
import requests

def add_index(line):
    data = json.loads(line)
    stid = data['stid']
    r = requests.put('http://192.168.7.7:9200/stamp/' + stid + '/_create', json = data)
    print(r)

with open('../stamp.json') as f:
    line = f.readline()
    while line:
        # 解析一行数据
        line = line.strip()
        add_index(line)
        # 读取下一行
        line = f.readline()
