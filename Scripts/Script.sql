select user(), database();

show databases;

use coffee;

show tables;

select * from product;

insert into product
values('A111', '뜨아메'), ('A112', '뜨아메');

delete from product where code = 'A012';
delete from product where code = 'A112';



select code, name
  from product
 where left(code, 1) = 'A';

select distinct name
  from product
 where left(code, 3) = 'A11';

select count(case when left(code, 3) = 'A11' then 1 end) as '집계'
  from product;

 

