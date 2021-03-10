select user(), database();

-- Main화면 sql
-- 1. 빠른회원검색
select userno, username, tel, phone 
  from user
 where userno = 12001;
 
select userno, username, tel, phone
  from user
 where username = '김동수';
 
select userno, username, tel, phone
  from user
 where tel = '051-111-2222';
 
select userno, username, tel, phone
  from user
 where phone = '010-7777-7777';
 
select userno, username, tel, phone
  from user
 where account = 'jinjin77@abc.com';

-- 2. 빠른도서검색
select bookno, booktitle,
	case
	 when isRented = 1 then '대여가능'
	 when isRented = 0 then '대여불가능'
	end as '대출여부'
  from book
 where bookno = '40001-1';

select distinct left(bookno, 5) as bookno, booktitle,
	case
	 when (select sum(isRented) from book where booktitle = 'The elements of statistical learning') > 0 then '대여가능'
	 else '대여가능'
	end as '대출여부'
  from book
 where booktitle = 'The elements of statistical learning';

-- 아래도 됨...
select distinct left(bookno, 5) as bookno, booktitle,
	case
	 when sum(isRented) = 0 then '대여불가능'
	 else '대여가능'
	end as '대출여부'
  from book
 where booktitle = 'The elements of statistical learning'
 group by left(bookno, 5);

select distinct left(bookno, 5), booktitle,
	case
	 when sum(isRented) = 0 then '대여불가능'
	 else '대여가능'
	end as '대출여부',
	count(*) as '권수'
  from book
 where bookcategory = 3
 group by left(bookno, 5);

select distinct left(bookno, 5), booktitle,
	case
	 when sum(isRented) = 0 then '대여불가능'
	 else '대여가능'
	end as '대출여부'
  from book
 where booktitle like '%ing%'
 group by left(bookno, 5);

-- distinct도 되고 group by 도 됨!
select left(bookno, 5), booktitle
  from book
 group by left(bookno, 5); 

select left(r.bookno, 5), b.booktitle, r.delaydate, r.rentaldate
  from (rentalstatus r left join book b on r.bookno = b.bookno) left join user u on r.userno = u.userno 
 where u.userno = 12001;
  
-- 대출화면
select userno, username, tel, phone
  from user
 where userno = 12001;

select userno, username, userbirth, account, tel, phone, address 
  from user
 where userno = 12003;

select *
  from user
 where username = '김동수';

select userno, username, userbirth, account, tel, phone, address 
  from user
 where account like '%ji%';

select *
  from user
 where userbirth = 19820707;

select bookno, booktitle,
	case 
	 when isRented = 1 then '대여가능'
	 else '대여불가능'
	end as '대출여부'
  from book
 where bookno = '40001-3';

select b.bookno, b.booktitle, c.categoryname,
	case 
	 when isRented = 0 then '대여불가능'
	 else '대여가능'
	end as '대출여부'
  from book b left join bookcategory c on b.bookcategory = c.bookcategory
 where (b.booktitle like '%ing%') and (b.isRented = 1);

-- 대여하기 
-- transaction 더 생각해보기...
-- 트랜잭션 시작


insert into rentalstatus
values (null, '40005-3', 12007, curdate(), null, default(delaydate));


update book
   set isRented = 0
 where bookno = '40005-2';

-- 여기까지 트랜잭션...(끝)

select * from rentalstatus;
select * from book;
select * from book where bookno = '40005-3';


-- 반납하기(트랜잭션...) -> 검색할 때 (연체일을 update 해야함)
-- 트랜잭션 시작
select r.userno, u.username, u.tel, u.phone 
  from rentalstatus r left join user u on r.userno = u.userno left join book b on r.bookno = b.bookno 
 where u.userno = 12007;

update rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno 
   set r.delaydate = curdate() - (r.rentaldate + b.rentalrange)
 where u.userno = 12007;
-- 트랜잭션 끝
-- 반납하기 ->도서목록가져오기

select b.bookno, b.booktitle, r.delaydate, r.rentaldate 
  from rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno 
 where u.userno = 12001;

-- 반납버튼 누르기 (트랜잭션 시작)
update rentalstatus
   set userreturndate = curdate()
 where bookno = '40001-1';

update book
   set isRented = 1
 where bookno = '40001-1';
-- 트랜잭션 끝
select * from rentalstatus;
select * from book;

-- 대여완료된 책들만 뽑아보기
select * from rentalstatus
where userreturndate is not null;

-- 연체된 책들만 뽑아보기
select b.bookno, b.booktitle, r.delaydate, r.rentaldate
  from rentalstatus r left join book b on r.bookno = b.bookno 
 where (curdate() - (r.rentaldate + b.rentalrange) > 0) and (userreturndate is null);
