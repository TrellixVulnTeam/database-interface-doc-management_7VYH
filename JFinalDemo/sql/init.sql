--user
drop table if exists user;
create table user (username, password);
insert into user values('admin', '123456');

--app_system
drop table if exists app_system;
CREATE TABLE app_system (id TEXT, system_name TEXT, system_desc TEXT);
insert into app_system values('1','CDMS','�������ݹ���ϵͳ');
insert into app_system values('2','CAPSTONE','Capstoneϵͳ');
insert into app_system values('3','HIPO','Hipoϵͳ');
insert into app_system values('4','HUNDSUN','����');
insert into app_system values('5','IDW','IDW');