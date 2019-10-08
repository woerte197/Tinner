// IBookAddListener.aidl
package com.wangyang.tinner;

// Declare any non-default types here with import statements
import  com.wangyang.tinner.Book;
interface IBookAddListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

     void NotificationRefresh(in Book book);

}
