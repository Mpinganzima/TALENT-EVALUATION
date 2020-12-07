/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import dao.CompletedDao;
import dao.ContestantDao;
import dao.EvaluationDao;
import domain.Completed;
import domain.Contestant;
import domain.Evaluation;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class CompletedModel {
    
    private Completed completed = new Completed();
    private CompletedDao completedDao = new CompletedDao();
    private List<Completed> comList = new CompletedDao().findAll(Completed.class);
    List<Completed> compList = new ArrayList<>();
    
    private List<Contestant> contestant = new ContestantDao().findAll();
    private List<Contestant> conts = new ArrayList<>();
    private String ContId = new String();
    
    private List<Evaluation> evaluation = new EvaluationDao().findAll(Evaluation.class);
    private List<Evaluation> eval = new ArrayList<>();
    private String EvaId = new String();
    
    Integer dropCont;
    Integer dropEva;
    
    private Integer totalVotes = 0;
    private Double totalEvaluation1 = 0.0;
    private Double totalEvaluation2 = 0.0;
    private Double totalEvaluation3 = 0.0;
    private List<Contestant> contList;
    private EvaluationDao evaluationDao = new EvaluationDao();

    @PostConstruct
    public void init() {
        compList = new CompletedDao().findAll(Completed.class);
        conts = new ArrayList<>();
        for (Contestant co : contestant) {
            if (co.getFName() != null) {
                conts.add(co);
            }
        }
        
        eval = new ArrayList<>();
        for (Evaluation ev : evaluation) {
            if (ev.getEvaId() != null) {
                eval.add(ev);
            }
        }
        contList = new ContestantDao().findAll();
    }
    
    public void findByContestnat() {
        try {
            compList = new ArrayList<>();
            Contestant cont = new Contestant();
            List<Completed> ls = new CompletedDao().findAll(Completed.class);
            for (Completed u : ls) {
                if (u.getContestant().getContId().equals(dropCont)) {
                    compList.add(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void findByEvaluation() {
        try {
            compList = new ArrayList<>();
            Contestant cont = new Contestant();
            List<Completed> ls = new CompletedDao().findAll(Completed.class);
            for (Completed u : ls) {
                if (u.getContestant().getContId().equals(dropCont)) {
                    compList.add(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void fetchEvaluation() {
        
        Evaluation evalua = new EvaluationDao().findbyId(Evaluation.class, dropEva);
        totalEvaluation1 = evalua.getGrading1();
        totalEvaluation2 = evalua.getGrading2();
        totalEvaluation3 = evalua.getGrading3();
        System.out.println(totalEvaluation1);
        System.out.println(totalEvaluation2);
        System.out.println(totalEvaluation3);
    }
    
    public void createCompleted() {
        
        System.out.println(":::::::::::Here We are:::::::::::::::::::::");
        Contestant cont = new Contestant();
        cont.setContId(dropCont);
        completed.setContestant(cont);
        Evaluation eva = new Evaluation();
        //eva.setEvaId(dropEva);
        eva.setGr1(totalEvaluation1);
        eva.setGr2(totalEvaluation2);
        eva.setGr3(totalEvaluation3);
        eva.setContestant(cont);
        evaluationDao.save(eva);
        completed.setEvaluation(eva);
        completed.setEvaluationGrade1(totalEvaluation1);
        completed.setEvaluationGrade2(totalEvaluation2);
        completed.setEvaluationGrade3(totalEvaluation3);
        
        completedDao.save(completed);
        
        completed = new Completed();
        
        comList = new CompletedDao().findAll(Completed.class);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Saved!", null));
        
    }
    
    public Completed getCompleted() {
        return completed;
    }
    
    public void setCompleted(Completed completed) {
        this.completed = completed;
    }
    
    public CompletedDao getCompletedDao() {
        return completedDao;
    }
    
    public void setCompletedDao(CompletedDao completedDao) {
        this.completedDao = completedDao;
    }
    
    public List<Completed> getComList() {
        return comList;
    }
    
    public void setComList(List<Completed> comList) {
        this.comList = comList;
    }
    
    public List<Completed> getCompList() {
        return compList;
    }
    
    public void setCompList(List<Completed> compList) {
        this.compList = compList;
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
    
    public List<Evaluation> getEvaluation() {
        return evaluation;
    }
    
    public void setEvaluation(List<Evaluation> evaluation) {
        this.evaluation = evaluation;
    }
    
    public List<Evaluation> getEval() {
        return eval;
    }
    
    public void setEval(List<Evaluation> eval) {
        this.eval = eval;
    }
    
    public String getEvaId() {
        return EvaId;
    }
    
    public void setEvaId(String EvaId) {
        this.EvaId = EvaId;
    }
    
    public Integer getDropCont() {
        return dropCont;
    }
    
    public void setDropCont(Integer dropCont) {
        this.dropCont = dropCont;
    }
    
    public Integer getDropEva() {
        return dropEva;
    }
    
    public void setDropEva(Integer dropEva) {
        this.dropEva = dropEva;
    }
    
    public Double getTotalEvaluation1() {
        return totalEvaluation1;
    }
    
    public void setTotalEvaluation1(Double totalEvaluation1) {
        this.totalEvaluation1 = totalEvaluation1;
    }
    
    public Double getTotalEvaluation2() {
        return totalEvaluation2;
    }
    
    public void setTotalEvaluation2(Double totalEvaluation2) {
        this.totalEvaluation2 = totalEvaluation2;
    }
    
    public Double getTotalEvaluation3() {
        return totalEvaluation3;
    }
    
    public void setTotalEvaluation3(Double totalEvaluation3) {
        this.totalEvaluation3 = totalEvaluation3;
    }
    
    public Integer getTotalVotes() {
        return totalVotes;
    }
    
    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }
    
    public List<Contestant> getContList() {
        return contList;
    }
    
    public void setContList(List<Contestant> contList) {
        this.contList = contList;
    }
    
}
