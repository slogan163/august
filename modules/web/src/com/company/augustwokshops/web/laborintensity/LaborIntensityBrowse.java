package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.WorkShop;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.DatePicker;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.LookupField;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

public class LaborIntensityBrowse extends AbstractLookup {

    @Inject
    private LaborIntensitiesDs laborIntensitiesDs;
    @Inject
    protected GroupTable<LaborIntensity> laborIntensitiesTable;
    @Inject
    private LookupField workshopLookup;
    @Inject
    private DatePicker monthPicker;

    @Override
    public void init(Map<String, Object> params) {
        monthPicker.addValueChangeListener(e -> refreshTable());
        workshopLookup.addValueChangeListener(e -> refreshTable());
    }

    protected void refreshTable() {
        Date date = monthPicker.getValue();
        WorkShop workshop = workshopLookup.getValue();
        laborIntensitiesDs.refresh(ParamsMap.of("date", date, "workshop", workshop));
    }
}