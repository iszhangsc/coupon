-- 登录 MySQL 服务器
mysql -h35.200.22.61 -uroot -pxxx123456

-- 创建数据库
create database if not exists imooc_coupon_data;

-- 登录 MySQL 服务器， 并进入到 imooc_coupon_data 数据库中
mysql -h35.200.22.61 -uroot -pxxx123456 -Dimooc_coupon_data