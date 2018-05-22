--使用数据库hrm_db
use hrm_db;
--创建employee_inf
drop table if exists employee_inf;
create table employee_inf(
    id int(11) not null auto_increment,
    dept_id int(11) not null,
    job_id int(11) not null,
    name varchar(20) not null,
    card_id varchar(18) not null,
    address varchar(50) not null,
    post_code varchar(50) default null,
    tel varchar(16) default null,
    phone varchar(11) default null,
    qq_num varchar(10) default null,
    email varchar(50) not null,
    sex int(11) not null,
    party varchar(10) default null,
    birthday datetime default null,
    race varchar(100) default null,
    education varchar(10) default null,
    speciality varchar(20) default null,
    hobby varchar(100) default null,
    remark varchar(500) default null,
    create_date timestamp not null default current_timestamp,
    primary key(id),
    constraint fk_emp_dept foreign key (dept_id) references dept_inf(id),
    constraint fk_emp_job foreign key (job_id) references job_inf(id)
)ENGINE=INNODB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
insert into employee_inf(id, dept_id,job_id,name,card_id,address,post_code,tel,phone,qq_num,email,sex,party,birthday,
    race,education,speciality,hobby,remark,create_date) values
(1,1,8,'白浅上神','4328011980','广州天河','510000','020-77777777','13702077777','36750066','baiqian_001@qq.com',0,
    '党员','1980-01-01 00:00:00','苗族','硕士','美声','唱歌','凉凉','2018-03-14 11:33:44'),
(2,2,8,'夜华','1100001988','北京','100000','010-88888888','13802088888','40783509','yehua_001@qq.com',2,
    '党员','1988-02-02 00:00:00','汉族','博士','计算机科学与技术','linux','ubuntu','2018-03-14 11:34:18'),
(3,1,2,'白凤九','4501001996','广西南宁','530000','0771-6666666','13902066666','50063119','baifengjiu_001@qq.com',1,
    '自由党派','1996-03-03 00:00:00','苗族','本科','数学','统计学','东华帝君','2018-03-15 19:22:33');
