
-- create database shangyou_v1;
-- ALTER DATABASE shangyou_v1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- create user 'shuoen'@'%' identified by 'TewtQ81_%&$';
-- grant all on shangyou_v1.* to 'shuoen'@'%';

use shangyou_v1;

--用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
       `uid` char(8) PRIMARY KEY COMMENT '用户id',
       `mobile_number` varchar(32) NOT NULL COMMENT '手机号码',
       `nickname` varchar(32) COMMENT '昵称',
       `gender` tinyint(1) NOT NULL DEFAULT 0 COMMENT '性别,1男,2女,3未知',
       `avatar` varchar(256) COMMENT '用户头像',
       `created_at` bigint(20) NOT NULL COMMENT '创建时间',
       `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='用户表';
CREATE UNIQUE INDEX i_user_mobile ON t_user(`mobile_number`);

--收藏夹表
DROP TABLE IF EXISTS t_favorite;
CREATE TABLE t_favorite(
       `faid` char(8) PRIMARY KEY,
       `uid` char(8) NOT NULL COMMENT '用户id',
       `stid` char(8) NOT NULL COMMENT '邮票id',
       `created_at` bigint(20) NOT NULL COMMENT '创建时间',
       `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间',
       `status` tinyint(1) NOT NULL COMMENT '1收藏,2未收藏'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='收藏夹表';
CREATE UNIQUE INDEX i_favorite_uid_stid ON t_favorite(`uid`, `stid`);

