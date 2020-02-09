-- 创建 coupon_template 数据表
create table if not exists `imooc_coupon_data`.`coupon_template`
(
    id int(11) not null auto_increment comment '自增主键',
    available boolean not null default false comment '是否可用状态; true:可用,false:不可用',
    expired boolean not null default false comment '是否过期; true:是, false:否',
    name varchar(64) not null default '' comment '优惠券名称',
    logo varchar(256) not null default '' comment '优惠券 logo',
    intro varchar(256) not null default '' comment '优惠券描述',
    category varchar(64) not null default '' comment '优惠券分类',
    product_line int(11) not null default 0 comment '产品线',
    coupon_count int(11) not null default 0 comment '总数',
    create_time datetime not null default '0000-01-01 00:00:00' comment '创建时间',
    user_id bigint(20) not null default 0 comment '创建用户',
    template_key varchar(128) not null default '' comment '优惠券模板的编码',
    target int(11) not null default 0 comment '目标用户',
    rule varchar(1024) not null default '' comment '优惠券规则: TemplateRule 的JSON',
    primary key (`id`),
    key `idx_category` (`category`),
    key `idx_user_id` (`user_id`),
    unique key `unk_name` (`name`)
) ENGINE = InnoDB auto_increment= 10 default charset=utf8mb4 comment '优惠券模板表';

-- 清空表数据
truncate coupon_template;