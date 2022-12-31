package com.lms.dao;

import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class UserDao {


    public void createUser(String firstName,String lastName){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);

            session.save(user);
            transaction.commit();
            System.out.println("User save successfully");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<User> findAll(){
        Session session = HibernateUtil.getSession();
        return session.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    public User findById(Long userId){
        if (userId==null){
            System.out.println("user id is null...");
            return null;
        }else {
            Session session = HibernateUtil.getSession();
            return session.get(User.class,userId);
        }
    }

    public void updateUser(Long userId,String firstName,String lastName){
        if (userId==null){
            System.out.println("user id is null...");
        }else {
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class,userId);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                session.update(user);
                transaction.commit();
                System.out.println("User update successfully");
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    public void deleteUser(Long userId) {
        if (userId==null){
            System.out.println("user id is null...");
        }else {
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class,userId);
                if (!user.getBooks().isEmpty()){
                    System.out.println("you can't delete user because this user have some books yet...");
                }else {
                    session.delete(user);
                    transaction.commit();
                    System.out.println("user delete successfully");
                }
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    public void borrowBook(Long userId,Long bookId){
        if (userId==null || bookId==null){
            System.out.println("user id or book id is null...");
        }else {
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class,userId);
                Book book = session.get(Book.class, bookId);
                book.setUser(user);
                session.update(book);
                transaction.commit();
                System.out.println("User update successfully");
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    public void returnBook(Long userIdReturn, Long bookIdReturn) {
        if (userIdReturn==null || bookIdReturn==null){
            System.out.println("user id or book id is null");
        }else {
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Book book = session.get(Book.class, bookIdReturn);
                book.setUser(null);
                session.update(book);
                transaction.commit();
                System.out.println("User update successfully");
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }
}