--邮票详情表
DROP TABLE IF EXISTS t_stamp;
CREATE TABLE t_stamp(
       `stid` char(8) PRIMARY KEY COMMENT '邮票id',
       `type` tinyint COMMENT '类型',
       `year` smallint COMMENT '发布年份',
       `name` varchar(128) COMMENT '邮票名称',
       `countryid` tinyint COMMENT '国家, 1-中国,',
       `number` varchar(32) NOT NULL COMMENT '邮票编号',
       `issued_date` char(32) COMMENT '邮票发布日期',
       `joint_issue` varchar(64) COMMENT '联合发行',
       `size` varchar(128) COMMENT '邮票尺寸',
       `chikong` varchar(128) COMMENT '邮票齿孔',
       `format` varchar(64) COMMENT '邮票版式',
       `fanwei` varchar(64) COMMENT '防伪技术',
       `designer` varchar(64) COMMENT '邮票设计',
       `editor` varchar(64) COMMENT '责任编辑',
       `carve` varchar(64) COMMENT '雕刻者',
       `side_design` varchar(64) COMMENT '边饰设计',
       `draw` varchar(64) COMMENT '绘画',
       `shoot` varchar(64) COMMENT '摄影',
       `printing_house` varchar(128) COMMENT '印刷厂',
       `background` text COMMENT '背景资料',
       `picture` TEXT COMMENT '邮票图片'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='邮票详情表';
CREATE INDEX i_stamp_countryid ON t_stamp(`countryid`);
CREATE INDEX i_stamp_year ON t_stamp(`year`);
CREATE INDEX i_stamp_type ON t_stamp(`type`);

--子邮票
DROP TABLE IF EXISTS t_sub_stamp;
CREATE TABLE t_sub_stamp(
       `sstid` char(8) PRIMARY KEY COMMENT '子邮票id',
       `stid` char(8) COMMENT '邮票id',
       `order` tinyint COMMENT '序号',       
       `title` varchar(128) NOT NULL COMMENT '邮票标题',
       `picture` varchar(256) COMMENT '邮票图片',
       `face_value` varchar(64) COMMENT '邮票面值',
       `issued_number` varchar(64) COMMENT '发行量'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='子邮票';
CREATE INDEX i_sub_stamp_stid ON t_sub_stamp(`stid`);

--大版表
DROP TABLE IF EXISTS t_big_format;
CREATE TABLE t_big_format(
       `bgid` char(8) PRIMARY KEY COMMENT '大版id',
       `stid` char(8) COMMENT '邮票id',
       `bgsize` varchar(64) COMMENT '大版尺寸',
       `bgnumber` varchar(128) COMMENT '大版枚数',
       `picture` varchar(256) COMMENT '邮票图片'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='大版表';
CREATE INDEX i_big_format_stid ON t_big_format(`stid`);

--小版表
DROP TABLE IF EXISTS t_small_format;
CREATE TABLE t_small_format(
       `slfid` char(8) PRIMARY KEY COMMENT '小版id',
       `stid` char(8) COMMENT '邮票id',
       `slsize` varchar(64) COMMENT '小版尺寸',
       `slnumber` varchar(64) COMMENT '小版枚数',
       `issued_number` varchar(64) COMMENT '发行量',
       `picture` varchar(256) COMMENT '邮票图片'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='小版表';
CREATE INDEX i_small_format_stid ON t_small_format(`stid`);

--赠送版表
DROP TABLE IF EXISTS t_zengsong;
CREATE TABLE t_zengsong(
       `zengid` char(8) PRIMARY KEY COMMENT '赠送版id',
       `stid` char(8) COMMENT '邮票id',
       `zeng_size` varchar(64) COMMENT '赠送版尺寸',
       `zeng_number` varchar(64) COMMENT '赠送版枚数',
       `picture` varchar(256) COMMENT '邮票图片'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='赠送版表';
CREATE INDEX i_zengsong_stid ON t_zengsong(`stid`);

--小本票表
DROP TABLE IF EXISTS t_xiaoben;
CREATE TABLE t_xiaoben(
       `xiaobenid` char(8) PRIMARY KEY COMMENT '小本票id',
       `stid` char(8) COMMENT '邮票id',
       `number` varchar(32) COMMENT '小本票编号',
       `size` varchar(64) COMMENT '尺寸',
       `issued_number` varchar(64) COMMENT '发行量',
       `face_value` varchar(8) COMMENT '小本票面值',
       `designer` varchar(64) COMMENT '邮票设计',
       `editor` varchar(64) COMMENT '责任编辑',
       `printing_house` varchar(32) COMMENT '印刷厂',
       `picture` varchar(256) COMMENT '邮票图片'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='小本票表';
CREATE INDEX i_xiaoben_stid ON t_xiaoben(`stid`);

--小型张表
DROP TABLE IF EXISTS t_small_sheet;
CREATE TABLE t_small_sheet(
       `slsid` char(8) PRIMARY KEY COMMENT '小型张id',
       `stid` char(8) COMMENT '邮票id',
       `face_value` varchar(64) COMMENT '小型张面值',
       `size` varchar(64) COMMENT '小型张尺寸',
       `main_picture` varchar(256) COMMENT '小型张邮票主图',
       `chikong` varchar(128) COMMENT '小型张齿孔',
       `designer`  varchar(64) COMMENT '邮票设计',
       `draw` varchar(32) COMMENT '小型张绘画',
       `picture` varchar(256) COMMENT '邮票图片'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='小型张表';
CREATE INDEX i_small_sheet_stid ON t_small_sheet(`stid`);

--小全张
DROP TABLE IF EXISTS t_mini_sheet;
CREATE TABLE t_mini_sheet(
       `minid` char(8) PRIMARY KEY COMMENT '小全张id',
       `stid` char(8) COMMENT '邮票id',
       `face_value` varchar(8) COMMENT '面值',
       `size` varchar(64) COMMENT '尺寸',
       `picture` varchar(256) COMMENT '邮票图片'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='小全张表';
CREATE INDEX i_mini_sheet_stid ON t_mini_sheet(`stid`);

--四连体小型张
DROP TABLE IF EXISTS t_four_sheet;
CREATE TABLE t_four_sheet(
       `fsid` char(8) PRIMARY KEY COMMENT '四连体小型张id',
       `stid` char(8) COMMENT '邮票id',
       `face_value` varchar(8) COMMENT '面值',
       `size` varchar(64) COMMENT '尺寸',
       `picture` varchar(256) COMMENT '邮票图片'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='四连体小型张';
CREATE INDEX i_four_sheet_stid ON t_four_sheet(`stid`);

--双联小型张
DROP TABLE IF EXISTS t_double_sheet;
CREATE TABLE t_double_sheet(
       `dsid` char(8) PRIMARY KEY COMMENT '双联小型张id',
       `stid` char(8) COMMENT '邮票id',
       `size` varchar(64) COMMENT '尺寸',
       `picture` varchar(256) COMMENT '邮票图片'
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='双联小型张表';
CREATE INDEX i_double_sheet_stid ON t_double_sheet(`stid`);
