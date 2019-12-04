#!/usr/bin/python
#encoding: utf-8

#
# 1. 解析product.json
# 2. 将数据dump到数据表 
#

import sys
import MySQLdb
import json
import hashlib

SRC_FILE = './product.json'

db = None

def general_stid(org_id):
    s = str(org_id)
    h = hashlib.md5(s).hexdigest() #32
    return 's' + h[25:]

def general_sub_stid(title):
    h = hashlib.md5(title.encode('utf-8')).hexdigest() #32
    return 'z' + h[25:]

def get_country_id(d):
    stage = d['stage']
    return int(stage['id'])


def get_list_in_items(list_name, d):
    for item in d:
        if item['title'] == list_name:
            return item['list']
    return None


def get_attr_in_list(attr_name, d):
    for item in d:
        if item[0] == attr_name:
            return item[1]
    return None


def parse_stamp_info(stid, data):
    items = data['items']
    countryid = get_country_id(data)
    stamp_info = get_list_in_items(u'基本属性', items)
    if not stamp_info:
        return False

    number = get_attr_in_list(u'编号', stamp_info)
    if not number:
        print '找不到 number 字段'
        return False

    issued_date = get_attr_in_list(u'发行日期', stamp_info)
    size = get_attr_in_list(u'尺寸', stamp_info)
    chikong = get_attr_in_list(u'齿孔', stamp_info)
    fmt = get_attr_in_list(u'版式', stamp_info)
    fanwei = get_attr_in_list(u'防伪技术', stamp_info)
    designer =get_attr_in_list(u'邮票设计', stamp_info)
    editor = get_attr_in_list(u'责任编辑', stamp_info)
    printing_house = get_attr_in_list(u'印刷厂', stamp_info)

    background_info = get_list_in_items(u'背景资料', items)
    if background_info:
        background = background_info[0][0]
    else:
        background = None

    print stid, countryid, number, issued_date, size, chikong, fmt, fanwei, designer, editor, printing_house, background

    sql = "insert into t_stamp(stid, countryid, `number`, issued_date, size, chikong, format, fanwei, designer, editor, printing_house, background)" \
          " values(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
    cursor_insert = db.cursor()
    cursor_insert.execute(sql, (stid, countryid, number, issued_date, size, chikong, fmt, fanwei, designer, editor, printing_house, background))
    cursor_insert.close()
    db.commit()
    
    return True


#子邮票
def parse_sub_stamp(stid, data):
    items = data['items']
    sub_stamp = get_list_in_items(u'图序', items)
    if not sub_stamp:
        return

    for item in sub_stamp:
        order = item[0]
        title = item[1]
        picture = ''
        if len(item) > 2:
            face_value = item[2]
        else:
            face_value = None
        if len(item) > 3:
            issued_number = item[3]
        else:
            issued_number = None
        sstid = general_sub_stid(title)
        print sstid, stid, order, title, picture, face_value, issued_number
        sql = "insert into t_sub_stamp(sstid, stid, order, title, picture, face_value, issued_number)" \ 
              " values(%s, %s, %d, %s, %s, %s, %s)"
        cursor_insert = db.cursor()
        cursor_insert.execute(sql, (sstid, stid, order, title, picture, face_value, issued_number))
        cursor_insert.close()
        db.commit()
        
        return True


#大版
def parse_big_format(stid, data):
    items = data['items']
    big_format = get_list_in_items(u'大版', items)
    if not big_format:
        return

    bgid = ''
    bgsize = get_attr_in_list(u'尺寸', big_format)
    bgnumber = get_attr_in_list(u'枚数', big_format)
    print bgid, stid, bgsize, bgnumber
    sql = "insert into t_big_format(bgid, stid, bgsize, bgnumber)" \
          "values(%s, %s, %s, %d)"
    cursor_insert = db.cursor()
    cursor_insert.execute(sql, (bgid, stid, bgsize, bgnumber))
    cursor_insert.close()
    db.commit()
    
    return True


#小版
def parse_small_format(stid, data):
    items = data['items']
    small_format = get_list_in_items(u'小版', items)
    if not small_format:
        return
    slfid = ''
    slsize = get_attr_in_list(u'尺寸', small_format)
    slnumber = get_attr_in_list(u'枚数', small_format)
    print slfid, stid ,slsize, slnumber
    sql = "insert into t_small_format(slfid, stid, slsize, slnumber)" \
          "values(%s, %s, %s, %d)"
    cursor_insert = db.cursor()
    cursor_insert.execute(sql, (slfid, stid, slsize, slnumber))
    cursor_insert.close()
    db.commit()
    
    return True


#赠送票
def parse_zengsong(stid,data):
    items = data['items']


#小本票
def parse_xiaoben(stid, data):
    items = data['items']
    xiaoben = get_list_in_items(u'小本票', items)
    if not xiaoben:
        return
    xiaobenid = ''
    number = get_attr_in_list(u'编号', xiaoben)
    face_value = get_attr_in_list(u'面值', xiaoben)
    print xiaobenid, stid, number, face_value
    sql = "insert into t_xiaoben(xiaobenid, stid, number, face_value)" \
          "values(%s, %s, %s, %s)"
    cursor_insert = db.cursor()
    cursor_insert.excute(sql, (slfid, stid, slsize, slnumber))
    cursor_insert.close()
    db.commit()
    
    return True


#小型张
def parse_small_sheet(stid, data):
    items = data['items']
    small_sheet = get_list_in_items(u'小型张', items)
    if not small_sheet:
        return
    slsid = ''
    face_value = get_attr_in_list(u'面值', small_sheet)
    size = get_attr_in_list(u'尺寸', small_sheet)
    image = get_attr_in_list(u'邮票主图', small_sheet)
    chikong = get_attr_in_list(u'齿孔', small_sheet)
    draw = get_ attr_in_list(u'绘画', small_sheet)
    print slsid, stid, face_value, size, image, chikong, draw
    sql = "insert into t_small_sheet(slsid, stid, face_value, size, image, chikong, draw)" \
          "values(%s, %s, %s, %s, %s, %s, %s)"
    cursor_insert = db.cursor()
    cursor_insert.excute(sql, (slsid, stid, face_value, size, image, chikong, draw))
    cursor_insert.close()
    db.commit()
    
    return True


def parse_line(line):
    #print line
    j = json.loads(line)
    data = j['data']
    stid = general_stid(data['id'])

    if not parse_stamp_info(stid, data):
        return
    parse_sub_stamp(stid, data)
    #parse_big_format(stid, data)
    # parse_...
    parse_big_format(stid, data)


def parse():
    with open(SRC_FILE) as f:
        line = f.readline()
        while line:
            # 解析一行数据
            line = line.strip()
            parse_line(line)
            # 读取下一行
            line = f.readline()


def main():
    global db
    db_host = "192.168.7.7"
    db_user = "shuoen"
    db_pwd = "TewtQ81_%&$"
    db_name = "shangyou_v1"
    db = MySQLdb.connect(db_host, db_user, db_pwd, db_name, charset="utf8")
    parse()
    if db:
        db.close()


if __name__ == "__main__":
    main()



 
