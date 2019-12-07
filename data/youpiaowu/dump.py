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
import re

SRC_FILE = './product.json'

db = None

def general_stid(org_id):
    s = str(org_id)
    h = hashlib.md5(s).hexdigest() #32
    return 's' + h[25:]

def general_sub_stid(stid, title, order):
    s = stid + title + str(order)
    h = hashlib.md5(s.encode('utf-8')).hexdigest() #32
    return 'z' + h[25:]

def general_big_format(stid, order):
    s = stid + 'bf' + str(order)
    h = hashlib.md5(s).hexdigest() #32
    return 'b' + h[25:]

def general_small_format_id(stid, order):
    s = stid + 'sf' + str(order)
    h = hashlib.md5(s).hexdigest()
    return 'm' + h[25:]

def general_zengsong_id(stid, order):
    s = stid + 'g' + str(order)
    h = hashlib.md5(s).hexdigest()
    return 'g' + h[25:]

def general_xiaoben_id(stid, order):
    s = stid + 'x' + str(order)
    h = hashlib.md5(s).hexdigest()
    return 'x' + h[25:]

def general_small_sheet_id(stid, order):
    s = stid + 't' + str(order)
    h = hashlib.md5(s).hexdigest()
    return 't' + h[25:]

def general_mini_sheet_id(stid, order):
    s = stid + 'm' + str(order)
    h = hashlib.md5(s).hexdigest()
    return 'm' + h[25:]

def general_four_sheet_id(stid, order):
    s = stid + 'f' + str(order)
    h = hashlib.md5(s).hexdigest()
    return 'f' + h[25:]

def general_double_sheet_id(stid, order):
    s = stid + 'd' + str(order)
    h = hashlib.md5(s).hexdigest()
    return 'd' + h[25:]

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
    
    ##改动##
    joint_issue = get_attr_in_list(u'联合发行', stamp_info)
    if not joint_issue:
        joint_issue = None
    carve = get_attr_in_list(u'雕刻', stamp_info)
    if not carve:
        carve = None
    side_design = get_attr_in_list(u'边饰设计', stamp_info)
    if not side_design:
        side_design = None
    draw = get_attr_in_list(u'绘画', stamp_info)
    if not draw:
        draw = None
    shoot = get_attr_in_list(u'摄影', stamp_info)
    if not shoot:
        shoot = None


    background_info = get_list_in_items(u'背景资料', items)
    if background_info:
        background = background_info[0][0]
    else:
        background = None

#    print stid, countryid, number, issued_date, size, chikong, fmt, fanwei, designer, editor, printing_house, background
    print stid, countryid, number, issued_date, joint_issue, size, chikong, fmt, fanwei, designer, editor, carve, side_design, draw, shoot, printing_house, background 
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

        face_value = ''
        issued_number = ''
        for v in item[2:]:
            if (v[-1:] == u'元' or v[-1:] == u'分'):
                if len(face_value) > 0:
                    face_value = face_value + u','
                face_value = face_value + v
            else:
                if len(issued_number) > 0:
                    issued_number = issued_number + u','
                issued_number = v

        if len(face_value) == 0:
            face_value = None
        if len(issued_number) == 0:
            issued_number = None

        sstid = general_sub_stid(stid, title, order)
        print sstid, stid, order, title, picture, face_value, issued_number
        sql = "insert into t_sub_stamp(sstid, stid, `order`, title, picture, face_value, issued_number)" \
              " values(%s, %s, %s, %s, %s, %s, %s)"
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

    org_size = get_attr_in_list(u'尺寸', big_format)
    org_number = get_attr_in_list(u'枚数', big_format)

    if (not org_size) or (not org_number):
        return

    org_size_items = org_size.split('\x01')
    org_number_items = org_number.split('\x01')

    items = []
    i = 0
    for org_size_item in org_size_items:
        if i < len(org_number_items):
            org_number_item = org_number_items[i].strip()
        else:
            org_number_item = None
        items.append({"size": org_size_item.strip(), "num": org_number_item})
        i = i + 1

    order = 0
    for item in items:
        bgid = general_big_format(stid, order)
        bgsize = item['size']
        bgnumber = item['num']
        print bgid, stid, bgsize, bgnumber

        sql = "insert into t_big_format(bgid, stid, bgsize, bgnumber)" \
              "values(%s, %s, %s, %s)"
        cursor_insert = db.cursor()
        cursor_insert.execute(sql, (bgid, stid, bgsize, bgnumber))
        cursor_insert.close()
        db.commit()

        order = order + 1
    
    return True


