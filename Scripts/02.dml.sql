select user(), database();

show tables;

-- 순서 유의
desc booksort;
                    
insert into booksort
values (1, '수학'), (2, '전자'), (3, '컴퓨터'), (4, '통계');

desc book;

insert into book
values (40001, 'The elements of statistical learning', null, 1, 5),
	   (40002, 'Computer vision : a modern approach', null, 3, 5), 
	   (40003, 'MATLAB for engineers', null, 2, 5), 
	   (40004, 'CUDA by example', null, 1, 5), 
	   (40005, 'An introduction to 3D computer vision', null, 3, 5), 
	   (40006, 'Numerical methods', null, 1, 5), 
	   (40007, 'Image-based modeling', null, 3, 5), 
	   (40008, 'Machine learning', null, 3, 5), 
	   (40009, 'Probabilistic robotics', null, 4, 5), 
	   (40010, 'Pattern recognition and machine learning', null, 3, 5); 

select * from book;

desc user;
insert into user
values (12001, '홍길동', 19700405, '042-421-1739', '010-9741-5821', '대전'),
	   (12002, '김연수', 19850301, '064-446-8695', '010-4568-5581', '제주'),
	   (12003, '김지원', 19860708, '053-548-7898', '010-9111-5556', '대구'),
	   (12004, '문희원', 19800108, '053-221-1231', '010-7777-7777', '울산'),
	   (12005, '유일한', 19790205, '051-111-2222', '010-4566-8886', '부산'),
	   (12006, '김동수', 19811123, '02-668-8887', '010-1231-1211', '서울'),
	   (12007, '배진태', 19820707, '044-500-7333', '010-7877-7777', '세종'),
	   (12008, '류은수', 19830301, '062-233-1122', '010-7444-1474', '광주'),
	   (12009, '김동철', 19870426, '061-887-4454', '010-8525-1235', '순천'),
	   (12010, '최홍석', 19900405, '054-555-7897', '010-3214-6547', '포항'),
	   (12011, '김동현', 19830108, '043-529-8457', '010-9566-4228', '제천');
	  
select * from user;
	  
desc rentalstatus;

alter table rentalstatus modify column rentalno int(5) auto_increment not null;




