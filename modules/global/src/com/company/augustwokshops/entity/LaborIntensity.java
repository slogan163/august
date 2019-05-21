package com.company.augustwokshops.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.global.PersistenceHelper;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamePattern("%s|employee")
@Table(name = "AUGUSTWOKSHOPS_LABOR_INTENSITY")
@Entity(name = "augustwokshops$LaborIntensity")
public class LaborIntensity extends StandardEntity {
    private static final long serialVersionUID = 7801935062501752580L;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_", nullable = false)
    protected Date date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EMPLOYEE_ID")
    protected Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OPERATION_ID")
    protected Operation operation;

    @NotNull
    @Column(name = "PARTY_COUNT", nullable = false)
    protected Integer partyCount = 0;

    @Column(name = "ELABORATION")
    protected Double elaboration = 0d;

    @Lob
    @Column(name = "COMMENTS")
    protected String comments;

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public Double getElaboration() {
        return elaboration != null ? elaboration : 0d;
    }

    public void setElaboration(Double elaboration) {
        this.elaboration = elaboration;
    }

    @MetaProperty(related = "operation")
    public Double getTotalMin() {
        if (!PersistenceHelper.isLoaded(this, "operation") || operation == null) {
            return 0d;
        }

        double partyAllTimeMin = (double) operation.getOperationTimeSec() * partyCount / 60;
        return partyAllTimeMin == 0d ? 0d : partyAllTimeMin + operation.getPartyTimeMin() + getElaboration();
    }

    @Nullable
    @MetaProperty(mandatory = true, related = "operation")
    public WorkShop getWorkShop() {
        Model model = getModel();

        if (model == null || !PersistenceHelper.isLoaded(model, "workShop")) {
            return null;
        }

        return model.getWorkShop();
    }

    @Nullable
    @MetaProperty(mandatory = true, related = "operation")
    public Model getModel() {
        if (!PersistenceHelper.isLoaded(this, "operation") || operation == null ||
                !PersistenceHelper.isLoaded(operation, "model")) {
            return null;
        }

        return operation.getModel();
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