--使用数据库hrm_db
use hrm_db;
--创建user_inf
drop table if exists user_inf;
create table user_inf(
    id int(11) not null auto_increment,
    login_name varchar(20) not null,
    password varchar(15) not null,
    status int(11) not null default '1',
    create_date timestamp not null default current_timestamp,
    username varchar(20) default null,
    primary key(id)
)ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
insert into user_inf(id, login_name, password, status, create_date, username) values
(1,'admin','123456',2,'2018-05-20 09:33:45','超级管理员');