#小版
def parse_small_format(stid, data):
    items = data['items']
    small_format = get_list_in_items(u'小版', items)
    if not small_format:
        return
    
    org_slsize = get_attr_in_list(u'尺寸', small_format)
    org_slnumber = get_attr_in_list(u'枚数', small_format)
    org_issued_number = get_attr_in_list(u'发行量', small_format)
#    if (not org_slsize) or (not org_slnumber):
#        return
    if org_slsize:
        org_slsize_items = org_slsize.split('\x01')
    else:
        org_slsize_items = ''
    if org_slnumber:
        org_slnumber_items = org_slnumber.split('\x01')
    else:
        org_slnumber_items = ''
    if org_issued_number:
        org_issued_number_items = org_issued_number.split('\x01')
    else:
        org_issued_number_items = ''
    items = []
    i = 0
    for org_slsize_item in org_slsize_items:
        if i < len(org_slnumber_items):
            org_slnumber_item = org_slnumber_items[i].strip()
        else:
            org_slnumber_item = None
        if i < len(org_issued_number_items):
            org_issued_number_item = org_issued_number_items[i].strip()
        else:
            org_issued_number_item = None
        items.append({"size":org_slsize_item.strip(), "num":org_slnumber_item, "issued_number":org_issued_number_item})
        i = i + 1

    order = 0
    for item in items:
        slfid = general_small_format_id(stid, order)
        slsize = item['size']
        slnumber = item['num']
        issued_number = item['issued_number']
        print slfid, stid ,slsize, slnumber, issued_number
        sql = "insert into t_small_format(slfid, stid, slsize, slnumber, issued_number)" \
              "values(%s, %s, %s, %s, %s)"
        cursor_insert = db.cursor()
        cursor_insert.execute(sql, (slfid, stid, slsize, slnumber, issued_number))
        cursor_insert.close()
        db.commit()

        order = order + 1

    return True


#赠送票
def parse_zengsong(stid, data):
    items = data['items']
    zengsong = get_list_in_items(u'赠送版',items)
    if not zengsong:
        return

    org_zeng_size = get_attr_in_list(u'尺寸',zengsong)
    org_zeng_number = get_attr_in_list(u'枚数',zengsong)
    if (not org_zeng_size) or (not org_zeng_size):
        return

    org_zeng_size_items = org_zeng_size.split('\x01')
    org_zeng_number_items = org_zeng_number.split('\x01')
    items = []
    i = 0
    for org_zeng_size_item in org_zeng_size_items:
        if i < len(org_zeng_number_items):
            org_zeng_number_item = org_zeng_number_items[i].strip()
        else:
            org_zeng_number_item = None
        items.append({"size":org_zeng_size_item.strip(), "num":org_zeng_number_item})
        i = i + 1

    order = 0
    for item in items:
        zengid = general_zengsong_id(stid, order)
        zeng_size = item['size']
        zeng_number = item['num']
        print zengid, stid, zeng_size, zeng_number

        sql = "insert into t_zengsong(zengid, stid, zeng_size, zeng_number)" \
              "values(%s, %s, %s, %s)"
        cursor_insert = db.cursor()
        cursor_insert.execute(sql, (zengid, stid, zeng_size, zeng_number))
        cursor_insert.close()
        db.commit()
        order = order + 1
    return True


#小本票
def parse_xiaoben(stid, data):
    items = data['items']
    xiaoben = get_list_in_items(u'小本票', items)
    if not xiaoben:
        return

    org_number = get_attr_in_list(u'编号', xiaoben)
    org_face_value = get_attr_in_list(u'面值', xiaoben)
    org_size = get_attr_in_list(u'尺寸', xiaoben)
    org_issued_number = get_attr_in_list(u'发行量', xiaoben)
    org_designer = get_attr_in_list(u'邮票设计', xiaoben)
    org_editor = get_attr_in_list(u'责任编辑', xiaoben)
    org_printing_house = get_attr_in_list(u'印刷厂', xiaoben)

