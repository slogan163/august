package com.company.augustwokshops.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamePattern("%s|name")
@Table(name = "AUGUSTWOKSHOPS_MODEL")
@Entity(name = "augustwokshops$Model")
public class Model extends StandardEntity {
    private static final long serialVersionUID = 5870291468782025866L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "model")
    @OrderBy("number")
    protected List<Operation> operations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_SHOP_ID")
    protected WorkShop workShop;

    public void setWorkShop(WorkShop workShop) {
        this.workShop = workShop;
    }

    public WorkShop getWorkShop() {
        return workShop;
    }


    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Operation> getOperations() {
        return operations;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}