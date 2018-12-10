package com.company.augustwokshops.web.laborintensity.datasources;

import com.company.augustwokshops.DateUtils;
import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.Timesheet;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.data.impl.CustomCollectionDatasource;

import java.time.LocalDate;
import java.util.*;

import static com.company.augustwokshops.web.laborintensity.LaborIntensityBrowse.FOND_FACT;
import static org.apache.commons.collections4.CollectionUtils.emptyCollection;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class TimesheetsDs extends CustomCollectionDatasource<Timesheet, String> {

    private Metadata metadata = AppBeans.get(Metadata.class);

    @Override
    protected Collection<Timesheet> getEntities(Map<String, Object> params) {
        Collection<LaborIntensity> list = (Collection<LaborIntensity>) params.get("items");

        if (isEmpty(list)) {
            return emptyCollection();
        }

        LocalDate localDate = DateUtils.localDate((Date) params.getOrDefault("date", new Date()));
        return createTimesheets(localDate);
    }

    private List<Timesheet> createTimesheets(LocalDate localDate) {
        List<Timesheet> timesheets = new ArrayList<>();

        for (int i = 1; i <= localDate.lengthOfMonth(); i++) {
            Timesheet timesheet = metadata.create(Timesheet.class);
            timesheet.setId(String.valueOf(i));
            timesheets.add(timesheet);
        }

        Timesheet fond = metadata.create(Timesheet.class);
        fond.setId(FOND_FACT);
        timesheets.add(fond);

        return timesheets;
    }
}