#    if (not org_number) or (not org_face_value):
#        return

    if org_number:
        org_number_items = org_number.split('\x01')
    else:
        org_number_items = ''
    if org_face_value:
        org_face_value_items = org_face_value.split('\x01')
    else:
        org_face_value_items = ''
    if org_size:
        org_size_items = org_size.split('\x01')
    else:
        org_size_items = ''
    if org_issued_number:
        org_issued_number_items = org_issued_number.split('\x01')
    else:
        org_issued_number_items = ''
    if org_designer:
        org_designer_items = org_designer.split('\x01')
    else:
        org_designer_items = ''
    if org_editor:
        org_editor_items = org_editor.split('\x01')
    else:
        org_editor_items = ''
    if org_printing_house:
        org_printing_house_items = org_printing_house.split('\x01')
    else:
        org_printing_house_items = ''


    items = []
    i = 0
    for org_number_item in org_number_items:
        if i < len(org_face_value_items):
            org_face_value_item = org_face_value_items[i].strip()
        else:
            org_face_value_item = None
        if i < len(org_size_items):
            org_size_item = org_size_items[i].strip()
        else:
            org_size_item = None
        if i < len(org_issued_number_items):
            org_issued_number_item = org_issued_number_items[i].strip()
        else:
            org_issued_number_item = None
        if i < len(org_designer_items):
            org_designer_item = org_designer_items[i].strip()
        else:
            org_designer_item = None
        if i < len(org_editor_items):
            org_editor_item = org_editor_items[i].strip()
        else:
            org_editor_item = None
        if i < len(org_printing_house_items):
            org_printing_house_item = org_printing_house_items[i].strip()
        else:
            org_printing_house_item = None

        items.append({"num":org_number_item.strip(), "value":org_face_value_item, "size":org_size_item, "issued_number":org_issued_number_item, "designer":org_designer_item, "editor":org_editor_item, "printing_house":org_printing_house_item})
        i = i + 1
   
    order = 0
    for item in items:
        xiaobenid = general_xiaoben_id(stid, order)
        number = item['num']
        face_value = item['value']
        size = item['size']
        issued_number = item['issued_number']
        designer = item['designer']
        editor = item['editor']
        printing_house = item['printing_house']
        print xiaobenid, stid, number, face_value
        
        sql = "insert into t_xiaoben(xiaobenid, stid, `number`, size, issued_number, face_value, designer, editor, printing_house)" \
              "values(%s, %s, %s, %s, %s, %s, %s, %s, %s)"
        cursor_insert = db.cursor()
        cursor_insert.execute(sql, (xiaobenid, stid, number, size, issued_number, face_value, designer, editor, printing_house))
        cursor_insert.close()
        db.commit()

        order = order + 1

    return True


#小型张
def parse_small_sheet(stid, data):
    items = data['items']
    small_sheet = get_list_in_items(u'小型张', items)
    if not small_sheet:
        return
    org_face_value = get_attr_in_list(u'面值', small_sheet)
    print org_face_value
    org_size = get_attr_in_list(u'外形尺寸', small_sheet)
    print org_size
    org_image = get_attr_in_list(u'邮票主图', small_sheet)
    print org_image
    org_chikong = get_attr_in_list(u'齿孔', small_sheet)
    print org_chikong
    org_draw = get_attr_in_list(u'绘画', small_sheet)
    print org_draw
    org_designer = get_attr_in_list(u'邮票设计', small_sheet)

    
    if not org_face_value:
        org_face_value_items = ''
    else:
        org_face_value_items = org_face_value.split('\x01')
    if not org_size:
        org_size_items = ''
    else:
        org_size_items = org_size.split('\x01')
    if not org_image:
        org_image_items = ''
    else:
        org_image_items = org_image.split('\x01')
    if not org_chikong:
        org_chikong_items = ''
    else:
        org_chikong_items = org_chikong.split('\x01')
    if not org_draw:
        org_draw_items = ''
    else:
        org_draw_items = org_draw.split('\x01')
    if not org_designer:
        org_designer_items = ''
    else:
        org_designer_items = org_designer.split('\x01')

    items = []
    i = 0
    
    for org_face_value_item in org_face_value_items:

        if i < len(org_size_items):
            org_size_item = org_size_items[i].strip()
        else:
            org_size_item = None

        if i < len(org_image_items):
            org_image_item = org_image_items[i].strip()
        else:
            org_image_item = None

        if i < len(org_chikong_items):
            org_chikong_item = org_chikong_items[i].strip()
        else:
            org_chikong_item = None

        if i < len(org_draw_items):
            org_draw_item = org_draw_items[i].strip()
        else:
            org_draw_item = None
        if i < len(org_designer_items):
            org_designer_item = org_designer_items[i].strip()
        else:
            org_designer_item = None
        items.append({"value": org_face_value_item.strip(), "size": org_size_item, "image": org_image_item, "chikong": org_chikong_item, "draw": org_draw_item, "designer":org_designer_item})
        i = i + 1

    order = 0
    for item in items:
        slsid = general_small_sheet_id(stid, order)
        face_value = item['value']
        size = item['size']
        image = item['image']
        chikong = item['chikong']
        draw = item['draw']
        designer = item['designer']

        print slsid, stid, face_value, size, image, chikong, draw
        sql = "insert into t_small_sheet(slsid, stid, face_value, size, image, chikong, designer, draw)" \
              "values(%s, %s, %s, %s, %s, %s, %s, %s)"
        cursor_insert = db.cursor()
        cursor_insert.execute(sql, (slsid, stid, face_value, size, image, chikong, designer, draw))
        cursor_insert.close()
        db.commit()

        order = order + 1

    return True

