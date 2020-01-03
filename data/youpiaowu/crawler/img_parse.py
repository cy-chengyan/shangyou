#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import json

with open('pics.lst', 'w', encoding='utf-8') as picfile:
    with open('product.json', encoding='utf-8') as file:
        for line in file:
            # print(line)
            j = json.loads(line)
            # print(j['data']['pictures'])
            pictures = j['data']['pictures']
            if not pictures is None:
                for p in pictures:
                    picfile.writelines([p, '\n'])
                    # print(p)
            # break
