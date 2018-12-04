package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.DateUtils;
import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.Timesheet;
import com.company.augustwokshops.entity.WorkShop;
import com.company.augustwokshops.web.laborintensity.datasources.LaborIntensitiesDs;
import com.company.augustwokshops.web.laborintensity.datasources.TimesheetsDs;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.*;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LaborIntensityBrowse extends AbstractLookup {

    @Inject
    protected LaborIntensitiesDs laborIntensitiesDs;
    @Inject
    protected TimesheetsDs timesheetsDs;
    @Inject
    protected GroupTable<LaborIntensity> laborIntensitiesTable;
    @Inject
    protected LookupField workshopLookup;
    @Inject
    protected DatePicker monthPicker;
    @Inject
    protected DataGrid<Timesheet> timesheetGrid;

    @Override
    public void init(Map<String, Object> params) {
        monthPicker.addValueChangeListener(e -> refreshTable());
        workshopLookup.addValueChangeListener(e -> refreshTable());

        laborIntensitiesDs.addCollectionChangeListener(e -> {
            removeColumns();
            addColumns();
            timesheetsDs.refresh(
                    ParamsMap.of("items", laborIntensitiesDs.getItems(), "date", monthPicker.getValue()));
        });
    }

    protected void refreshTable() {
        Date date = monthPicker.getValue();
        WorkShop workshop = workshopLookup.getValue();
        laborIntensitiesDs.refresh(ParamsMap.of("date", date, "workshop", workshop));
    }

    private void removeColumns() {
        List<DataGrid.Column> removedColumns = timesheetGrid.getColumns().stream()
                .filter(c -> !c.getId().equals("id"))
                .collect(Collectors.toList());
        removedColumns.forEach(c -> timesheetGrid.removeColumn(c));
    }

    private void addColumns() {
        Map<String, List<LaborIntensity>> mapByLastName = getLastNames();

        mapByLastName.keySet().stream().sorted().forEach(lastName -> {

            timesheetGrid.addGeneratedColumn(lastName, new DataGrid.ColumnGenerator<Timesheet, Double>() {
                @Override
                public Double getValue(DataGrid.ColumnGeneratorEvent<Timesheet> event) {
                    Integer date = Integer.valueOf(event.getItem().getId());
                    LaborIntensity intensity = findByDate(date, mapByLastName.get(lastName));
                    return intensity == null ? 0d : intensity.getTotalMin();
                }

                @Override
                public Class<Double> getType() {
                    return Double.class;
                }
            });
        });
    }

    protected Map<String, List<LaborIntensity>> getLastNames() {
        return laborIntensitiesDs.getItems().stream()
                .collect(Collectors.groupingBy(i -> i.getEmployee().getLastName()));
    }

    @Nullable
    protected LaborIntensity findByDate(Integer date, List<LaborIntensity> intensities) {
        return intensities.stream()
                .filter(i -> date.equals(DateUtils.getDayOfMonth(i.getDate())))
                .findAny()
                .orElse(null);
    }
}