#小全张
def parse_mini_sheet(stid, data):
    item = data['items']
    mini_sheet = get_list_in_items(u'小全张', item)
    if not mini_sheet:
        return
    org_value = get_attr_in_list(u'面值', mini_sheet)
    org_size = get_attr_in_list(u'尺寸', mini_sheet)
    
    if (not org_value) or (not org_size):
        return
    org_value_items = org_value.split('\x01')
    org_size_items = org_size.split('\x01')

    items = []
    i = 0

    for org_value_item in org_value_items:
        if i < len(org_size_items):
            org_size_item = org_size_items[i].strip()
        else:
            org_size_item = None
        items.append({"value":org_value_item.strip(), "size":org_size_item})
        i = i + 1

    order = 0
    for item in items:
        minid = general_mini_sheet_id(stid, order)
        face_value = item['value']
        size = item['size']
        sql = "insert into t_mini_sheet(minid, stid, face_value, size)" \
              "values(%s, %s, %s, %s)"
        cursor_insert = db.cursor()
        cursor_insert.execute(sql, (minid, stid, face_value, size))
        cursor_insert.close()
        db.commit()

        ordre  = order + 1

    return True

#四连体小型张
def parse_four_sheet(stid, data):
    item = data['items']
    four_sheet = get_list_in_items(u'四连体小型张', item)
    if not four_sheet:
        return

    org_value = get_attr_in_list(u'面值', four_sheet)
    org_size = get_attr_in_list(u'外形尺寸', four_sheet)

    if (not org_value) or (not org_size):
        return
    org_value_items = org_value.split('\x01')
    org_size_items = org_value.split('\x01')
    
    items = []
    i = 0
    for org_value_item in org_value_items:
        if i < len(org_size_items):
            org_size_item = org_size_items[i].strip()
        else:
            org_size_item = None
        items.append({"value":org_value_item.strip(), "size":org_size_item})
        i = i + 1

    order = 0
    for item in items:
        fsid = general_four_sheet_id(stid, order)
        face_value = item['value']
        size = item['size']
        print fsid, stid, face_value, size
        
        sql = "insert into t_four_sheet(fsid, stid, face_value, size)" \
             "values(%s, %s, %s, %s)"
        cursor_insert = db.cursor()
        cursor_insert.execute(sql, (fsid, stid, face_value, size))
        cursor_insert.close()
        db.commit()
    return True

#双联小型张
def parse_double_sheet(stid, data):
    item = data['items']
    double_sheet = get_list_in_items(u'双联小型张', item)
    if not double_sheet:
        return
    org_size = get_attr_in_list(u'外形尺寸', double_sheet)
    if not org_size:
        return
    org_size_items = org_size.split('\x01')
    
    items = []
    i = 0
    for org_size_item in org_size_items:
        if i < len(org_size_items):
            org_size_item = org_size_items[i].strip()
        else:
            org_size_item = None
        items.append({"size":org_size_item.strip()})
        i = i + 1
    
    order = 0
    for item in items:
        dsid = general_double_sheet_id(stid, order)
        size = item['size']
        print dsid, stid, size
        sql = "insert into t_double_sheet(dsid, stid, size)" \
              "values(%s, %s, %s)"
        cursor_insert = db.cursor()
        cursor_insert.execute(sql, (dsid, stid, size))
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
    parse_big_format(stid, data)
    parse_small_format(stid, data)
    parse_zengsong(stid, data)
    parse_xiaoben(stid, data)
    parse_small_sheet(stid, data)
    parse_mini_sheet(stid, data)
    parse_four_sheet(stid, data)
    parse_double_sheet(stid, data)


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



 
