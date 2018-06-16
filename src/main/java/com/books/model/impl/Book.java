package com.books.model.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Set;

/**
 * Created by Harry on 11.06.2018.
 */

public class Book extends AbstractModelImpl {


    private String description;
    private float price;
    private Set<Genre> genres;
    private Set<Author> authors;
    private static final Logger LOGGER = LogManager.getLogger(Book.class);

    public Book() {
    }

    private Book(BookBuilder builder){
        this.setId(builder.id);
        this.setName(builder.name);
        this.description=builder.description;
        this.price=builder.price;
        this.authors=builder.authors;
        this.genres=builder.genres;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (Float.compare(book.price, price) != 0) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (genres != null ? !genres.equals(book.genres) : book.genres != null) return false;
        return authors != null ? authors.equals(book.authors) : book.authors == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + this.getId() + '\'' +
                "name='" + this.getName() + '\'' +
                "descr='" + description + '\'' +
                ", price=" + price +
                ", genres=" + genres +
                ", authors=" + authors +
                '}';
    }

    public static class BookBuilder{

        private long id;
        private String name;
        private String description;
        private float price;
        private Set<Author> authors;
        private Set<Genre> genres;

        public BookBuilder(String name) {
            this.name = name;
        }


        public BookBuilder id(long id) {
            LOGGER.debug("id changed");

            this.id = id;
            return this;
        }

        public BookBuilder description(String desrc){
            LOGGER.debug("description changed");
            this.description = desrc;
            return this;
        }


        public BookBuilder price(float price) {
            LOGGER.debug("price changed");
            this.price = price;
            return this;
        }

        public BookBuilder authors(Set<Author> aut) {
            LOGGER.debug("authors changed");
            this.authors = aut;
            return this;
        }

        public BookBuilder genres(Set<Genre> gen) {
            LOGGER.debug("genres changed");
            this.genres = gen;
            return this;
        }

        public Book build(){
            //validateApplicationParams();
            LOGGER.debug("Book builded with success");
            return new Book(this);
        }

        private void validateApplicationParams() {
            if ("".equals(this.name)) {
                IllegalArgumentException ex = new IllegalArgumentException("name should not be null");
                LOGGER.info("name not valid- name is null or empty: "+ex);
                throw ex;
            }

            LOGGER.debug("book is valid");
        }

        @Override
        public String toString() {
            return "BookBuilder{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", price=" + price +
                    ", authors=" + authors +
                    ", genres=" + genres +
                    '}';
        }
    }

}