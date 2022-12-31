package com.lms;

import com.lms.util.HibernateUtil;
import com.lms.util.MainMenu;
import org.hibernate.Session;


public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        System.out.println("connection is ok...");
        session.close();
        new MainMenu().menu();
    }
}
