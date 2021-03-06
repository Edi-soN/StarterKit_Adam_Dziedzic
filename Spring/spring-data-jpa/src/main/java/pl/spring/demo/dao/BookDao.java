package pl.spring.demo.dao;

import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookDao extends DaoAbstract<BookTo> {

	List<BookTo> findBookByTitle(String title);

	List<BookTo> findBooksByAuthor(String author);

}
