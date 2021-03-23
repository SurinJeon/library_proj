select user(), database();

select mngaccount, passwd from manager;
select mngaccount, passwd from manager where mngaccount = 'manager_1@lib.go.kr';
insert into manager values ('manager_4@lib.go.kr', '11223344');
update manager set mngaccount = 'aaa', passwd = '@aaa' where mngaccount = 'manager_4@lib.go.kr';
delete from manager where mngaccount = 'manager_4lib.go.kr';

select bookcategory, categoryname from bookcategory;
select bookcategory, categoryname from bookcategory where bookcategory = 4;
insert into bookcategory values(5, '문학');
update bookcategory set bookcategory = 5, categoryname = '철학' where bookcategory = 5;
delete from bookcategory where bookcategory = 5;

select bookno, booktitle, isRented, bookcategory, count, rentalrange from book;
select bookno, booktitle, isRented, bookcategory, count, rentalrange from book where ;

delete from book where bookno = '40008-6';
desc user;

select * from user;
delete from user where userno = 12012;
select userno, username, userbirth, account, tel, phone, address from user where account like '%abc%';

insert into user values (12012, '전수린', 19950901, 'surin0901@abc', '053-521-8802', '010-6246-9273', '대구');

update user set userno = 12013, username = '짱정아', userbirth = 19930101, account = 'zzangstella@abc.com', tel = '053-555-5555', phone = '010-1111-1111', address = '대구' where userno = 12012; 

create view vw_user
as 
select userno, username, userbirth, account, tel, phone, address from user;

drop view vw_user;

select userno, username, userbirth, account, tel, phone, address from vw_user;

select * from rentalstatus;


select userno, username, userbirth, rentaldate from vw_user where userbirth = 19070405;

select * from vw_all;
select * from vw_user where userbirth = 19070405;
select * from rentalstatus r left join user u on r.userno = u.userno;

select userno, username, userbirth, account, tel, phone, address from vw_user;

drop view vw_all;

create view vw_all
as
select r.rentalno, b.bookno, u.userno, r.rentaldate, r.userreturndate, r.delaydate, u.username,u.userbirth,u.account,u.tel,u.phone,u.address,b.booktitle,b.isRented,b.bookcategory, b2.categoryname,b.count,b.rentalrange 
from rentalstatus r left join user u on r.userno = u.userno left join book b on r.bookno = b.bookno left join bookcategory b2 on b.bookcategory = b2.bookcategory;



select * from vw_all;
select rentalno,bookno,userno,rentaldate,userreturndate,delaydate,username,userbirth,account,tel,phone,address,booktitle,isRented,bookcategory,count,rentalrange
from vw_all where userno = 12001;

select rentalno, bookno, userno, rentaldate, userreturndate, delaydate from rentalstatus;

select * from rentalstatus;

select rentalno, bookno, userno, rentaldate, userreturndate, delaydate from rentalstatus where userno = 12002;

insert into rentalstatus values (5, '40006-1', 12005, 20210309, null, default(delaydate));

delete from rentalstatus where rentalno = 5;

update rentalstatus set rentalno = 5, bookno = '40006-1', userno = 12006, rentaldate = , userreturndate = ?, delaydate = ? where rentalno = ?;


select bookno, booktitle, rentalno, delaydate, rentaldate from vw_all where userno = 12001;

select * from vw_all;

select * from manager;

select userno, username, tel, phone from user;

select * from manager;
desc manager;

insert into manager values('aaa', 'aaa');
select * from user where username like '%김동%';

select * from rentalstatus;

select rentalno, bookno, booktitle, rentaldate, userreturndate, delaydate from vw_all;

create view vw_book
as
select b.bookno, b.booktitle, b.isRented, c.bookcategory, c.categoryname, b.count, b.rentalrange from book b left join bookcategory c on b.bookcategory = c.bookcategory; 

select * from vw_book;

select bookno, booktitle, isRented, bookcategory, categoryname, count, rentalrange
 from vw_book
 where bookno = '40002-1' and isRented = 1;


select bookno, booktitle, isRented, bookcategory, count, rentalrange
 from vw_all where userno = 12001;


select rentalno, bookno, userno, rentaldate, userreturndate, delaydate, username, userbirth, account, tel, phone, address, booktitle, isRented, bookcategory, count, rentalrange from vw_all;


update rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno
 set r.delaydate = curdate() - (r.rentaldate + b.rentalrange)
 where u.userno = 12002;
 
select * from rentalstatus;

update rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno
 set r.delaydate = curdate() - (r.rentaldate + b.rentalrange)
 where r.userreturndate is null;

update rentalstatus r left join book b on r.bookno = b.bookno
   set delaydate = userreturndate - (rentaldate + b.rentalrange)
 where b.bookno = '40001-1';

update rentalstatus r left join book b on r.bookno = b.bookno
   set delaydate = null;
  
  
select * from rentalstatus;
select * from book;
