--使用数据库hrm_db
use hrm_db;
--创建notice_inf
drop table if exists notice_inf;
create table notice_inf(
    id int(11) not null auto_increment,
    title varchar(50) not null,
    content text not null,
    create_date timestamp not null default current_timestamp,
    user_id int(11) default null,
    primary key(id),
    constraint fk_notice_user foreign key (user_id) references user_inf(id)
)ENGINE=INNODB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;