package com.lms.util;

import com.lms.dao.UserDao;

import java.util.Scanner;

public class UserMenu {
    public void menu() {
        UserDao userDao = new UserDao();
        boolean flag = false;


        while (!flag){
            Scanner choiceScanner = new Scanner(System.in);
            Scanner intScanner = new Scanner(System.in);
            Scanner strScanner = new Scanner(System.in);
            System.out.println("Menu : \n select a choice: \n 1)create user" +
                    " \n 2)show all users \n 3)show user by id \n 4)update user \n 5)delete user by id" +
                    " \n 6)borrow book \n 7)return book  \n 8)back to main menu");

            int choice = choiceScanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("enter first name:");
                    String firstName = strScanner.nextLine();
                    System.out.println("enter last name:");
                    String lastName = strScanner.nextLine();
                    userDao.createUser(firstName,lastName);
                    break;
                case 2:
                    userDao.findAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("enter the userId:");
                    Long userId = strScanner.nextLong();
                    System.out.println(userDao.findById(userId));
                    break;
                case 4:
                    System.out.println("enter the userId for updating:");
                    Long theId = intScanner.nextLong();
                    System.out.println("enter first name:");
                    String newFirstName = strScanner.nextLine();
                    System.out.println("enter last name:");
                    String newLastName = strScanner.nextLine();
                    userDao.updateUser(theId,newFirstName,newLastName);
                    break;
                case 5:
                    System.out.println("enter the userId for delete:");
                    Long id = intScanner.nextLong();
                    userDao.deleteUser(id);
                    break;
                case 6:
                    System.out.println("enter the userId for borrow book:");
                    Long userIdBorrow = intScanner.nextLong();
                    System.out.println("enter the bookId for borrow book:");
                    Long bookIdBorrow = intScanner.nextLong();
                    userDao.borrowBook(userIdBorrow,bookIdBorrow);
                    break;
                case 7:
                    System.out.println("enter the userId for return book:");
                    Long userIdReturn = intScanner.nextLong();
                    System.out.println("enter the bookId for return book:");
                    Long bookIdReturn = intScanner.nextLong();
                    userDao.returnBook(userIdReturn,bookIdReturn);
                    break;
                case 8:
                    flag = true;
                    break;
                default:
                    System.out.println("wrong choice ... select a correct choice");
            }
        }
    }
}
