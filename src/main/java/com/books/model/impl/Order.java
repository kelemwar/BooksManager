package com.books.model.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Harry on 10.05.2018.
 */
public class Order implements Serializable{

    private static final Logger LOGGER = LogManager.getLogger(Book.class);



    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private long idBook;
    private String bookName;
    private int quantity;
    private Date orderDate;


    private Order(OrderBuilder builder){
        this.id=builder.id;
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.address=builder.address;
        this.idBook=builder.idBook;
        this.quantity=builder.quantity;
        this.orderDate=builder.orderDate;
        this.bookName=builder.bookName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Long getIdBook() {
        return idBook;
    }

    public String getBookName() {
        return bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (idBook != order.idBook) return false;
        if (quantity != order.quantity) return false;
        if (firstName != null ? !firstName.equals(order.firstName) : order.firstName != null) return false;
        if (lastName != null ? !lastName.equals(order.lastName) : order.lastName != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        return orderDate != null ? orderDate.equals(order.orderDate) : order.orderDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (int) (idBook ^ (idBook >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        return result;
    }

    public static class OrderBuilder{

        private long id;
        private String firstName;
        private String lastName;
        private String address;
        private long idBook;
        private int quantity;
        private Date orderDate;
        private String bookName;

        public OrderBuilder(String name,String lastName,long idBook) {
            this.firstName = name;
            this.lastName = lastName;
            this.idBook=idBook;
        }

        public OrderBuilder(Order order) {
            this.id = order.getId();
            this.firstName = order.getFirstName();
            this.lastName = order.getLastName();
            this.idBook = order.getIdBook();
            this.quantity = order.getQuantity();
            this.bookName=order.getBookName();

        }


        public OrderBuilder id(long id) {
            LOGGER.debug("id changed");
            this.id = id;
            return this;
        }

        public OrderBuilder address(String address){
            LOGGER.debug("address changed");
            this.address = address;
            return this;
        }

        public OrderBuilder bookName(String bookName) {
            LOGGER.debug("bookName changed");
            this.bookName = bookName;
            return this;
        }

        public OrderBuilder quantity(int quantity) {
            LOGGER.debug("quantity changed");
            this.quantity = quantity;
            return this;
        }

        public OrderBuilder orderDate(Date date) {
            LOGGER.debug("orderDate changed");
            this.orderDate = date;
            return this;
        }

        public Order build(){
            //validateApplicationParams();
            LOGGER.debug("Order builded with success");
            return new Order(this);
        }

        private void validateApplicationParams() {
            if (this.idBook==0) {
                IllegalArgumentException ex = new IllegalArgumentException("name should not be null");
                LOGGER.info("name not valid- name is null or empty: "+ex);
                throw ex;
            }

            LOGGER.debug("order is valid");
        }
    }


}
