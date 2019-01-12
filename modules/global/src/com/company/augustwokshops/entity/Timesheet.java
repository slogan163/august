package com.company.augustwokshops.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;

import java.util.HashMap;
import java.util.Map;
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

    @MetaProperty(mandatory = true)
    protected String caption;

    @MetaProperty
    protected UUID uuid;

    @MetaProperty
    protected Map<String, Double> lastNameHoursMap = new HashMap<>();

    @MetaProperty
    protected Double elaborationSum;

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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Map<String, Double> getLastNameHoursMap() {
        return lastNameHoursMap;
    }

    public void setLastNameHoursMap(Map<String, Double> lastNameHoursMap) {
        this.lastNameHoursMap = lastNameHoursMap;
    }

    public Double getElaborationSum() {
        return elaborationSum;
    }

    public void setElaborationSum(Double elaborationSum) {
        this.elaborationSum = elaborationSum;
    }
}