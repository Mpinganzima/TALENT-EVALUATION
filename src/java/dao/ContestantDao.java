/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Contestant;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;


/**
 *
 * @author pc
 */
public class ContestantDao{
    public String save(Contestant a) {
        
        Session ss = HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.save(a);
        ss.getTransaction().commit();
        ss.close();
        return "success";
        

    }

    public void update(Contestant a) throws Errors {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.update(a);
        ss.getTransaction().commit();
        ss.close();
        } catch (Exception e) {
            throw new Errors();
        }
        

    }

    public void delete(Contestant a) {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.delete(a);
        ss.getTransaction().commit();
        ss.close();

    }

    public List<Contestant> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("from Contestant");
        List<Contestant> li = q.list();
        s.close();
        return li;
    }
    public Contestant findbyId(Class c, Serializable id) {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Contestant s = (Contestant) ss.get(c, id);
        ss.close();
        return s;
    }    
}
