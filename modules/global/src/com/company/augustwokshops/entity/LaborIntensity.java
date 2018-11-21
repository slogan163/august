package com.company.augustwokshops.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.validation.constraints.NotNull;

@NamePattern("%s|employee")
@Table(name = "AUGUSTWOKSHOPS_LABOR_INTENSITY")
@Entity(name = "augustwokshops$LaborIntensity")
public class LaborIntensity extends StandardEntity {
    private static final long serialVersionUID = 7801935062501752580L;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_", nullable = false)
    protected Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKSHOP_ID")
    protected WorkShop workshop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    protected Employee employee;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID")
    protected Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPERATION_ID")
    protected Operation operation;

    @Column(name = "PARTY_COUNT")
    protected Integer partyCount;

    @Column(name = "ELABORATION")
    protected String elaboration;

    public void setWorkshop(WorkShop workshop) {
        this.workshop = workshop;
    }

    public WorkShop getWorkshop() {
        return workshop;
    }


    @MetaProperty(related = "operation")
    public Double getTotalMin() {
        if (operation == null) {
            return 0d;
        }

        return new Double((double) (operation.operationTimeSec / 60 * partyCount + operation.partyTimeMin));
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }


    public void setElaboration(String elaboration) {
        this.elaboration = elaboration;
    }

    public String getElaboration() {
        return elaboration;
    }


    public void setPartyCount(Integer partyCount) {
        this.partyCount = partyCount;
    }

    public Integer getPartyCount() {
        return partyCount;
    }


    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }


}