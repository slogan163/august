package com.company.augustwokshops.web.laborintensity.datasources;

import com.company.augustwokshops.DateUtils;
import com.company.augustwokshops.entity.Employee;
import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.Timesheet;
import com.company.augustwokshops.entity.WorkShop;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.data.impl.CustomCollectionDatasource;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.emptyCollection;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class TimesheetsDs extends CustomCollectionDatasource<Timesheet, String> {

    public static final String FOND_FACT_ROW = "fondFact";

    private Metadata metadata = AppBeans.get(Metadata.class);

    protected Collection<LaborIntensity> laborIntensities = new ArrayList<>();
    protected WorkShop workShop = null;

    @Override
    protected Collection<Timesheet> getEntities(Map<String, Object> params) {
        laborIntensities = (Collection<LaborIntensity>) params.get("items");
        workShop = (WorkShop) params.get("workshop");

        if (isEmpty(laborIntensities) || workShop == null) {
            return emptyCollection();
        }

        LocalDate localDate = DateUtils.localDate((Date) params.getOrDefault("date", new Date()));
        return createTimesheets(localDate, laborIntensities, workShop);
    }

    public List<String> getLastNames() {
        return Optional.ofNullable(workShop)
                .map(w -> w.getEmploees().stream().map(Employee::getLastName).sorted().collect(toList()))
                .orElse(Collections.emptyList());
    }

    private List<Timesheet> createTimesheets(LocalDate localDate, Collection<LaborIntensity> list, WorkShop workShop) {
        List<Timesheet> timesheets = new ArrayList<>();

        for (int i = 1; i <= localDate.lengthOfMonth(); i++) {
            Timesheet timesheet = createDaysTimesheet(i);
            timesheets.add(timesheet);
        }

        Timesheet fond = createFondTimesheet();
        timesheets.add(fond);

        return timesheets;
    }

    protected Timesheet createDaysTimesheet(Integer day) {
        Timesheet timesheet = metadata.create(Timesheet.class);
        String caption = String.valueOf(day);
        timesheet.setId(caption);
        timesheet.setCaption(caption);

        for (String lastName : getLastNames()) {
            timesheet.getLastNameHoursMap().put(lastName, calculateDaysHours(day, lastName));
        }
        timesheet.setElaborationSum(calculateElaborationSum(day));

        return timesheet;
    }

    protected Timesheet createFondTimesheet() {
        Timesheet timesheet = metadata.create(Timesheet.class);
        timesheet.setId(FOND_FACT_ROW);
        timesheet.setCaption("Фонд факт.");

        for (String lastName : getLastNames()) {
            timesheet.getLastNameHoursMap().put(lastName, calculateFondHours(lastName));
        }
        timesheet.setElaborationSum(calculateAllSum());

        return timesheet;
    }

    private Double calculateAllSum() {
        return laborIntensities.stream()
                .mapToDouble(LaborIntensity::getTotalMin)
                .sum() / 60;
    }

    private Double calculateFondHours(String lastName) {
        return laborIntensities.stream()
                .filter(i -> lastName.equals(i.getEmployee().getLastName()))
                .mapToDouble(LaborIntensity::getTotalMin).sum() / 60;
    }

    private Double calculateElaborationSum(Integer day) {
        return laborIntensities.stream()
                .filter(i -> day.equals(DateUtils.getDayOfMonth(i.getDate())))
                .mapToDouble(LaborIntensity::getTotalMin)
                .sum() / 60;
    }

    private Double calculateDaysHours(Integer day, String lastName) {
        return laborIntensities.stream()
                .filter(i -> day.equals(DateUtils.getDayOfMonth(i.getDate())))
                .filter(i -> lastName.equals(i.getEmployee().getLastName()))
                .mapToDouble(LaborIntensity::getTotalMin)
                .sum() / 60;
    }
}
