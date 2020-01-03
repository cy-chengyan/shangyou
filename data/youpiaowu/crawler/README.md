
# 邮票屋数据

```text
注意: 保存文件时，文件换行符使用 '\n' (Unix换行)
```

1. crawler.py 下载邮票数据json
2. 使用vim把没有结果的记录删除掉，然后保存成 product.json
3. img_parse.py 解析 product.json，解析出所有的图片文件
4. 使用 img_download.py 下载所有的图片，保存到imgs目录下
