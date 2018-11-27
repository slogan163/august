package com.company.augustwokshops.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@NamePattern("%s|lastName")
@Table(name = "AUGUSTWOKSHOPS_EMPLOYEE")
@Entity(name = "augustwokshops$Employee")
public class Employee extends StandardEntity {
    private static final long serialVersionUID = 5571183584429966890L;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    protected String firstName;

    @NotNull
    @Column(name = "LAST_NAME", nullable = false)
    protected String lastName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_SHOP_ID")
    protected WorkShop workShop;

    public void setWorkShop(WorkShop workShop) {
        this.workShop = workShop;
    }

    public WorkShop getWorkShop() {
        return workShop;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }


}