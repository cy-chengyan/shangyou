
# 需求

## 用户

1. 用户可以使用手机号登录(输入手机号，验证码）

   用户通过输入手机号获取6位数验证码，进行验证登录。

2. 用户可以收藏邮票

   每张邮票实现一个收藏按钮(也可取消收藏)，用户可以在自己的收藏夹中进行查看。

## 核心功能

* 邮票列表(分类)
  * 版式
    * 平版
    * 胶雕套印
    * 影写
    * 胶印
    * 柯式平板印刷
  * 年份
    * 1949——2019

* 邮票详情
  * 参照其它app的邮票详情
    * 基本属性
      * 编号
      * 发行日期
      * 尺寸
      * 齿孔
      * 版式
      * 防伪标志
      * 邮票设计
      * 雕刻
      * 责任编辑
      * 印刷厂
      * 背景资料(对邮票的文字介绍)
    * 每枚邮票信息(1:n)
      * 标题
      * 序号
      * 图片
      * 面值
    * 大版(1:n)
      * 尺寸
      * 枚数
    * 小版(1:n)
      * 尺寸
      * 枚数
    * 赠送版(1:n)
      * 尺寸
      * 枚数
    * 小本票(1:n)
      * 编号
      * 面值
    * 小型张(1:n)
      * 面值
      * 尺寸
      * 外形尺寸
      * 邮票主图
      * 齿孔
      * 绘画
