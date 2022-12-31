package com.lms.util;

import org.hibernate.Session;

import java.util.Scanner;

public class MainMenu {
    public void menu(){
        boolean flag = false;
        while (!flag){
            Scanner choiceScanner = new Scanner(System.in);
            System.out.println("Menu : \n select a choice: \n 1)user menu \n 2)book menu \n 3)exit ");
            int choice = choiceScanner.nextInt();
            switch (choice){
                case 1:
                    new UserMenu().menu();
                    break;
                case 2:
                    new BookMenu().menu();
                    break;
                case 3:
                    System.out.println("have a good day");
                    flag = true;
                    break;
                default:
                    System.out.println("wrong choice ... select a correct choice");
            }
        }
    }
}
