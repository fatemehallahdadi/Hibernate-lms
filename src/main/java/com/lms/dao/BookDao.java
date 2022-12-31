package com.lms.dao;

import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookDao {

    public void createBook(String bookName,String authorName){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Book book = new Book();
            book.setBookName(bookName);
            book.setAuthorName(authorName);

            session.save(book);
            transaction.commit();
            System.out.println("Book save successfully");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Book> findAll(){
        Session session = HibernateUtil.getSession();
        return session.createQuery("SELECT a FROM Book a", Book.class).getResultList();
    }

    public Book findById(Long bookId){
        if (bookId==null){
            System.out.println("book id is null...");
            return null;
        }else {
            Session session = HibernateUtil.getSession();
            return session.get(Book.class,bookId);
        }
    }

    public void updateBook(Long bookId,String bookName,String authorName){
        if (bookId==null){
            System.out.println("book id is null");
        }else {
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Book book = session.get(Book.class,bookId);
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                session.update(book);
                transaction.commit();
                System.out.println("Book update successfully");
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    public void deleteBook(Long bookId) {
        if (bookId==null){
            System.out.println("book id is null...");
        }else {
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Book book = session.get(Book.class,bookId);
                if (book.getUser()!=null){
                    System.out.println("you can't delete this book because this book is borrowed yet");
                }else {
                    session.delete(book);
                    transaction.commit();
                    System.out.println("Book delete successfully");
                }
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }
}
