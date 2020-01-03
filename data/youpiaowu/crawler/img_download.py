#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import requests

HOST = 'https://s.axcdn.com/'
LOCAL_DIR = 'imgs/'

with open('pics.lst', encoding='utf-8') as picfile:
    for p in picfile:
        p = p.strip()
        if len(p):
            dirname, basename = os.path.split(p)
            directory = LOCAL_DIR + dirname
            if not os.path.exists(directory):
                os.makedirs(directory, mode=0o755, exist_ok=True)
            # print(directory, basename)

            # 下载文件
            fullpath = directory + '/' + basename
            print(fullpath)
            url = HOST + p
            # print(url)
            try:
                r = requests.get(url)
                with open(fullpath, 'wb') as imgfile:
                    imgfile.write(r.content)
            except:
                print('[ERR]', url)
            
