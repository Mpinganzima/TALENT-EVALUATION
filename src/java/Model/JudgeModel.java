/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import dao.JudgeDao;
import dao.LoginDao;
import domain.Judge;
import domain.Login;
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
public class JudgeModel {

    private Login login = new Login();
    LoginDao loginDao = new LoginDao();

    private Judge judge = new Judge();
    private Judge juddetails = new Judge();
    JudgeDao judDao = new JudgeDao();
    private String id = new String();
    private String searchKey = new String();
    private List<Judge> judList;

    @PostConstruct
    public void init() {
        judList = new JudgeDao().findAll(Judge.class);
    }

    public void createJudge() {
        login.setPassword(login.getPassword());
//        login.setUserType("Judge");
        loginDao = new LoginDao();
        loginDao.save(login);
        judge.setLogin(login);
        judDao.save(judge);
        judge = new Judge();
        login=new Login();
        judList = judDao.findAll(Judge.class);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfull registed", null));

    }

    public void search() {
        if (searchKey.length() > 0) {
            judList = new ArrayList<>();
            List<Judge> currnt = judDao.findAll(Judge.class);
            for (Judge judge : currnt) {
                if (judge.getJudgeId().toString().contains(searchKey)) {
                    judList.add(judge);
                }
            }
        } else {
            judList = judDao.findAll(Judge.class);
        }
    }

    public void updateJudge() {
        judDao.update(juddetails);
        juddetails = new Judge();
        judList = judDao.findAll(Judge.class);
        FacesContext ct = FacesContext.getCurrentInstance();
        ct.addMessage(null, new FacesMessage("Well updated"));
    }

    public String fetchJudge(final Judge jud) {
        this.juddetails = jud;
        return "editJudge.xhtml?faces-redirect=true";
    }

    public void deleteJudge() {
        judDao.delete(this.juddetails);
        judList = judDao.findAll(Judge.class);
        FacesContext ct = FacesContext.getCurrentInstance();
        ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Judge is well deleted", ""));
    }

    public void fetchAndShow(final Judge jud) {
        this.juddetails = jud;
        RequestContext.getCurrentInstance().execute("PF('delete').show()");
    }

    public void clearJudgeDetails() {
        this.juddetails = null;
    }

    public List<Judge> view() {
        List<Judge> list = new JudgeDao().findAll(Judge.class);
        return list;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public Judge getJuddetails() {
        return juddetails;
    }

    public void setJuddetails(Judge juddetails) {
        this.juddetails = juddetails;
    }

    public JudgeDao getJudDao() {
        return judDao;
    }

    public void setJudDao(JudgeDao judDao) {
        this.judDao = judDao;
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

    public List<Judge> getJudList() {
        return judList;
    }

    public void setJudList(List<Judge> judList) {
        this.judList = judList;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
    
    

}
