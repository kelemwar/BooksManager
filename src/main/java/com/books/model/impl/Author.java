package com.books.model.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Harry on 10.06.2018.
 */
public class Author extends AbstractModelImpl {

    private static final Logger LOGGER = LogManager.getLogger(Author.class);



    private Author(Author.AuthorBuilder builder){
        this.setId(builder.id);
        this.setName(builder.name);
    }

    public static class AuthorBuilder{

        private long id;
        private String name;

        public AuthorBuilder(String name) {
            this.name = name;
        }


        public Author.AuthorBuilder id(long id) {
            LOGGER.debug("id changed");
            this.id = id;
            return this;
        }

        public Author build(){
            LOGGER.debug("Author builded with success");
            return new Author(this);
        }


    }
}
