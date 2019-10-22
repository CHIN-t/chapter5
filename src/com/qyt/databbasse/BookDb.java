package com.qyt.databbasse;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.qyt.book.Book;

public class BookDb {
      private static Map<String, Book> books = 
  			new LinkedHashMap<String, Book>();
  	static {
  		books.put("1",new Book("1","java 开发"));
  		books.put("2",new Book("2","java web开发"));
  		books.put("3",new Book("3","java EE开发"));
  		books.put("4",new Book("4","Spring Boot开发"));
  		
  	}
  	//获取所有图书
  	public static Collection<Book> getAll() {
  		return books.values();
  	}
  	
  	//根据指定ID获取图书
  	public static Book getBook(String id) {
  		return books.get(id);
  	}
}
