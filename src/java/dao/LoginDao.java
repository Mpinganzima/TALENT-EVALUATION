/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import domain.Login;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;


/**
 *
 * @author pc
 */
public class LoginDao extends GenericDao<Login>{
    public List<Login> login(String psw,String un){
      Session  s=HibernateUtil.getSessionFactory().openSession();
      Query q=s.createQuery("from Login s where s.password= :v and s.username= :l");
      q.setString("v", psw);
       q.setString("l", un);
      List<Login> u=q.list();
      return u;
    } 
   
}