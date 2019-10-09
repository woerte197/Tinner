package com.wangyang.tinner;
import  com.wangyang.tinner.Book;
import  com.wangyang.tinner.IBookAddListener;
interface IBook {
 List<Book> getBookList();
 void addBook(in  Book book);
 void register(in IBookAddListener listener);
 void unRegister(in IBookAddListener listener);
}
