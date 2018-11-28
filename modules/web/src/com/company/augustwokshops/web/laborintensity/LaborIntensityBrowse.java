package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.WorkShop;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.*;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LaborIntensityBrowse extends AbstractLookup {

    @Inject
    protected LaborIntensitiesDs laborIntensitiesDs;
    @Inject
    protected GroupTable<LaborIntensity> laborIntensitiesTable;
    @Inject
    protected LookupField workshopLookup;
    @Inject
    protected DatePicker monthPicker;
    @Inject
    protected VBoxLayout timesheetTabBox;

    protected Layout box = null;
    Table table = new Table();

    @Override
    public void init(Map<String, Object> params) {
        monthPicker.addValueChangeListener(e -> refreshTable());
        workshopLookup.addValueChangeListener(e -> refreshTable());
        laborIntensitiesDs.addCollectionChangeListener(e -> refreshTimesheets(e.getItems()));

        box = timesheetTabBox.unwrap(Layout.class);
    }

    private void refreshTimesheets(List<LaborIntensity> items) {
        box.removeComponent(table);

//        table = new Table();
//        table.addGeneratedColumn("first", );

        box.addComponent(table);
    }

    protected void refreshTable() {
        Date date = monthPicker.getValue();
        WorkShop workshop = workshopLookup.getValue();
        laborIntensitiesDs.refresh(ParamsMap.of("date", date, "workshop", workshop));
    }
}