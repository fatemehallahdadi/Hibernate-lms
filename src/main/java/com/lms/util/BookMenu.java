package com.lms.util;

import com.lms.dao.BookDao;
import com.lms.dao.UserDao;

import java.util.Scanner;

public class BookMenu {
    public void menu() {
        BookDao bookDao = new BookDao();
        boolean flag = false;


        while (!flag){
            Scanner choiceScanner = new Scanner(System.in);
            Scanner intScanner = new Scanner(System.in);
            Scanner strScanner = new Scanner(System.in);
            System.out.println("Menu : \n select a choice: \n 1)create book" +
                    " \n 2)show all books \n 3)show book by id \n 4)update book \n 5)delete book by id" +
                    "  \n 6)back to main menu");

            int choice = choiceScanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("enter book name:");
                    String bookName = strScanner.nextLine();
                    System.out.println("enter author name:");
                    String authorName = strScanner.nextLine();
                    bookDao.createBook(bookName,authorName);
                    break;
                case 2:
                    bookDao.findAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("enter the bookId:");
                    Long bookId = strScanner.nextLong();
                    System.out.println(bookDao.findById(bookId));
                    break;
                case 4:
                    System.out.println("enter the bookId for updating:");
                    Long theId = intScanner.nextLong();
                    System.out.println("enter book name:");
                    String newBookName = strScanner.nextLine();
                    System.out.println("enter author name:");
                    String newAuthorName = strScanner.nextLine();
                    bookDao.updateBook(theId,newBookName,newAuthorName);
                    break;
                case 5:
                    System.out.println("enter the bookId for delete:");
                    Long id = intScanner.nextLong();
                    bookDao.deleteBook(id);
                    break;
                case 6:
                    flag = true;
                    break;
                default:
                    System.out.println("wrong choice ... select a correct choice");
            }
        }
    }

}
