select user(), database();

-- 내 스키마
DROP SCHEMA IF EXISTS library;

-- 내 스키마
CREATE SCHEMA library;

-- 회원정보
CREATE TABLE library.user (
	userno    INTEGER(5)  NOT NULL COMMENT '회원번호', -- 회원번호
	username  VARCHAR(50) NULL     COMMENT '회원이름', -- 회원이름
	userbirth DATE        NULL     COMMENT '회원생년월일', -- 회원생년월일
	tel       VARCHAR(13) NULL     COMMENT '전화번호', -- 전화번호
	phone     VARCHAR(13) NULL     COMMENT '휴대전화', -- 휴대전화
	address   VARCHAR(20) NULL     COMMENT '주소' -- 주소
)
COMMENT '회원정보';

-- 회원정보
ALTER TABLE library.user
	ADD CONSTRAINT PK_user -- 회원정보 기본키
		PRIMARY KEY (
			userno -- 회원번호
		);

-- 도서정보
CREATE TABLE library.book (
	bookno    INTEGER(5)  NOT NULL COMMENT '도서번호', -- 도서번호
	booktitle VARCHAR(40) NULL     COMMENT '도서제목', -- 도서제목
	isRented  BOOLEAN     NULL     COMMENT '대출여부', -- 대출여부
	booksort  INTEGER(1)  NULL     COMMENT '도서구분', -- 도서구분
	count     INTEGER(5)  NULL     COMMENT '권수' -- 권수
)
COMMENT '도서정보';

-- 도서정보
ALTER TABLE library.book
	ADD CONSTRAINT PK_book -- 도서정보 기본키
		PRIMARY KEY (
			bookno -- 도서번호
		);

-- 대출반납내역
CREATE TABLE library.rentalstatus (
	rentalno   INTEGER(5)  NOT NULL COMMENT '대여번호', -- 대여번호
	bookno     INTEGER(5)  NULL     COMMENT '도서번호', -- 도서번호
	userno     INTEGER(5)  NULL     COMMENT '회원번호', -- 회원번호
	rentaldate DATE        NULL     COMMENT '대여일', -- 대여일
	returndate DATE        NULL     COMMENT '반납일', -- 반납일
	delaydate  INTEGER(10) NULL     COMMENT '연체일' -- 연체일
)
COMMENT '대출반납내역';

-- 대출반납내역
ALTER TABLE library.rentalstatus
	ADD CONSTRAINT PK_rentalstatus -- 대출반납내역 기본키
		PRIMARY KEY (
			rentalno -- 대여번호
		);

-- 도서구분
CREATE TABLE library.booksort (
	booksort INTEGER(1)  NOT NULL COMMENT '도서구분', -- 도서구분
	sortname VARCHAR(20) NULL     COMMENT '구분이름' -- 구분이름
)
COMMENT '도서구분';

-- 도서구분
ALTER TABLE library.booksort
	ADD CONSTRAINT PK_booksort -- 도서구분 기본키
		PRIMARY KEY (
			booksort -- 도서구분
		);

-- 도서정보
ALTER TABLE library.book
	ADD CONSTRAINT FK_booksort_TO_book -- 도서구분 -> 도서정보
		FOREIGN KEY (
			booksort -- 도서구분
		)
		REFERENCES library.booksort ( -- 도서구분
			booksort -- 도서구분
		);

-- 대출반납내역
ALTER TABLE library.rentalstatus
	ADD CONSTRAINT FK_book_TO_rentalstatus -- 도서정보 -> 대출반납내역
		FOREIGN KEY (
			bookno -- 도서번호
		)
		REFERENCES library.book ( -- 도서정보
			bookno -- 도서번호
		);

-- 대출반납내역
ALTER TABLE library.rentalstatus
	ADD CONSTRAINT FK_user_TO_rentalstatus -- 회원정보 -> 대출반납내역
		FOREIGN KEY (
			userno -- 회원번호
		)
		REFERENCES library.user ( -- 회원정보
			userno -- 회원번호
		);