package com.company.augustwokshops.web.laborintensity.datasources;

import com.company.augustwokshops.DateUtils;
import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.WorkShop;
import com.haulmont.cuba.core.global.DataManager;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface LabourIntensityLoader {

    default Collection<LaborIntensity> getEntities(DataManager dataManager, Map<String, Object> params) {
        WorkShop workshop = (WorkShop) params.get("workshop");
        LocalDate localDate = DateUtils.localDate((Date) params.getOrDefault("date", new Date()));

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
