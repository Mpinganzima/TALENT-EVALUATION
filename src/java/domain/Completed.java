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
import javax.persistence.OneToOne;

/**
 *
 * @author pc
 */
@Entity
public class Completed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @OneToOne
    private Contestant contestant;
    private Double VotedGrade;
    @OneToOne
    private Evaluation evaluation;
    private Double EvaluationGrade1;
    private Double EvaluationGrade2;
    private Double EvaluationGrade3;
    private Double TotalGrading;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Double getTotalGrading() {
        return TotalGrading;
    }

    public void setTotalGrading(Double TotalGrading) {
        this.TotalGrading = TotalGrading;
    }

    public Double getVotedGrade() {
        return VotedGrade;
    }

    public void setVotedGrade(Double VotedGrade) {
        this.VotedGrade = VotedGrade;
    }

    public Double getEvaluationGrade1() {
        return EvaluationGrade1;
    }

    public void setEvaluationGrade1(Double EvaluationGrade1) {
        this.EvaluationGrade1 = EvaluationGrade1;
    }

    public Double getEvaluationGrade2() {
        return EvaluationGrade2;
    }

    public void setEvaluationGrade2(Double EvaluationGrade2) {
        this.EvaluationGrade2 = EvaluationGrade2;
    }

    public Double getEvaluationGrade3() {
        return EvaluationGrade3;
    }

    public void setEvaluationGrade3(Double EvaluationGrade3) {
        this.EvaluationGrade3 = EvaluationGrade3;
    }


    
    
}
