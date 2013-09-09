create database student;
use student;

-- 学生信息表创建
drop table if exists stu_info;
drop table if exists stu_score;
drop table if exists stu_score_avg;
create table stu_info
(
	id int primary key auto_increment,
	username varchar(100),
	gender char(1)
);

-- 学生成绩表创建
create table stu_score
(
	username varchar(100),
	Chinese int,
	English int,
	Information int,
	`Electromagnetic Fields` int
);

-- 平均成绩表
create table stu_score_avg
(
	id int primary key auto_increment,
	username varchar(100),
	score_avg float
);

-- 学生信息表 数据插入
insert into stu_info values (null, 'Yan', 'M');
insert into stu_info values (null, 'Ready', 'F');
insert into stu_info values (null, 'Prince', 'M');
insert into stu_info values (null, 'Quan', 'M');
insert into stu_info values (null, 'Hua', 'F');
insert into stu_info values (null, 'Chang', 'M');
insert into stu_info values (null, 'Qiang', 'M');
insert into stu_info values (null, 'Xuan', 'F');
insert into stu_info values (null, 'Xin', 'F');
insert into stu_info values (null, 'Min', 'M');

-- 学生成绩表 数据插入
insert into stu_score values ('Yan', 85, 85, 75, 70);
insert into stu_score values ('Ready', 75, 90, 45, 75);
insert into stu_score values ('Prince', 60, 80, 30, 65);
insert into stu_score values ('Quan', 55, 45, 60, 60);
insert into stu_score values ('Hua', 78, 78, 80, 55);
insert into stu_score values ('Chang', 90, 95, 85, 80);
insert into stu_score values ('Qiang', 95, 75, 65, 78);
insert into stu_score values ('Xuan', 65, 70, 63, 85);
insert into stu_score values ('Xin', 80, 65, 62, 90);
insert into stu_score values ('Min', 45, 60, 88, 100);