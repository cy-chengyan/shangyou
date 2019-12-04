
create database shangyou_v1;
ALTER DATABASE 	shangyou_v1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

use shangyou_v1;

create user 'qilpsian'@'%' identified by '密码';
grant all on shangyou_v1.* to 'qilpsian'@'%';

--用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
       'uid' char(16) PRIMARY KEY COMMENT '用户id',
       'mobile_number' varchar(32) NOT NULL COMMENT '手机号码',
       'nickname' varchar(32）NOT NULL COMMENT ‘昵称’，
       'gender' tinyint(1) NOT NULL COMMENT '性别,1男,2女,3未知',
       'created_at' bigint(20) NOT NULL COMMENT '创建时间',
       'updated_at' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP()
        ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间'
       )ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='用户表'; 
      -- CREATE UNIQUE INDEX i_user_mobile ON t_user('mobile_number');

--收藏夹表
DROP TABLE IF EXISTS t_favorite;
CREATE TABLE t_favorite(
--自己的id，用户id，邮票id，创建时间，更新时间，状态值
       'faid' varchar(8) PRIMARY KEY,
       'uid' char(16) NOT NULL COMMENT '用户id',
       'stid' varchar(16) NOT NULL COMMENT '邮票id',
       'created_at' bigint(20) NOT NULL COMMENT '创建时间',
       'updated_at' timestamp NOT NULL COMMENT CURRENT_TIMESTAMP()
       ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间',
       'status' tinyint(1) NOT NULL COMMENT '1收藏,2未收藏');  

--邮票详情表
DROP TABLE IF EXISTS t_stamp;
CREATE TABLE t_stamp(
       'stid' varchar(16) PRIMARY KEY COMMENT '邮票id',
       'title' varchar(16) NOT NULL COMMENT '邮票标题',
       'picture' varchar(256) NOT COMMENT '邮票图片',
       'facevalue' varchar(16) NOT NULL COMMENT '邮票面值',
       'number' varchar(16) NOT NULL COMMENT '邮票编号',
       'issuedate' bigint(20) NOT NULL COMMENT '邮票发布日期',
       'size' varchar(16) NOT NULL COMMENT '邮票尺寸',
       'chikong' varchar(3) NOT NULL COMMENT '邮票齿孔',
       'format' varchar(8) NOT NULL COMMENT '邮票版式',
       'fanwei' varchar(16) NOT NULL COMMENT '防伪技术',
       'design' varchar(16) NOT NULL COMMENT '邮票设计',
       'edit' varchar(16) NOT NULL COMMENT '责任编辑',
       'printing' varchar(16) NOT NULL COMMENT '印刷厂',
       'backgroundinf' varchar(256) NOT NULL COMMENT '背景资料'); 

--大版表
DROP TABLE IF EXISTS t_big_format;
CREATE TABLE t_big_format(
       'bgid' varchar(16) PRIMARY KEY COMMENT '大版id',
       'stid' varchar(16) COMMENT '邮票id',
       'bgsize' varchar(16) COMMENT '大版尺寸',
       'bgnumber' smallint(16) COMMENT '大版枚数');

--小版表
DROP TABLE IF EXISTS t_small_format;
CREATE TABLE t_small_format(
       'slid' varchar(16) PRIMARY KEY COMMENT '小版id',
       'stid' varchar(16) COMMENT '邮票id',
       'slsize' varchar(16) COMMENT '小版尺寸',
       'slnumber' smallint(16) COMMENT '小版枚数',

--赠送版表
DROP TABLE IF EXISTS t_zengsong;
CREATE TABLE t_zengsong(
       'zengid' varchar(16) PRIMARY KEY COMMENT '赠送版id',
       'stid' varchar(16) COMMENT '邮票id',
       'zeng_size' varchar(16) COMMENT '赠送版尺寸',
       'zeng_number' smallint(16) COMMENT '赠送版枚数');

--小本票表
DROP TABLE IF EXISTS t_xiaoben;
CREATE TABLE t_xiaoben(
       'xiaobenid' varchar(16) PRIMARY KEY COMMENT '小本票id',
       'stid' varchar(16) COMMENT '邮票id',
       'number' varchar(16) COMMENT '小本票编号',
       'face_value' varchar(16) COMMENT '小本票面值');

--小型张表
DROP TABLE IF EXISTS t_small_sheet;
CREATE TABLE t_small_sheet(
       'id' varchar(16) PRIMARY KEY COMMENT '小型张id',
       'stid' varchar(16) COMMENT '邮票id',
       'face_value' varchar(16) COMMENT '小型张面值',
       'size' varchar(16) COMMENT '小型张尺寸',
       'image' varchar(256) COMMENT '小型张邮票主图',
       'chikong' varchar(16) COMMENT '小型张齿孔',
       'draw' varchar(16) COMMENT '小型张绘画');
