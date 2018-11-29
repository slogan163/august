package com.company.augustwokshops.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import java.util.UUID;
import javax.persistence.Id;
import com.haulmont.cuba.core.entity.BaseStringIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;

@MetaClass(name = "augustwokshops$Timesheet")
public class Timesheet extends BaseStringIdEntity implements HasUuid {
    private static final long serialVersionUID = 4714367230810096110L;

    @Id
    @MetaProperty(mandatory = true)
    protected String id;

    @MetaProperty
    protected Integer date;

    @MetaProperty
    protected Double elaboration;

    @MetaProperty
    protected Integer mans;

    @MetaProperty
    protected Double plannedElaboration;

    @MetaProperty
    protected UUID uuid;

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getDate() {
        return date;
    }

    public void setElaboration(Double elaboration) {
        this.elaboration = elaboration;
    }

    public Double getElaboration() {
        return elaboration;
    }

    public void setMans(Integer mans) {
        this.mans = mans;
    }

    public Integer getMans() {
        return mans;
    }

    public void setPlannedElaboration(Double plannedElaboration) {
        this.plannedElaboration = plannedElaboration;
    }

    public Double getPlannedElaboration() {
        return plannedElaboration;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }


}