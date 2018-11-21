package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.WorkShop;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.data.impl.CustomGroupDatasource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LaborIntesityBrowseDs extends CustomGroupDatasource<LaborIntensity, UUID> {

    private DataManager dataManager = AppBeans.get(DataManager.class);

    @Override
    protected Collection<LaborIntensity> getEntities(Map<String, Object> params) {
        WorkShop workshop = params.get("workshop");
        Date date = params.getOrDefault("date", new Date());
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        StringBuilder query = new StringBuilder("select e from augustwokshops$LaborIntensity e ");
        query.append("where extract(YEAR from e.date) = :year and extract(MONTH from e.date) = :month ");

        Map<String, Object> map = new HashMap<>();
        map.put("year", localDate.getYearValue());
        map.put("month", localDate.getMonthValue());


        return null;
    }
}