package com.books.web.controller;

import com.books.dao.BookDAO;
import com.books.dao.OrderDAO;
import com.books.model.impl.Book;
import com.books.model.impl.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Set;

/**
 * Created by Harry on 10.06.2018.
 */

@Controller
public class OrderController {

    private static final Logger LOGGER =
            LogManager.getLogger(OrderController.class);

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    BookDAO bookDAO;

    @RequestMapping(value = "/orders-list", method = RequestMethod.GET)
    public String bookList(Model model) {
        LOGGER.debug("return index page");
        Set<Order> orders=orderDAO.findAll();
        model.addAttribute("orderList",orders);
        return "index/orderslist";
    }


    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public String findBook(Model model, @PathVariable Long id) {
        LOGGER.debug("get order profile page");
        Order order=orderDAO.findById(id);
        Book book=bookDAO.findBookById(order.getIdBook());
        model.addAttribute("currOrder",order);
        model.addAttribute("bookName",book.getName());
        return "index/orderPage";
    }



    @RequestMapping(value = "/create-order", method = RequestMethod.POST)
    public String createOrder(Model model,
                                    @RequestParam("bookId") Long bookId,
                                    @RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("address") String address,
                                    @RequestParam("quant") int quant
    )
            throws ParseException {

        LOGGER.debug("create order");

        Order order = new Order.OrderBuilder(firstName,lastName,bookId).address(address).quantity(quant).build();
        orderDAO.save(order);

        LOGGER.info("order saved");
        model.addAttribute("OrderSaved","Order was saved successfully");
        return "redirect:/";
    }
}
