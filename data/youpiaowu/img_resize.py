#!/usr/bin/python
#encoding: utf-8

import os, sys
from PIL import Image

SRC_FILE = './pics.lst'
IMG_SRC_DIR = '/home/chengyan/data/imgs/'
IMG_DEST_DIR = '/home/chengyan/data/thumbnail/'
MAX_SIZE = 400, 400

def proc(line):
    line = line.strip()
    src_path = IMG_SRC_DIR + line
    dirname, basename = os.path.split(line) 
    dest_dir = IMG_DEST_DIR + dirname
    if not os.path.exists(dest_dir):
        # os.makedirs(dest_dir, mode = 0o755, exist_ok = True)
        os.makedirs(dest_dir, mode = 0o755)
    dest_path = dest_dir + '/' + basename
    # dest_path, file_ext = os.path.splitext(dest_path)
    # dest_path = dest_path + '.jpeg'
    # print(dest_dir, dest_path)
    print('%s => %s' % (src_path, dest_path))

    try:
        img = Image.open(src_path)
        img.thumbnail(MAX_SIZE, Image.ANTIALIAS)
        img.save(dest_path)
    except IOError:
        print("cannot create thumbnail for '%s'" % line)


with open(SRC_FILE) as f:
    line = f.readline()
    while line:
        proc(line)
        line = f.readline()


