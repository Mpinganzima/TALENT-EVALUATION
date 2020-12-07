/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import dao.ContestantDao;
import dao.EvaluationDao;
import domain.Contestant;
import domain.Evaluation;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class EvaluationModel {

private List<Contestant> contestant=new ContestantDao().findAll(); 
private List<Contestant> conts=new ArrayList<>();
private String ContId=new String();

private Evaluation evaluation=new Evaluation();
private Evaluation evadetails=new Evaluation();
EvaluationDao evaDao=new EvaluationDao();
private String id=new String();
private String searchKey=new String();
private List<Evaluation> evaList;

        
@PostConstruct
public void init(){
evaList=new EvaluationDao().findAll(Evaluation.class);
conts = new ArrayList<>();
        for (Contestant co : contestant) {
            if (co.getFName()!= null) {
                conts.add(co);
            }
        }
}

 public Contestant finalConts() {
        for (Contestant co : contestant) {
            if (co.getContId().toString().equals(ContId)) {
                return co;
            }
        }
        return null;
    }

public void createEvaluation1(){
      if(evaluation.getGr1()>20 || evaluation.getGr2()>20 || evaluation.getGr3()>20){
          
            RequestContext context = RequestContext.getCurrentInstance();
    context.execute("swal('','Grades above 20 are not allowed!','warning')");  
      }
      else{
 evaluation.setGrading1(((evaluation.getGr1()+evaluation.getGr2()+evaluation.getGr3())*20)/60);
 evaluation.setContestant(finalConts());
 evaDao=new EvaluationDao();
    evaDao.save(evaluation);
    evaluation=new Evaluation();
    evaList=evaDao.findAll(Evaluation.class);
    RequestContext context = RequestContext.getCurrentInstance();
    context.execute("swal('Thank You','Submitted!','success')");
      }
    
}

public void createEvaluation2(){
      if(evaluation.getGr1()>20 || evaluation.getGr2()>20 || evaluation.getGr3()>20){
          
            RequestContext context = RequestContext.getCurrentInstance();
    context.execute("swal('','Grades above 20 are not allowed!','warning')");  
      }
      else{
 evadetails.setGrading2(((evaluation.getGr1()+evaluation.getGr2()+evaluation.getGr3())*20)/60);
// evadetails.setContestant(finalConts());
 evaDao=new EvaluationDao();
    evaDao.update(evadetails);
    evadetails=new Evaluation();
    evaList=evaDao.findAll(Evaluation.class);
    RequestContext context = RequestContext.getCurrentInstance();
    context.execute("swal('Thank You','Submitted!','success')");
      }
    
}
      
public void createEvaluation3(){
    
      if(evaluation.getGr1()>20 || evaluation.getGr2()>20 || evaluation.getGr3()>20){
          
            RequestContext context = RequestContext.getCurrentInstance();
    context.execute("swal('','Grades above 20 are not allowed!','warning')");  
      }
      else{
          
 evadetails.setGrading3(((evaluation.getGr1()+evaluation.getGr2()+evaluation.getGr3())*20)/60);
// evadetails.setContestant(finalConts());
 evaDao=new EvaluationDao();
    evaDao.update(evadetails);
    evadetails=new Evaluation();
    evaList=evaDao.findAll(Evaluation.class);
    RequestContext context = RequestContext.getCurrentInstance();
    context.execute("swal('Thank You','Submitted!','success')");
      }
    
}
     public void search() {
        if (searchKey.length() > 0) {
            evaList = new ArrayList<>();
            List<Evaluation> currnt = evaDao.findAll(Evaluation.class);
            for (Evaluation evaluation : currnt) {
                if (evaluation.getEvaId().toString().contains(searchKey)) {
                    evaList.add(evaluation);
                }
            }
        } else {
            evaList = evaDao.findAll(Evaluation.class);
        }
    }


       public void updateEvaluation()
{
       if(evadetails.getGrading1()>50 || evadetails.getGrading2()>50 || evadetails.getGrading3()>50){
       RequestContext context = RequestContext.getCurrentInstance();
    context.execute("swal('','Grade above 50 is not allowed!!','warning')");
    }
    else{
    evadetails.setContestant(finalConts());
evaDao.update(evadetails);
evadetails=new Evaluation();
evaList=evaDao.findAll(Evaluation.class);
      RequestContext context = RequestContext.getCurrentInstance();
      context.execute("swal('Congratulation','Updated!','success')");
       }
}

 public String fetchEvaluation2(final Evaluation eva) {
        this.evadetails = eva;
        return "UpEvaluation.xhtml";
    }

  public String fetchEvaluation3(final Evaluation eva) {
        this.evadetails = eva;
        return "UpEvaluation.xhtml";
    }

 
 public void deleteEvaluation() {
        evaDao.delete(this.evadetails);
        evaList=evaDao.findAll(Evaluation.class);
        FacesContext ct = FacesContext.getCurrentInstance();
        ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluation is well deleted", ""));
    }

 public void fetchAndShow(final Evaluation eva) {
        this.evadetails = eva;
        RequestContext.getCurrentInstance().execute("PF('delete').show()");
    }

 
          public void clearEvaluationDetails() {
        this.evadetails = null;
    }   

          
              public List<Evaluation> view(){
     List<Evaluation> list=new EvaluationDao().findAll(Evaluation.class);
     return list;
     }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Evaluation getEvadetails() {
        return evadetails;
    }

    public void setEvadetails(Evaluation evadetails) {
        this.evadetails = evadetails;
    }

    public EvaluationDao getEvaDao() {
        return evaDao;
    }

    public void setEvaDao(EvaluationDao evaDao) {
        this.evaDao = evaDao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List<Evaluation> getEvaList() {
        return evaList;
    }

    public void setEvaList(List<Evaluation> evaList) {
        this.evaList = evaList;
    }

    public List<Contestant> getContestant() {
        return contestant;
    }

    public void setContestant(List<Contestant> contestant) {
        this.contestant = contestant;
    }

    public List<Contestant> getConts() {
        return conts;
    }

    public void setConts(List<Contestant> conts) {
        this.conts = conts;
    }

    public String getContId() {
        return ContId;
    }

    public void setContId(String ContId) {
        this.ContId = ContId;
    }
       
    
}
