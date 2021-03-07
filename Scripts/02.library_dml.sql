-- insert 순서
-- 1. bookcategory
-- 2. book
-- 3. user
-- 4. manager
-- 5. rentalstatus

desc bookcategory;

insert into bookcategory
values (1, '수학'), (2, '전자'), (3, '컴퓨터'), (4, '통계');

select * from bookcategory;

--
desc book;
delete from book;
insert into book 
values ('400011', 'The elements of statistical learning', 0 , 1, 1, 3),
	   ('400012', 'The elements of statistical learning', 0, 1, 1, 3),
	   ('400013', 'The elements of statistical learning', 0, 1, 1, 3),
	   ('400014', 'The elements of statistical learning', 0, 1, 1, 3),
	   ('400015', 'The elements of statistical learning', 0, 1, 1, 3),
	   ('400021', 'Computer vision : a modern approach', 0, 3, 1, 5),
	   ('400022', 'Computer vision : a modern approach', 0, 3, 1, 5),
	   ('400023', 'Computer vision : a modern approach', 0, 3, 1, 5),
	   ('400024', 'Computer vision : a modern approach', 0, 3, 1, 5),
	   ('400025', 'Computer vision : a modern approach', 0, 3, 1, 5),
	   ('400031', 'MATLAB for engineers', 0, 2, 1, 3),
	   ('400032', 'MATLAB for engineers', 0, 2, 1, 3),
	   ('400033', 'MATLAB for engineers', 0, 2, 1, 3),
	   ('400034', 'MATLAB for engineers', 0, 2, 1, 3),
	   ('400035', 'MATLAB for engineers', 0, 2, 1, 3);
	  
select * from book;

delete from book where left(bookno, 5) = '40004';

insert into book
values ('400041', 'CUDA by example', 0, 1, 1, 3),
	   ('400042', 'CUDA by example', 0, 1, 1, 3),
	   ('400043', 'CUDA by example', 0, 1, 1, 3),
	   ('400044', 'CUDA by example', 0, 1, 1, 3),
	   ('400045', 'CUDA by example', 0, 1, 1, 3);
	  
	  
