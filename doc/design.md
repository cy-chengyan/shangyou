
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


# 业务接口

## 用户相关

### login

1. 输入手机号, 验证码
2. 输出成功或失败, 用户的登录信息

### logout

1. 输出成功或失败

### update

1. 可以更新nickname, gender

## 邮票相关

### 列表

* 输入
1. 以年份查询
2. 从邮票类型查询
3. 排序规则(时间从新到旧, 时间从旧到新)
4. offset, size
* 输出
邮票基本信息列表

### 详情

输入邮票id
输出邮票的所有信息

