package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.WorkShop;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.data.impl.CustomGroupDatasource;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class LaborIntensitiesDs extends CustomGroupDatasource<LaborIntensity, UUID> {

    private DataManager dataManager = AppBeans.get(DataManager.class);

    @Override
    protected Collection<LaborIntensity> getEntities(Map<String, Object> params) {
        WorkShop workshop = (WorkShop) params.get("workshop");
        Date date = (Date) params.getOrDefault("date", new Date());
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        StringBuilder query = new StringBuilder("select e from augustwokshops$LaborIntensity e ");
        query.append("where extract(YEAR from e.date) = :year and extract(MONTH from e.date) = :month ");

        Map<String, Object> map = new HashMap<>();
        map.put("year", localDate.getYear());
        map.put("month", localDate.getMonthValue());

        if (workshop != null) {
            query.append("and e.operation.model.workShop.id = :workshop ");
            map.put("workshop", workshop);
        }

        return dataManager.load(LaborIntensity.class)
                .query(query.toString())
                .setParameters(map)
                .view("laborIntensity-browser")
                .list();
    }
}