package com.company.augustwokshops.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamePattern("%s|number")
@Table(name = "AUGUSTWOKSHOPS_WORK_SHOP")
@Entity(name = "augustwokshops$WorkShop")
public class WorkShop extends StandardEntity {
    private static final long serialVersionUID = 1106932321535176594L;

    @NotNull
    @Column(name = "NUMBER_", nullable = false)
    protected String number;

    @OneToMany(mappedBy = "workShop")
    @OrderBy("lastName,firstName")
    protected List<Employee> emploees;

    @OneToMany(mappedBy = "workShop")
    @OrderBy("name")
    protected List<Model> models;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public void setModels(List<Model> models) {
        this.models = models;
    }

    public List<Model> getModels() {
        return models;
    }


    public void setEmploees(List<Employee> emploees) {
        this.emploees = emploees;
    }

    public List<Employee> getEmploees() {
        return emploees;
    }




}