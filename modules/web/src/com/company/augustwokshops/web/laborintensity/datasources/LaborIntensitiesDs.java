package com.company.augustwokshops.web.laborintensity.datasources;

import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.WorkShop;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.data.impl.CustomGroupDatasource;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class LaborIntensitiesDs extends CustomGroupDatasource<LaborIntensity, UUID> implements LabourIntensityLoader {

    private DataManager dataManager = AppBeans.get(DataManager.class);

    @Override
    protected Collection<LaborIntensity> getEntities(Map<String, Object> params) {
        return getEntities(dataManager, params);
    }
}