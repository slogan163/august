package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.Timesheet;
import com.company.augustwokshops.entity.WorkShop;
import com.company.augustwokshops.web.laborintensity.datasources.LaborIntensitiesDs;
import com.company.augustwokshops.web.laborintensity.datasources.TimesheetsDs;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

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
        monthPicker.addValueChangeListener(e -> refreshIntensitiesTable());
        workshopLookup.addValueChangeListener(e -> refreshIntensitiesTable());

        laborIntensitiesDs.addCollectionChangeListener(e -> {
            removeColumns();
            timesheetsDs.refresh(ParamsMap.of(
                    "workshop", workshopLookup.getValue(),
                    "items", laborIntensitiesDs.getItems(),
                    "date", monthPicker.getValue()));
            addLatNamesColumns();
        });
    }

    protected void refreshIntensitiesTable() {
        Date date = monthPicker.getValue();
        WorkShop workshop = workshopLookup.getValue();
        laborIntensitiesDs.refresh(ParamsMap.of("date", date, "workshop", workshop));
    }

    private void removeColumns() {
        List<DataGrid.Column> removedColumns = timesheetGrid.getColumns().stream()
                .filter(c -> !asList("caption", "elaborationSum").contains(c.getId()))
                .collect(toList());
        removedColumns.forEach(c -> timesheetGrid.removeColumn(c));
    }

    protected void addLatNamesColumns() {
        List<String> lastNames = timesheetsDs.getLastNames();
        for (int i = 0; i < lastNames.size(); i++) {
            addColumn(lastNames.get(i), i + 1);
        }
    }

    protected void addColumn(String lastName, int index) {
        timesheetGrid.addGeneratedColumn(lastName, new DataGrid.ColumnGenerator<Timesheet, Double>() {
            @Override
            public Double getValue(DataGrid.ColumnGeneratorEvent<Timesheet> event) {
                return event.getItem().getLastNameHoursMap().getOrDefault(lastName, 0d);
            }

            @Override
            public Class<Double> getType() {
                return Double.class;
            }
        }, index);
    }
}