drop table Orders CASCADE CONSTRAINTS;
drop table BooksAuthors CASCADE CONSTRAINTS;
drop table BooksGenres CASCADE CONSTRAINTS;
drop table Authors CASCADE CONSTRAINTS;
drop table Books CASCADE CONSTRAINTS;
drop table Genres CASCADE CONSTRAINTS;

CREATE TABLE Books(
   id NUMERIC(3) NOT NuLL,
   name VARCHAR(30) NOT NULL,
   description  VARCHAR(300),
   price FLOAT NOT NULL,
   CHECK (price > 0),
   CONSTRAINT book_pk
          PRIMARY KEY (id)
);


CREATE TABLE Genres(
   id NUMERIC(4) NOT NuLL,
   name VARCHAR(15) NOT NULL,
   CONSTRAINT genre_pk
          PRIMARY KEY (id)
);

CREATE TABLE Authors(
   id NUMERIC(4) NOT NuLL,
   name VARCHAR(15) NOT NULL,
   CONSTRAINT author_pk
          PRIMARY KEY (id)
);

CREATE TABLE BooksGenres(
  id_book NUMERIC(4),
  id_genre NUMERIC(4),
   PRIMARY KEY (id_book, id_genre),
    FOREIGN KEY (id_book) REFERENCES Books (id),
    FOREIGN KEY (id_genre) REFERENCES Genres (id)
);

CREATE TABLE BooksAuthors(
  id_book NUMERIC(4),
  id_author NUMERIC(4),
   PRIMARY KEY (id_book, id_author),
    FOREIGN KEY (id_book) REFERENCES Books (id),
    FOREIGN KEY (id_author) REFERENCES Authors (id)
);


CREATE TABLE Orders(
    id   NUMBER(6) NOT NULL,
    first_name VARCHAR(10) NOT NULL,
    last_name  VARCHAR(10) NOT NULL,
   address    VARCHAR2(100) NOT NULL,
   id_book NUMBER(4) NOT NULL,
    quant NUMBER(4),
    order_date DATE,
    CHECK (quant > 0),
   PRIMARY KEY (id),
    FOREIGN KEY (id_book) REFERENCES Books (id)
 );


CREATE SEQUENCE genres_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

 CREATE SEQUENCE books_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

 CREATE SEQUENCE authors_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

  CREATE SEQUENCE orders_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;



INSERT INTO Books VALUES (books_seq.NEXTVAL,'The Government Inspector','description text',100);
INSERT INTO Books VALUES (books_seq.NEXTVAL,'The Old Man and the Sea','description text',352);
INSERT INTO Books VALUES (books_seq.NEXTVAL,'Nine Princes in Amber','description text',256);
INSERT INTO Books VALUES (books_seq.NEXTVAL,'Game of Thrones','description text',548);
INSERT INTO Books VALUES (books_seq.NEXTVAL,'American Gods','description text',259);



INSERT INTO Genres VALUES (genres_seq.NEXTVAL,'fantasy');
INSERT INTO Genres VALUES (genres_seq.NEXTVAL,'novel');
INSERT INTO Genres VALUES (genres_seq.NEXTVAL,'Comedy');
INSERT INTO Genres VALUES (genres_seq.NEXTVAL,'Tragedy');


INSERT INTO Authors VALUES (authors_seq.NEXTVAL,'Neil Gaiman');
INSERT INTO Authors VALUES (authors_seq.NEXTVAL,'Nikolai Gogol');
INSERT INTO Authors VALUES (authors_seq.NEXTVAL,'Roger Joseph Zelazny');
INSERT INTO Authors VALUES (authors_seq.NEXTVAL,'George Raymond Richard Martin');
INSERT INTO Authors VALUES (authors_seq.NEXTVAL,'Ernest Miller Hemingway');


INSERT INTO BooksAuthors VALUES (5,1);
INSERT INTO BooksAuthors VALUES (1,2);
INSERT INTO BooksAuthors VALUES (3,3);
INSERT INTO BooksAuthors VALUES (2,5);
INSERT INTO BooksAuthors VALUES (4,4);


INSERT INTO BooksGenres VALUES (1,3);
INSERT INTO BooksGenres VALUES (2,2);
INSERT INTO BooksGenres VALUES (3,1);
INSERT INTO BooksGenres VALUES (4,1);
INSERT INTO BooksGenres VALUES (5,1);



commit;

