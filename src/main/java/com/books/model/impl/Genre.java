package com.books.model.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Harry on 10.06.2018.
 */
public class Genre extends AbstractModelImpl {

     private static final Logger LOGGER = LogManager.getLogger(Genre.class);



    private Genre(Genre.GenreBuilder builder){
        this.setId(builder.id);
        this.setName(builder.name);
    }

    public static class GenreBuilder{

        private long id;
        private String name;

        public GenreBuilder(String name) {
            this.name = name;
        }

        public GenreBuilder(Genre genre) {
            this.id = genre.getId();
            this.name = genre.getName();
        }


        public Genre.GenreBuilder id(long id) {
            LOGGER.debug("id changed");
            this.id = id;
            return this;
        }

          public Genre build(){
            LOGGER.debug("genre builded success");
            return new Genre(this);
        }

        private void validateGenreParams() {
            if ("".equals(this.name)) {
                IllegalArgumentException ex = new IllegalArgumentException("name not be null");
                LOGGER.info("name is null or empty: "+ex);
                throw ex;
            }
            LOGGER.debug("genre is valid");
        }
    }
}