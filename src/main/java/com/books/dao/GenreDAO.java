package com.books.dao;

import com.books.model.impl.Genre;
import java.util.Set;

/**
 * Created by Harry on 12.06.2018.
 */
public interface GenreDAO {

    Set<Genre> findAll();

    Genre findById(long id);

    Set<Genre> findGenresByBook(long id);

    String GENRE_NEW_ID = "SELECT genres_seq.NEXTVAL as new_genre_id FROM dual";

    String FIND_GENRE_BY_ID="Select * from GENRES where id=?";

    String FIND_ALL_GENRES="Select * from GENRES";

    String FIND_GENRES_BY_BOOK_ID="Select g.id,g.name from GENRES g, BooksGenres bg where bg.id_genre=g.id and id_book=?";

}
