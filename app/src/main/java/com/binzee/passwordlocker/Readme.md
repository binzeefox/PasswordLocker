# Readme.md

## 页面代码

1. 闪屏页
2. 锁定页
3. 列表页
4. 详情页
5. 设置页

## 页面内容

1. 准备数据库、申请权限
2. 指纹解锁
3. 展示内容列表，以及基本信息
4. 展示详情、新增条目、修改条目
5. 清除数据

## 数据结构

- NotiItem
    - id: Long  主键
    - username: String  用户名
    - password: String  密码
    - loginFor: String  用于登录
    - lastCheck: Long   上次查看时间戳
    - createTime: Long  创建时间戳
    - describe: Long    描述