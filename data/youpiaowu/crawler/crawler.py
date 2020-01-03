#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import requests
import json

URL = 'https://youpiaowu.cn/api/stamp/formatted-detail'
# 头信息需要通过在浏览器查看页面后得到 'https://youpiaowu.cn/detail/stamp-2267'
HEADERS = {
    'Origin': 'https://youpiaowu.cn',
    'Accept-Encoding': 'gzip, deflate, br',
    'x-csrf-token': 'USTOvXwd-9rbbC-9CJcjYU8v_3OTe3JpsV6w',
    'Accept-Language': 'zh-CN,zh;q=0.9',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36',
    'Content-Type': 'application/json;charset=UTF-8',
    'Accept': 'application/json',
    'Referer': 'https://youpiaowu.cn/',
    'Cookie': 'EGG_SESS=4-jZwJRY3zXw3vyx1smA8c-750Q2c8AS43H89Twcr_wH-luCQMignPjjDfawq2AeQB-899L8FR8ibgsNKQBrywiQlJaYCGohSCxQL0HLBOPNWaBUHPs9uDLeXLXBHEzj64-Oyy7nsl3ONhkufzvhfdEs4NiJDYD14--55R6-mWQ=',
    'Connection': 'keep-alive'
}
MAX_INDEX = 2321

dest_file = open('youpiaowu.json', 'wb')

index = 2270
while index < MAX_INDEX:
    payload = {"id": str(index)}
    payload = json.dumps(payload)
    print(payload)
    r = requests.post(URL, headers=HEADERS, data=payload)
    # print(r.content)
    dest_file.write(r.text.encode('utf-8'))
    dest_file.write('\n'.encode())
    index = index + 1

dest_file.close()
