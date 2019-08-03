package com.company.augustwokshops.web.laborintensity.datasources;

import com.company.augustwokshops.DateUtils;
import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.WorkShop;
import com.haulmont.cuba.core.global.DataManager;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public interface LabourIntensityLoader {

    default Collection<LaborIntensity> getEntities(DataManager dataManager, Map<String, Object> params) {
        WorkShop workshop = (WorkShop) params.get("workshop");
        LocalDate localDate = DateUtils.localDate((Date) params.getOrDefault("date", new Date()));

        if (workshop == null) {
            return Collections.emptyList();
        }

        return dataManager.load(LaborIntensity.class)
                .query("select e from augustwokshops$LaborIntensity e " +
                        "where extract(YEAR from e.date) = :year and extract(MONTH from e.date) = :month " +
                        "and e.operation.model.workShop.id = :workshop " +
                        "order by e.date ")
                .parameter("year", localDate.getYear())
                .parameter("month", localDate.getMonthValue())
                .parameter("workshop", workshop)
                .view("laborIntensity-browser")
                .list();
    }
}
