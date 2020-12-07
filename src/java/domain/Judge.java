/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;

/**
 *
 * @author pc
 */
@Entity
public class Judge {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer JudgeId;
    private String FirstName;
    private String LastName;
    private String Phone;
    private String Email;
    @ManyToOne
    private Login login;
//    @OneToMany(mappedBy = "judge", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)

    public Integer getJudgeId() {
        return JudgeId;
    }

    public void setJudgeId(Integer JudgeId) {
        this.JudgeId = JudgeId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }


    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
