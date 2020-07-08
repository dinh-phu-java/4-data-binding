package com.codegym.service;

import com.codegym.model.Email;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public class EmailService implements IEmailService{


    @Override
    public List<Email> findAll() {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Email.class).buildSessionFactory();
        Session session=null;
        ArrayList<Email> list= new ArrayList<>();
        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            list.addAll(session.createQuery("from Email").getResultList());

            session.getTransaction().commit();

        }
        finally{
            session.close();
            myfac.close();
        }
        return list;
    }

    @Override
    public Email findById(int id) {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Email.class).buildSessionFactory();
        Session session=null;
        Email email= null;
        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            email=session.get(Email.class,id);

            session.getTransaction().commit();

        }
        finally{
            session.close();
            myfac.close();
        }
        return email;
    }

    @Override
    public void save(Email model) {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Email.class).buildSessionFactory();
        Session session=null;

        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            session.save(model);

            session.getTransaction().commit();

        }
        finally{
            session.close();
            myfac.close();
        }

    }

    @Override
    public void remove(int id) {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Email.class).buildSessionFactory();
        Session session=null;

        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            Email deleteEmail=session.get(Email.class,id);
            if (deleteEmail!=null){
                session.delete(deleteEmail);
            }


            session.getTransaction().commit();

        }
        finally{
            session.close();
            myfac.close();
        }

    }

    @Override
    public void update(int id, Email model) {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Email.class).buildSessionFactory();
        Session session=null;

        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            Email editEmail=session.get(Email.class,id);
            if (editEmail!=null){
                editEmail.setFilter(model.getFilter());
                editEmail.setLanguage(model.getLanguage());
                editEmail.setPageSize(model.getPageSize());
                editEmail.setSignature(model.getSignature());
            }
            session.getTransaction().commit();
        }
        finally{
            session.close();
            myfac.close();
        }
    }

    public static void main(String[] args) {
        EmailService emailService=new EmailService();
        Email newEmail=new Email("Japanese",3,true,"nguyen van phot phat");
        emailService.update(1,newEmail);
    }
}
