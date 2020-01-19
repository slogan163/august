package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.entity.Employee;
import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.Timesheet;
import com.company.augustwokshops.entity.WorkShop;
import com.company.augustwokshops.web.laborintensity.datasources.LaborIntensitiesDs;
import com.company.augustwokshops.web.laborintensity.datasources.TimesheetsDs;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.company.augustwokshops.web.laborintensity.datasources.TimesheetsDs.FOND_FACT_ROW;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class LaborIntensityBrowse extends AbstractLookup {

    @Inject
    protected LaborIntensitiesDs laborIntensitiesDs;
    @Inject
    private CollectionDatasource<WorkShop, UUID> workShopsDs;
    @Inject
    private CollectionDatasource<Employee, UUID> emploeesDs;
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
    @Inject
    private DateField patternDateField;
    @Named("laborIntensitiesTable.create")
    private CreateAction create;

    @Override
    public void init(Map<String, Object> params) {
        create.setWindowParamsSupplier(() -> ParamsMap.of("workshop", workShopsDs.getItem(),
                "date", patternDateField.getValue(), "employee", emploeesDs.getItem()));

        monthPicker.addValueChangeListener(e -> refreshIntensitiesTable());
        workshopLookup.addValueChangeListener(e -> {
            create.setEnabled(e.getValue() != null);
            refreshIntensitiesTable();
        });

        laborIntensitiesDs.addCollectionChangeListener(e -> {
            removeColumns();
            timesheetsDs.refresh(ParamsMap.of(
                    "workshop", workshopLookup.getValue(),
                    "items", laborIntensitiesDs.getItems(),
                    "date", monthPicker.getValue()));
            addLatNamesColumns();
        });

        timesheetGrid.addCellStyleProvider((timesheet, columnId) -> {
            if (isLastNameColumn(columnId) && !FOND_FACT_ROW.equals(timesheet.getId())) {
                Double cellElaboration = timesheet.getLastNameHoursMap().getOrDefault(columnId, 0d);
                return cellElaboration >= 7.4d ? "green" : null;
            }
            return null;
        });

        laborIntensitiesTable.addStyleProvider((intensity, columnId) -> {
            if ("totalMin".equals(columnId) && isOverWork(intensity)) {
                return "green";
            }
            return null;
        });
    }

    protected boolean isOverWork(LaborIntensity intensity) {
        final Double overWorkMin = 7.4d * 60;
        if (intensity.getTotalMin() >= overWorkMin) {
            return true;
        }

        Employee employee = intensity.getEmployee();
        Date date = intensity.getDate();

        double totalMin = laborIntensitiesDs.getItems().stream()
                .filter(i -> employee.equals(i.getEmployee()) && date.equals(i.getDate()))
                .mapToDouble(LaborIntensity::getTotalMin)
                .sum();

        return totalMin >= overWorkMin;
    }

    protected void refreshIntensitiesTable() {
        Date date = monthPicker.getValue();
        WorkShop workshop = workshopLookup.getValue();
        laborIntensitiesDs.refresh(ParamsMap.of("date", date, "workshop", workshop));
    }

    private void removeColumns() {
        List<DataGrid.Column> removedColumns = timesheetGrid.getColumns().stream()
                .filter(c -> isLastNameColumn(c.getId()))
                .collect(toList());
        removedColumns.forEach(c -> timesheetGrid.removeColumn(c));
    }

    protected void addLatNamesColumns() {
        List<String> lastNames = timesheetsDs.getLastNames();
        for (int i = 0; i < lastNames.size(); i++) {
            addColumn(lastNames.get(i), i + 1);
        }
    }

    protected boolean isLastNameColumn(String columnId) {
        return !asList("caption", "elaborationSum").contains(columnId);
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