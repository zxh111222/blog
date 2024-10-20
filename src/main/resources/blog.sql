-- 创建 blog 表
create table blog
(
    id      int auto_increment comment '自动递增ID主键',
    title   varchar(100) not null comment '博客标题',
    content longtext     not null comment '博客内容',
    constraint blog_pk
        primary key (id)
) comment '博客';