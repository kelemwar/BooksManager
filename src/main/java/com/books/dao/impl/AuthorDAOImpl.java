package com.books.dao.impl;

import com.books.dao.AuthorDAO;
import com.books.model.impl.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Harry on 12.06.2018.
 */
@Component
public class AuthorDAOImpl implements AuthorDAO {


    private static final Logger LOGGER = LogManager.getLogger(GenreDAOImpl.class);

    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;


    @Autowired
    public AuthorDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Author findById(long id) {
        LOGGER.debug("searching for author " + id);

        return jdbcTemplate.queryForObject(FIND_AUTHOR_BY_ID, new Object[]{id}, new AuthorDAOImpl.AuthorMapper());
    }

    @Override
    public Set<Author> findAll() {
        LOGGER.debug("get list of all the authors");
        return new HashSet<Author>(jdbcTemplate.query(FIND_ALL_AUTHORS, new AuthorMapper()));
    }

    @Override
    public Set<Author> findAuthorsByBook(long id){
        LOGGER.debug("get list of all authors by book "+id);
        return new HashSet<Author>(jdbcTemplate.query(FIND_AUTHORS_BY_BOOK_ID,new Object[]{id}, new AuthorMapper()));
    }


    private class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");

            Author author = new Author.AuthorBuilder(name).id(id).build();

            return author;
        }

    }

    private class AuthorNewIdMapper implements RowMapper<Long> {
        @Override
        public Long mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong("new_author_id");
        }
    }

}
