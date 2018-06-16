package com.books.web.controller;
/**
 * Created by Harry on 10.06.2018.
 */

import com.books.dao.AuthorDAO;
import com.books.dao.BookDAO;
import com.books.dao.GenreDAO;
import com.books.model.impl.Author;
import com.books.model.impl.Book;
import com.books.model.impl.Genre;
import org.apache.commons.lang.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;


@Controller
public class BookController {


    @Autowired
    BookDAO bookDAO;

    @Autowired
    GenreDAO genreDAO;

    @Autowired
    AuthorDAO authorDAO;

    private static final Logger LOGGER =
            LogManager.getLogger(IndexController.class);

    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String bookList(Model model) {
        LOGGER.debug("return books list page");
        Set<Book> books=bookDAO.findAll();
        model.addAttribute("bookList",books);
        return "index/bookslist";
    }


    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String findBook(Model model, @PathVariable Long id) {
        LOGGER.debug("get book profile page");
        Book book=bookDAO.findBookById(id);

        model.addAttribute("currBook",book);
        model.addAttribute("authorsForBook",book.getAuthors());
        model.addAttribute("genresForBook",book.getGenres());

        model.addAttribute("genreList",genreDAO.findAll());
        model.addAttribute("authorList",authorDAO.findAll());

        return "index/bookPage";
    }

    @RequestMapping(value = "/books-list-by-genre/{id}", method = RequestMethod.GET)
    public String findBooksByGenre(Model model, @PathVariable Long id) {
        LOGGER.debug("get books by genre");
        Set<Book> books=bookDAO.findBookByGenre(id);
        model.addAttribute("bookList",books);
        return "index/bookslist";
    }

    @RequestMapping(value = "/books-list-by-author/{id}", method = RequestMethod.GET)
    public String findBooksByAuthor(Model model, @PathVariable Long id) {
        LOGGER.debug("get books by author");
        Set<Book> books=bookDAO.findBookByAuthor(id);
        model.addAttribute("bookList",books);
        return "index/bookslist";
    }


    @RequestMapping(value = "/create-book-form", method = RequestMethod.GET)
    public String createBookForm(Model model) {
        LOGGER.debug("create book form page");
        model.addAttribute("authorsList", authorDAO.findAll());
        model.addAttribute("genresList", genreDAO.findAll() );

        return "index/createBook";
    }


    @RequestMapping(value = "/create-book", method = RequestMethod.POST)
       public String createBook(Model model, /*@ModelAttribute("newBook") Book newBook,*/
                                @RequestParam("name") String name,
                                @RequestParam("descr") String descr,
                             @RequestParam("price") float price,
                             @RequestParam("selectedAuthors") String[] auts,
                             @RequestParam("selectedGenres") String[] gens )
                           //  @ModelAttribute("newAuthors") List<Author> authors)
            throws ParseException {
        LOGGER.debug("create book");

                if ( "".equals(auts)||"".equals(gens)||auts==null||gens==null)
                    return "redirect:/create-book";

                if ( "".equals(name)||name==null||price==0)
                    return "redirect:/create-book";


                Book  book = new Book.BookBuilder(name).description(descr).price(price).build();
         bookDAO.save(book,pseudoOneStepConversion(auts),true,pseudoOneStepConversion(gens),true);


        LOGGER.info("book saved");
        model.addAttribute("Booksaved","Book was saved successfully");
        return "redirect:/";
    }

    @RequestMapping(value = "/edit-book-form/{id}", method = RequestMethod.GET)
    public String editBookForm(Model model , @PathVariable Long id) {
        LOGGER.debug("edit book form page");
        Book book=bookDAO.findBookById(id);

        model.addAttribute("currBook",book);
        model.addAttribute("authorsForBook",book.getAuthors());
        model.addAttribute("genresForBook",book.getGenres());
        model.addAttribute("authorList", authorDAO.findAll());
        model.addAttribute("genreList", genreDAO.findAll() );

        return "index/editBook";
    }


    @RequestMapping(value = "/edit-book/{id}", method = RequestMethod.POST)
    public String editBook(Model model, @PathVariable Long id, /*@ModelAttribute("newBook") Book newBook,*/
                             @RequestParam("name") String name,
                             @RequestParam("descr") String descr,
                             @RequestParam("price") float price,
                             @RequestParam("selectedAuthors") String[] auts,
                             @RequestParam("selectedGenres") String[] gens )
        //  @ModelAttribute("newAuthors") List<Author> authors)
            throws ParseException {
        LOGGER.debug("edit book");

        if ( "".equals(auts)||"".equals(gens)||auts==null||gens==null)
            return "redirect:/edit-book-form/"+id;

        if ( "".equals(name)||name==null||price==0)
            return "redirect:/edit-book-form/"+id;



                Set<Author> autList = new HashSet<Author>() ;
                Set<Genre> genList= new HashSet<Genre>() ;
            long[] autsLong = pseudoOneStepConversion(auts);
            long[] gensLong = pseudoOneStepConversion(gens);

                for (int i = 0; i < autsLong.length; i++) {
                    autList.add(authorDAO.findById(autsLong[i]));
                }

                for (int i = 0; i < gensLong.length; i++) {
                    genList.add(genreDAO.findById(gensLong[i]));
                }

                Book  book = new Book.BookBuilder(name).id(id).description(descr).price(price).authors(autList).genres(genList).build();

                Book  currentBook = bookDAO.findBookById(id);

                if(book.equals(currentBook)){
                    LOGGER.info("Data the same");
                return "redirect:/book/"+id;
                }else if(autList.equals(currentBook.getAuthors())) {
                    if (genList.equals(currentBook.getGenres())) {
                        bookDAO.save(book, autsLong, false, gensLong, false);
                    }else{
                        bookDAO.save(book, autsLong, false, gensLong, true);
                    }
                }else if(genList.equals(currentBook.getGenres())){
                    bookDAO.save(book, autsLong, true, gensLong, false);
                }else {
                    bookDAO.save(book, autsLong, true, gensLong, true);
                }

        LOGGER.info("book saved/edited");
        model.addAttribute("BookSaved","Book was saved successfully");
        return "redirect:/book/"+id;
    }


    @RequestMapping(value = "/delete-book/{id}", method = RequestMethod.GET)
    public String deleteBook(Model model , @PathVariable long id) {
        LOGGER.debug("remove book "+id);
        bookDAO.remove(new Book.BookBuilder("removename").id(id).build());
        model.addAttribute("BookRemoved","Book was removes successfully");
        return "redirect:/books-list";
    }



    private long[] pseudoOneStepConversion(String[] numbers) {
        Long[] result = new Long[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            result[i] = Long.parseLong(numbers[i]);

        long[] longArray = ArrayUtils.toPrimitive(result);
        return longArray;
    }
}
