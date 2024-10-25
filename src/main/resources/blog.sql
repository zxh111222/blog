-- 创建 blog 表
create table blog
(
    id      int auto_increment comment '自动递增ID主键',
    title   varchar(100) not null comment '博客标题',
    content longtext     not null comment '博客内容',
    constraint blog_pk
        primary key (id)
) comment '博客';

-- 插入演示数据
insert into blog(title, content)
values ("我的第一篇博客", "第一篇博客的正文内容，待补充"),
       ("我的第二篇博客", "第二篇博客的正文内容，待补充"),
       ("我的第三篇博客", "第三篇博客的正文内容，待补充"),
       ("我的第四篇博客", "第四篇博客的正文内容，待补充"),
       ("我的第五篇博客", "第五篇博客的正文内容，待补充"),
       ("我的第六篇博客", "第六篇博客的正文内容，待补充"),
       ("我的第七篇博客", "第七篇博客的正文内容，待补充"),
       ("我的第八篇博客", "第八篇博客的正文内容，待补充")
;

-- 添加博客类型 type
alter table blog
    add type varchar(11) not null comment '博客类型';

-- 增加`博客封面`字段
alter table blog
    add cover varchar(256) null comment '博客封面图';



