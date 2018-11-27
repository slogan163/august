package com.company.augustwokshops.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.Lob;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|number")
@Table(name = "AUGUSTWOKSHOPS_OPERATION")
@Entity(name = "augustwokshops$Operation")
public class Operation extends StandardEntity {
    private static final long serialVersionUID = 5562002850541867185L;

    @NotNull
    @Column(name = "NUMBER_", nullable = false)
    protected Integer number;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @Column(name = "EXECUTOR")
    protected String executor;

    @Column(name = "FACILITIES_AND_PLACE_OF_PERFOMANCE")
    protected String facilitiesAndPlaceOfPerfomance;

    @NotNull
    @Column(name = "OPERATION_TIME_MIN", nullable = false)
    protected Integer operationTimeSec = 0;

    @NotNull
    @Column(name = "PARTY_TIME", nullable = false)
    protected Integer partyTimeMin = 0;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MODEL_ID")
    protected Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }


    public void setPartyTimeMin(Integer partyTimeMin) {
        this.partyTimeMin = partyTimeMin;
    }

    public Integer getPartyTimeMin() {
        return partyTimeMin;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getExecutor() {
        return executor;
    }

    public void setOperationTimeSec(Integer operationTimeSec) {
        this.operationTimeSec = operationTimeSec;
    }

    public Integer getOperationTimeSec() {
        return operationTimeSec;
    }

    public void setFacilitiesAndPlaceOfPerfomance(String facilitiesAndPlaceOfPerfomance) {
        this.facilitiesAndPlaceOfPerfomance = facilitiesAndPlaceOfPerfomance;
    }

    public String getFacilitiesAndPlaceOfPerfomance() {
        return facilitiesAndPlaceOfPerfomance;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }



}