#!/usr/bin/python
#encoding: utf-8

import sys
import MySQLdb
import json
import hashlib
import re

db = None

def read_sub_stamps(stid):
    sub_stamps = []
    sql = "select sstid, stid, `order`, title, picture, face_value, issued_number from t_sub_stamp where stid = '%s'" % stid
    cur = db.cursor()
    cur.execute(sql)
    rows = cur.fetchall()
    for row in rows:
        sub_stamp = {
            "sstid": row[0],
            "order": row[2],
            "title": row[3],
            "picture": row[4],
            "face_value": row[5],
            "issued_number": row[6]
            }
        sub_stamps.append(sub_stamp)
    cur.close()
    return sub_stamps
    

def dump():
    sql = "select stid, `type`, year, name, countryid, number, issued_date, joint_issue, size, chikong, `format`, fanwei," \
          " designer, editor, carve, side_design, draw, shoot, printing_house, background, picture from t_stamp"
    cur = db.cursor()
    cur.execute(sql)
    rows = cur.fetchall()
    for row in rows:
        stamp = {
            "stid": row[0],
            "type": row[1],
            "year": row[2],
            "name": row[3],
            "countryid": row[4],
            "number": row[5],
            "issued_date": row[6],
            "joint_issue": row[7],
            "size": row[8],
            "chikong": row[9],
            "format": row[10],
            "fanwei": row[11],
            "designer": row[12],
            "editor": row[13],
            "carve": row[14],
            "side_design": row[15],
            "draw": row[16],
            "shoot": row[17],
            "printing_hourse": row[18],
            "background": row[19],
            "picture": row[20],
            "sub_stamps": read_sub_stamps(row[0])
            }
        # print(stamp)
        print(json.dumps(stamp))
    cur.close()


def main():
    global db
    db_host = "192.168.7.7"
    db_user = "shuoen"
    db_pwd = "TewtQ81_%&$"
    db_name = "shangyou_v1"
    db = MySQLdb.connect(db_host, db_user, db_pwd, db_name, charset="utf8mb4")
    dump()
    if db:
        db.close()


if __name__ == "__main__":
    main()

