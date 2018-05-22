--使用数据库hrm_db
use hrm_db;
--创建表job_inf
drop table if exists job_inf;
create table job_inf(
    id int(11) not null auto_increment,
    name varchar(50) not null,
    remark varchar(300) default null,
    primary key (id)
)ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
insert into job_inf(id,name,remark) values
(1,'职员','职员'),
(2,'Java 开发工程师','Java 开发工程师'),
(3,'Java 中级开发工程师','Java 中级开发工程师'),
(4,'Java 高级开发工程师','Java 高级开发工程师'),
(5,'系统管理员','系统管理员'),
(6,'架构师','架构师'),
(7,'主管','主管'),
(8,'经理','经理'),
(9,'总经理','总经理');