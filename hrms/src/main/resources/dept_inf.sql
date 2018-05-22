--使用数据库hrm_db
use hrm_db;
--创建表dept_inf
drop table if exists dept_inf;
create table dept_inf(
    id int(11) not null auto_increment,
    name varchar(50) not null,
    remark varchar(300) default null,
    primary key (id)
)ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
insert into dept_inf(id,name,remark) values
(1,'Tecnology','Tecnology'),
(2,'Operations','Operations'),
(3,'Finance','Finance'),
(4,'Personnel','Personnel'),
(5,'General Office','General Office'),
(6,'Market','Market'),
(7,'Education','Education');