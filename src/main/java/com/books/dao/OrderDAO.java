package com.books.dao;

import com.books.model.impl.Order;
import java.util.Set;

/**
 * Created by Harry on 12.06.2018.
 */
public interface OrderDAO {

    void save(Order order);

    void removeWithBookRemoval(long id);

    Set<Order> findAll();

    Order findById(long id);

    String ORDER_NEW_ID = "SELECT orders_seq.NEXTVAL as new_order_id FROM dual";

    String FIND_ORDER_BY_ID="Select * from ORDERS where id=?";

    String FIND_ALL_ORDER="Select * from ORDERS";

    String ORDER_ADD = "INSERT ALL " +
            "INTO ORDERS VALUES (?,?,?,?,?,?,sysdate)" +
            "SELECT * FROM dual";

    String ORDER_REMOVE_BY_BOOK = "DELETE FROM ORDERS WHERE id_book= ?";
}
