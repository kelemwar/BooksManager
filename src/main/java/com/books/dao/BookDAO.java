package com.books.dao;

import com.books.model.impl.Book;
import java.util.Set;

/**
 * Created by Harry on 12.06.2018.
 */
public interface BookDAO {

    Book findBookById(long id);

    void save(Book book,long[] authors, boolean isAuthorChanged,long[] genres,boolean isGenreChanged);

    void remove(Book book);

    Set<Book> findAll();

    Set<Book> findBookByGenre(long id);

    Set<Book> findBookByAuthor(long id);


    String BOOK_NEW_ID = "SELECT books_seq.NEXTVAL as new_book_id FROM dual";

    String FIND_BOOK_BY_ID="Select * from BOOKS where id=?";

    String FIND_ALL_BOOKS="Select * from BOOKS";

    String FIND_BOOK_BY_GENRE = "select books.id as id ,books.name as name, books.description as description,books.price as price from BOOKS,BooksGenres\n" +
            "where BooksGenres.ID_BOOK=BOOKS.ID\n" +
            "and BooksGenres.ID_GENRE=?";

    String FIND_BOOK_BY_AUTHOR = "select books.id as id ,books.name as name, books.description as description,books.price as price from BOOKS,BooksAuthors\n" +
            " where BooksAuthors.ID_BOOK=BOOKS.ID\n" +
            "    and BooksAuthors.ID_AUTHOR=?";

    String INSERT_BOOK = "INSERT INTO Books VALUES (?,?,?,?)";

    String INSERT_BG = "INSERT INTO BooksGenres VALUES (?,?)";

    String INSERT_BA = "INSERT INTO BA VALUES (?,?)";

    String UPDATE_BOOK="Update Books\n" +
            "set NAME= ?,DESCRIPTION=?,PRICE=?\n" +
            "where ID=?";

    String BA_REMOVE = "DELETE FROM BA WHERE id_book = ?";

    String BG_REMOVE = "DELETE FROM BooksGenres WHERE id_book= ?";

    String BOOK_REMOVE = "DELETE FROM BOOKS WHERE id= ?";
}
