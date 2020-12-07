/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import dao.ContestantDao;
import dao.Errors;
import dao.LoginDao;
import domain.Contestant;
import domain.Login;
import java.util.ArrayList;
import java.util.List;
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
public class ContestantModel {

    private Login login = new Login();
    LoginDao loginDao = new LoginDao();

    private Contestant contestant = new Contestant();
    private Contestant contdetails = new Contestant();
    private ContestantDao contDao = new ContestantDao();
    private String id = new String();
    private String searchKey = new String();
    private List<Contestant> contList;

    public ContestantModel() {
        
        contList = new ContestantDao().findAll();
    }

    public List<Contestant> getCont() {
        return contDao.findAll();
    }

    public void createContestant() {

        login.setPassword(login.getPassword());
        login.setUserType("Contestant");
        loginDao = new LoginDao();
        loginDao.save(login);
        contestant.setLogin(login);
        contDao = new ContestantDao();
        contDao.save(contestant);

        contestant = new Contestant();
        login = new Login();
        contList = new ContestantDao().findAll();
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully registed", null));

    }

    public List<Contestant> view() {
        List<Contestant> list = new ContestantDao().findAll();
        return list;
    }

    public void updateContestant() throws Errors {

        contDao.update(contdetails);
        contdetails = new Contestant();
        contList = contDao.findAll();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("swal('Congratulation','Updated!','success')");

    }

    public String fetchContestant(final Contestant cont) {
        this.contdetails = cont;
        return "editContestant.xhtml?faces-redirect=true";
    }

    public void deleteContestant() {
        contDao.delete(this.contdetails);
        contList = contDao.findAll();
        FacesContext ct = FacesContext.getCurrentInstance();
        ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contestant is well deleted", ""));
    }

    public void fetchAndShow(final Contestant cont) {
        this.contdetails = cont;
        RequestContext.getCurrentInstance().execute("PF('delete').show()");
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

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public Contestant getContdetails() {
        return contdetails;
    }

    public void setContdetails(Contestant contdetails) {
        this.contdetails = contdetails;
    }

    public ContestantDao getContDao() {
        return contDao;
    }

    public void setContDao(ContestantDao contDao) {
        this.contDao = contDao;
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

    public List<Contestant> getContList() {
        return contList;
    }

    public void setContList(List<Contestant> contList) {
        this.contList = contList;
    }

}
