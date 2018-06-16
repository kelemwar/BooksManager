package com.books.dao;

import com.books.model.impl.Author;
import java.util.Set;

/**
 * Created by Harry on 12.06.2018.
 */
public interface AuthorDAO {


    Set<Author> findAll();

    Set<Author> findAuthorsByBook(long id);

    Author findById(long id);


    String AUTHOR_NEW_ID = "SELECT authors_seq.NEXTVAL as new_author_id FROM dual";

    String FIND_AUTHOR_BY_ID="Select * from AUTHORS where id=?";

    String FIND_AUTHORS_BY_BOOK_ID="Select a.id,a.name from AUTHORS a, BooksAuthors ba where ba.id_author=a.id and id_book=?";

    String FIND_ALL_AUTHORS="Select * from AUTHORS";

}
