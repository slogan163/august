package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.DateUtils;
import com.company.augustwokshops.entity.Employee;
import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.Timesheet;
import com.company.augustwokshops.entity.WorkShop;
import com.company.augustwokshops.web.laborintensity.datasources.LaborIntensitiesDs;
import com.company.augustwokshops.web.laborintensity.datasources.TimesheetsDs;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.*;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class LaborIntensityBrowse extends AbstractLookup {

    public static final String ELABORATION = "Выработка, час";
    public static final String FOND_FACT = "Фонд факт.";

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
            timesheetsDs.refresh(ParamsMap.of("items", laborIntensitiesDs.getItems(),
                    "date", monthPicker.getValue()));
            if (workshopLookup.getValue() != null) {
                addColumns();
            }
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
                .collect(toList());
        removedColumns.forEach(c -> timesheetGrid.removeColumn(c));
    }

    private void addColumns() {
        addLatNamesColumns();
        addColumn(ELABORATION);
    }

    protected void addLatNamesColumns() {
        for (String lastName : getLastNames()) {
            addColumn(lastName);
        }
    }

    protected void addColumn(String columnName) {
        timesheetGrid.addGeneratedColumn(columnName, new DataGrid.ColumnGenerator<Timesheet, Double>() {
            @Override
            public Double getValue(DataGrid.ColumnGeneratorEvent<Timesheet> event) {
                String rowName = event.getItem().getId();

                switch (columnName) {
                    case ELABORATION:
                        return calculateElaborationColumn(rowName);
                    default:
                        return calculateLastNameColumn(columnName, rowName);
                }
            }

            @Override
            public Class<Double> getType() {
                return Double.class;
            }
        });
    }

    private Double calculateElaborationColumn(String rowName) {
        if (FOND_FACT.equals(rowName)) {
            return laborIntensitiesDs.getItems().stream()
                    .mapToDouble(LaborIntensity::getTotalMin)
                    .sum() / 60;
        }

        Integer date = Integer.valueOf(rowName);
        return laborIntensitiesDs.getItems().stream()
                .filter(i -> date.equals(DateUtils.getDayOfMonth(i.getDate())))
                .mapToDouble(LaborIntensity::getTotalMin)
                .sum() / 60;
    }

    private Double calculateLastNameColumn(String lastName, String rowName) {
        if (FOND_FACT.equals(rowName)) {
            return laborIntensitiesDs.getItems().stream()
                    .filter(i -> lastName.equals(i.getEmployee().getLastName()))
                    .mapToDouble(LaborIntensity::getTotalMin).sum() / 60;
        }

        Integer date = Integer.valueOf(rowName);
        return laborIntensitiesDs.getItems().stream()
                .filter(i -> date.equals(DateUtils.getDayOfMonth(i.getDate())))
                .filter(i -> lastName.equals(i.getEmployee().getLastName()))
                .mapToDouble(LaborIntensity::getTotalMin)
                .sum() / 60;
    }

    protected List<String> getLastNames() {
        return Optional.ofNullable(workshopLookup.getValue())
                .map(w -> ((WorkShop) w).getEmploees().stream().map(Employee::getLastName).sorted().collect(toList()))
                .orElse(Collections.emptyList());
    }
}