package com.books.web.controller;


import com.books.dao.AuthorDAO;
import com.books.dao.GenreDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Harry on 10.06.2018.
 */

@Controller
public class IndexController {


    @Autowired
    GenreDAO genreDAO;

    @Autowired
    AuthorDAO authorDAO;

    private static final Logger LOGGER =
            LogManager.getLogger(IndexController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        LOGGER.debug("return index page");

        model.addAttribute("genreList",genreDAO.findAll());
        model.addAttribute("authorList",authorDAO.findAll());

        return "index/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model ) {

            LOGGER.debug("return login page");
            return "index/loginPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model) {
        LOGGER.debug("403 page");
            LOGGER.error("not enough priveleges");
            model.addAttribute("msg",
                    "You do not have permission to access this page!");
        return "index/Errors/Error403";
    }

}
