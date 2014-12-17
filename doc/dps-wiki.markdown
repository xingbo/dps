# 用例

标签（空格分隔）： dps

---

## 数据库设计

### 表详解

#### USER
| 字段名 | 类型 | 长度 | 描述 |
| -----  | ----- | ----- |  ----- | 
| id | INT UNSIGNED |  | 用户编号，主键 | 
| loginname | VARCHAR | 64 | 登录名，可以是手机号，或邮箱 | 
| nickname  | VARCHAR | 64 | 用户注册成功后给自己起的昵称 | 
| avatar  | VARCHAR | 128 | 用户注册成功后给自己选的头像 | 
| password | VARCHAR | 128 | 用户密码，加密保存 | 
| email | VARCHAR | 64 | 邮箱 | 
| phone | VARCHAR | 24 | 手机号 | 
| type | TINYINT |  | 用户类别，例如1：普通用户；2：教练| 
| statue | TINYINT |  | 用户状态，例如1：刚注册未激活；2：正常激活；0：被系统管理员锁定；-1：被删除| 
| createdate | TIMESTAMP |  | 用户申请注册的时间 | 
| updatedate | TIMESTAMP |  | 用户基本信息上次的更新时间 | 
| activecode | VARCHAR | 64 | 激活码，注册后将其发邮件/短信到用户注册时提供的邮箱/手机，用来验证| 
| isphonevalid | TINYINT | 1 | 标志用户的手机是否通过验证，例如0是未通过验证；1是通过验证 | 
| isemailvalid | TINYINT | 1 | 标志用户的邮箱是否通过验证，含义如手机验证标志 | 

#### CONSUMER_USER
| 字段名 | 类型 | 长度 | 描述 |
| -----  | ----- | ----- |  ----- | 
| userid | INT UNSIGNED |  | 外键，USER表中ID |
| truename | VARCHAR | 64 | 用户的真实姓名 |
| gender | TINYINT | 1 | 性别，例如1：男，2：女，3：保密 |
| birth | DATE |  | 用户的出生日期 |
| country | VARCHAR | 64 | 用户地址信息，国家 |
| provnice | VARCHAR | 64 | 用户地址信息，省份 |
| city | VARCHAR | 64 | 用户地址信息，城市 |
| district | VARCHAR | 64 | 用户地址信息，区 |
| street | VARCHAR | 128 | 用户地址信息，街道 |
| apartment | VARCHAR | 128 | 用户地址信息，楼层/门牌  |
| description | VARCHAR | 512 | 个人自我介绍  |

#### COACH_USER
| 字段名 | 类型 | 长度 | 描述 |
| -----  | ----- | ----- |  ----- | 
| userid | INT UNSIGNED |  | 外键，USER表中ID |
| truename | VARCHAR | 64 | 用户的真实姓名 |
| gender | TINYINT | 1 | 性别，例如1：男，2：女，3：保密 |
| birth | DATE |  | 用户的出生日期 |
| country | VARCHAR | 64 | 用户地址信息，国家 |
| provnice | VARCHAR | 64 | 用户地址信息，省份 |
| city | VARCHAR | 64 | 用户地址信息，城市 |
| district | VARCHAR | 64 | 用户地址信息，区 |
| street | VARCHAR | 128 | 用户地址信息，街道 |
| apartment | VARCHAR | 128 | 用户地址信息，楼层/门牌  |
| description | VARCHAR | 512 | 教练自我介绍  |
| idcard | VARCHAR | 128 | 教练的身份证照片  |
| photo  | VARCHAR | 128 | 教练的免冠近照  |
| servicearea | VARCHAR | 255 | 教练的服务区域  |
| status | TINYINT |  | 教练的审核状态，例如0：待审核，1：审核通过，2：审核未通过，4：冻结  |

#### CERTIFICATION
| 字段名 | 类型 | 长度 | 描述 |
| -----  | ----- | ----- |  ----- | 
| userid | INT UNSIGNED |  | 外键，USER表中ID |
| sportid | INT UNSIGNED |  | 外键，SPROT_TYPE表中ID |
| level | VARCHAR | 255 | 教练该项运动最高资质描述 |
| certification | VARCHAR | 255 |  教练该项运动最高资质证明 |

#### SPORT_TYPE
| 字段名 | 类型 | 长度 | 描述 |
| -----  | ----- | ----- |  ----- | 
| id | INT UNSIGNED |  | 主键，运动编号 |
| sportname | VARCHAR | 64 | 运动名称 |
| sportdesc | VARCHAR | 512 | 运动简介 |


## 用例说明

### 前台用户逻辑
#### 1. 用户相关
##### 1.1 用户注册
##### 1.2 用户通过手机激活帐号
##### 1.3 用户通过邮箱激活帐号
##### 1.4 用户更新自己的基本信息
##### 1.5 普通用户更新自己的附加信息
##### 1.6 教练用户更新自己的附加信息

#### 2. 课程相关

#### 3. 支付相关

#### 4. 用户评论、打分

#### 5. 用户间留言


### 后台管理员逻辑
#### 1. 操作用户
##### 1.1 锁定用户
##### 1.2 删除用户


## 未来扩充功能

### 给用户添加积分/等级 数据
可以考虑再新建一张表USER_LEVEL，通过USER表的ID外键关联。里面可以保存用户的评级/积分等。

