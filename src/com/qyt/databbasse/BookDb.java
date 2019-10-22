package com.qyt.databbasse;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.qyt.book.Book;

public class BookDb {
      private static Map<String, Book> books = 
  			new LinkedHashMap<String, Book>();
  	static {
  		books.put("1",new Book("1","java ����"));
  		books.put("2",new Book("2","java web����"));
  		books.put("3",new Book("3","java EE����"));
  		books.put("4",new Book("4","Spring Boot����"));
  		
  	}
  	//��ȡ����ͼ��
  	public static Collection<Book> getAll() {
  		return books.values();
  	}
  	
  	//����ָ��ID��ȡͼ��
  	public static Book getBook(String id) {
  		return books.get(id);
  	}
}
