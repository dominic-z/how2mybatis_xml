drop database if exists how2mybatis;
create schema how2mybatis default character set 'utf8' collate 'utf8_bin';

use how2mybatis;

drop table if exists blog;
create table blog
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    content    varchar(45),
    authorId   int,
    authorName varchar(45),
    coAuthorId int comment '第二作者的id'
) comment '博客表';
insert into blog (content, authorId, authorName, coAuthorId)
values ('content11', 1, 'zhang', null),
       ('content12', 1, 'zhang', 2),
       ('content2', 2, 'wang', null),
       ('content3', 3, 'li', 1);

drop table if exists author;
create table author
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    name    varchar(45),
    age     float,
    sexType int comment '0为男，1为女',
    sex     enum ('MALE','FEMALE')
) comment '作者表';
insert into author (name, age, sexType, sex)
values ('zhang', 18.5, 0, 'MALE'),
       ('wang', 21.2, 1, 'FEMALE'),
       ('li', 21.7, 1, 'FEMALE');


# delete from how2mybatis.blog where id>=5;
# ALTER TABLE how2mybatis.blog auto_increment=5;


