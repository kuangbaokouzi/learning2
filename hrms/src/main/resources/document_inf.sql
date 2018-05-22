--使用数据库hrm_db
use hrm_db;
--创建document_inf
drop table if exists document_inf;
create table document_inf(
    id int(11) not null auto_increment,
    title varchar(50) not null,
    filename varchar(300) not null,
    remark varchar(300) default null,
    create_date timestamp not null default current_timestamp,
    user_id int(11) default null,
    primary key(id),
    constraint fk_document_user foreign key (user_id) references user_inf(id)
)ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;