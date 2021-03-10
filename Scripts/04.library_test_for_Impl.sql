select user(), database();

select mngaccount, passwd from manager;
select mngaccount, passwd from manager where mngaccount = 'manager_1@lib.go.kr';
insert into manager values ('manager_4@lib.go.kr', '11223344');
update manager set mngaccount = 'aaa', passwd = 'aaa' where mngaccount = 'manager_4@lib.go.kr';
delete from manager where mngaccount = 'aaa';

select bookcategory, categoryname from bookcategory;
select bookcategory, categoryname from bookcategory where bookcategory = 4;
insert into bookcategory values(5, '문학');
update bookcategory set bookcategory = 5, categoryname = '철학' where bookcategory = 5;
delete from bookcategory where bookcategory = 5;

select bookno, booktitle, isRented, bookcategory, count, rentalrange from book;
select bookno, booktitle, isRented, bookcategory, count, rentalrange from book where ;

delete from book where bookno = '40008-6';
desc user;
select userno, username, userbirth, account, tel, phone, address from user;