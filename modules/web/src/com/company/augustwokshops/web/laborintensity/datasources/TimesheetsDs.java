package com.company.augustwokshops.web.laborintensity.datasources;

import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.Timesheet;
import com.haulmont.cuba.core.entity.KeyValueEntity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.data.impl.CustomGroupDatasource;
import org.apache.commons.lang3.tuple.Pair;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyCollection;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class TimesheetsDs extends CustomGroupDatasource<Timesheet, String> {

    private Metadata metadata = AppBeans.get(Metadata.class);

    @Override
    protected Collection<Timesheet> getEntities(Map<String, Object> params) {
        Collection<LaborIntensity> list = (Collection<LaborIntensity>) params.get("items");

        if (isEmpty(list)) {
            return emptyCollection();
        }

        Date date = (Date) params.getOrDefault("date", new Date());
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        return createTimesheets(localDate);


//        Map<Integer, List<LaborIntensity>> entesitiesByDay = entesitiesByDay(list);
//        List<KeyValueEntity> values = createIntensityMap(localDate, entesitiesByDay);
//        return values;
    }

    private List<Timesheet> createTimesheets(LocalDate localDate) {
        List<Timesheet> timesheets = new ArrayList<>();
        int daysInMonth = localDate.lengthOfMonth();

        for (int i = 1; i <= localDate.lengthOfMonth(); i++) {
            Timesheet timesheet = metadata.create(Timesheet.class);
            timesheet.setId(String.valueOf(i));
            timesheets.add(timesheet);
        }
        return timesheets;
    }

//    private List<KeyValueEntity> createIntensityMap(LocalDate localDate, Map<Integer, List<LaborIntensity>> entesitiesByDay) {
//        int daysInMonth = localDate.lengthOfMonth();
//        List<KeyValueEntity> values = new ArrayList<>();
//
//        for (int i = 1; i < daysInMonth + 1; i++) {
//            KeyValueEntity value = metadata.create(KeyValueEntity.class);
//            value.setIdName("id");
//            value.setId(i);
//            value.setValue("day", i, false);
//            value.se
//
//
//            values.add(value);
//        }
//
//        return values;
//    }

//    private Map<Integer, List<LaborIntensity>> entesitiesByDay(Collection<LaborIntensity> list) {
//        Map<Integer, List<LaborIntensity>> map = list.stream().collect(Collectors.groupingBy(i -> getDay(i.getDate())));
//
//        for (Map.Entry<Integer, List<LaborIntensity>> entry : map.entrySet()) {
//            entry.getValue().sort(Comparator.comparing(l -> l.getEmployee().getLastName()));
//        }
//
//        return map;
//    }
//
//    private Integer getDay(Date date) {
//        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        return localDate.getDayOfMonth();
//    }
}
