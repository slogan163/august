package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.entity.LaborIntensity;
import com.company.augustwokshops.entity.WorkShop;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.DatePicker;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import java.util.UUID;

public class LaborIntensityBrowse extends AbstractLookup {

    @Inject
    private LookupField groupByLookup;
    @Inject
    private GroupDatasource<LaborIntensity, UUID> laborIntensitiesDs;
    @Inject
    private CollectionDatasource<WorkShop, UUID> workShopsDs;
    @Inject
    private LookupField workshopLookup;
    @Inject
    private DatePicker monthPicker;
}