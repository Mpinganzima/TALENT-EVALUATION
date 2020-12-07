/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author pc
 */
@Entity
public class Evaluation {
@GeneratedValue(strategy = GenerationType.AUTO)
@Id
private Integer EvaId;
@ManyToOne
private Contestant contestant;
private Double Gr1,Gr2,Gr3;
private Double Grading1;
private Double Grading2;
private Double Grading3= new Double(0);


    public Integer getEvaId() {
        return EvaId;
    }

    public void setEvaId(Integer EvaId) {
        this.EvaId = EvaId;
    }

    public Double getGrading1() {
        return Grading1;
    }

    public void setGrading1(Double Grading1) {
        this.Grading1 = Grading1;
    }

    public Double getGrading2() {
        return Grading2;
    }

    public void setGrading2(Double Grading2) {
        this.Grading2 = Grading2;
    }

    public Double getGrading3() {
        return Grading3;
    }

    public void setGrading3(Double Grading3) {
        this.Grading3 = Grading3;
    }

   

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public Double getGr1() {
        return Gr1;
    }

    public void setGr1(Double Gr1) {
        this.Gr1 = Gr1;
    }

    public Double getGr2() {
        return Gr2;
    }

    public void setGr2(Double Gr2) {
        this.Gr2 = Gr2;
    }

    public Double getGr3() {
        return Gr3;
    }

    public void setGr3(Double Gr3) {
        this.Gr3 = Gr3;
    }

}
