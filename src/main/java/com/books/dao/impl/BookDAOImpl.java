package com.books.dao.impl;

import com.books.dao.AuthorDAO;
import com.books.dao.BookDAO;
import com.books.dao.GenreDAO;
import com.books.dao.OrderDAO;
import com.books.model.impl.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Harry on 12.06.2018.
 */

@Component
public class BookDAOImpl implements BookDAO {


    @Autowired
    AuthorDAO authorDAO;

    @Autowired
    GenreDAO genreDAO;

    @Autowired
    OrderDAO orderDAO;

    private static final Logger LOGGER = LogManager.getLogger(BookDAOImpl.class);

    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;


    @Autowired
    public BookDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Book findBookById(long id) {
        LOGGER.debug("searching for book " + id);
        return jdbcTemplate.queryForObject(FIND_BOOK_BY_ID, new Object[]{id}, new BookMapper());
    }

    @Override
    @Transactional
    public void save(Book book, long[] authors, boolean isAuthorChanged,long[] genres, boolean isGenreChanged) {

        if (book.getId() == 0) {

            Long newId = jdbcTemplate.queryForObject(BOOK_NEW_ID, new BookNewIdMapper());
            LOGGER.debug("creating book " + newId);

            jdbcTemplate.update(INSERT_BOOK,
                    newId, book.getName(), book.getDescription(), book.getPrice()
            );

            for (int i = 0; i < authors.length; i++) {
                jdbcTemplate.update(INSERT_BA, newId, authors[i]);
            }

            for (int i = 0; i < genres.length; i++) {
                jdbcTemplate.update(INSERT_BG, newId, genres[i]);
            }

        } else {
            long currId=book.getId();
            LOGGER.debug("updating book " +  currId);

            jdbcTemplate.update(UPDATE_BOOK, book.getName(), book.getDescription(), book.getPrice(), currId);

            if (isAuthorChanged) {
                LOGGER.debug("updating authors for " +  currId);
                jdbcTemplate.update(BA_REMOVE, currId);

                for (int i = 0; i < authors.length; i++)
                    jdbcTemplate.update(INSERT_BA, currId, authors[i]);
            }

            if (isGenreChanged) {
                LOGGER.debug("updating genres for " +  currId);
                jdbcTemplate.update(BG_REMOVE, currId);

                for (int i = 0; i < genres.length; i++)
                    jdbcTemplate.update(INSERT_BG, currId, genres[i]);
            }

        }
    }

    @Override
    public void remove(Book book) {
        long currId=book.getId();
        LOGGER.info("book " + currId + " removed");
        orderDAO.removeWithBookRemoval(currId);
        jdbcTemplate.update(BA_REMOVE, currId);
        jdbcTemplate.update(BA_REMOVE, currId);
        jdbcTemplate.update(BG_REMOVE, currId);
        jdbcTemplate.update(BOOK_REMOVE, currId);
           }

    @Override
    public Set<Book> findAll() {
        LOGGER.debug("get list of all the books");
        return new HashSet<Book>(jdbcTemplate.query(FIND_ALL_BOOKS, new BookMapper()));
    }

    @Override
    public Set<Book> findBookByGenre(long id) {
        LOGGER.debug("get list of all books by genre "+id);
        return new HashSet<Book>(jdbcTemplate.query(FIND_BOOK_BY_GENRE,new Object[]{id}, new BookMapper()));
    }

    @Override
    public Set<Book> findBookByAuthor(long id) {
        LOGGER.debug("get list of all books by author "+id);
        return new HashSet<Book>(jdbcTemplate.query(FIND_BOOK_BY_AUTHOR,new Object[]{id}, new BookMapper()));
    }

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            float price = resultSet.getFloat("price");

            Book book = new Book.BookBuilder(name).id(id).description(description).price(price).authors(authorDAO.findAuthorsByBook(id)).genres(genreDAO.findGenresByBook(id)).build();

            return book;
        }
    }

    private class BookNewIdMapper implements RowMapper<Long> {
        @Override
        public Long mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong("new_book_id");
        }
    }

}
