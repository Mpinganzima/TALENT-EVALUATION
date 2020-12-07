package Model;

import dao.GenericDao;
import dao.LoginDao;
import domain.Login;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private Login log = new Login();
    private List<Login> logList = new GenericDao().findAll(Login.class);
    private String uname = new String();
    private String psd = new String();

    public String searchingCredentials() throws IOException {
        String msg = "";
        HttpSession session = SessionUtils.getSession();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Login us = checkUser(psd, uname);
        Optional<Login> op = Optional.ofNullable(log);
        if(session.getAttribute("userSession")!=null) {	
		session.invalidate();	
	}
        if (op.isPresent()) {
            if (us.getUserType().equals("Host")) {
                //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", log);
                session.setAttribute("userSession", us);
                ec.redirect(ec.getRequestContextPath() + "/faces/Dashboard/examples/Index.xhtml");
                return "faces/Dashboard/examples/Index.xhtml?faces-redirect=true";
            } else if (us.getUserType().equals("Judge1")) {

               // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", log);
            session.setAttribute("userSession", us);
                ec.redirect(ec.getRequestContextPath() + "/faces/Dashboard/examples/Final.xhtml");
                return "faces/Dashboard/Dashboard/examples/homepage.xhtml?faces-redirect=true";
            } 
            else if (us.getUserType().equals("Contestant")) {
                

                //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", log);
                session.setAttribute("userSession", us);
                ec.redirect(ec.getRequestContextPath() + "/faces/Dashboard/examples/ContestantResults.xhtml");
                return "faces/Dashboard/Dashboard/examples/homepage.xhtml?faces-redirect=true";
            }else if (us.getUserType().equals("Admin")) {
                session.setAttribute("userSession", us);
               // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", log);
                ec.redirect(ec.getRequestContextPath() + "/faces/admin/Contestants.xhtml");
                return "faces/Dashboard/Dashboard/examples/homepage.xhtm?faces-redirect=true";
                 
            } else {
               // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", log);
                ec.redirect(ec.getRequestContextPath() + "/faces/admin/Contestants.xhtml");
                return "faces/Dashboard/examples/homepage.xhtml?faces-redirect=true";
            }
        } else {
            ec.redirect(ec.getRequestContextPath() + "/faces/Admin/homepage.xhtml");
        }
        return msg;
    }

    public Login checkUser(String psw, String un) {
        List<Login> u = new LoginDao().login(psw, un);
        if (!u.isEmpty()) {
            return u.get(0);
        } else {
            return null;
        }
    }

    public Login getLog() {
        return log;
    }

    public void setLog(Login log) {
        this.log = log;
    }

    public List<Login> getLogList() {
        return logList;
    }

    public void setLogList(List<Login> logList) {
        this.logList = logList;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public void login() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("swal('Login Success!','You are Logged in!','Success')");
    }

    public void logout() {
 FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
          ExternalContext ec = context.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/Homepage/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
