package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.entity.*;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class LaborIntensityEdit extends AbstractEditor<LaborIntensity> {

    @Inject
    protected Datasource<LaborIntensity> laborIntensityDs;
    @Inject
    protected CollectionDatasource<WorkShop, UUID> workShopsDs;
    @Inject
    protected CollectionDatasource<Employee, UUID> emploeesDs;
    @Inject
    protected CollectionDatasource<Model, UUID> modelsDs;
    @Inject
    protected CollectionDatasource<Operation, UUID> operationsDs;
    @Inject
    protected LookupField workShopLookup;
    @Named("fieldGroup.date")
    private DateField dateField;
    @Inject
    protected LookupField employeeLookup;
    @Inject
    protected LookupField modelLookup;
    @Inject
    protected LookupField operationLookup;
    @Inject
    protected TextField operationTimeSecField;
    @Inject
    protected TextField partyTimeMinField;
    @Inject
    protected TextField totalMinField;
    @Inject
    protected Frame windowActions;
    @Inject
    private ComponentsFactory componentsFactory;

    @WindowParam(name = "workshop")
    private WorkShop workshop;
    @WindowParam(name = "date")
    private Date date;
    @WindowParam(name = "employee")
    private Employee employee;

    @Override
    public void ready() {
        LaborIntensity item = getItem();

        if (workshop != null) {
            workShopLookup.setValue(workShopsDs.getItems().stream().filter(i -> i.getId().equals(workshop.getId())).findAny().orElse(null));
            workShopLookup.setEditable(false);
        }

        calculateTotalMin();

        if (PersistenceHelper.isNew(item)) {
            if (date != null) {
                dateField.setValue(date);
            }
            if (employee != null) {
                employeeLookup.setValue(employee);
            }
        } else {
            workShopLookup.setValue(workShopsDs.getItems().stream().filter(i -> i.getId().equals(item.getWorkShop().getId())).findAny().orElse(null));
            modelLookup.setValue(modelsDs.getItems().stream().filter(i -> i.getId().equals(item.getModel().getId())).findAny().orElse(null));
        }

        workShopsDs.addItemChangeListener(e -> {
            if (!Objects.equals(e.getPrevItem(), e.getItem())) {
                modelLookup.setValue(null);
                employeeLookup.setValue(null);
                operationLookup.setValue(null);
            }
        });
        modelsDs.addItemChangeListener(e -> {
            if (!Objects.equals(e.getPrevItem(), e.getItem())) {
                operationLookup.setValue(null);
            }
        });
        operationsDs.addItemChangeListener(e -> {
            if (!Objects.equals(e.getPrevItem(), e.getItem())) {
                Operation operation = e.getItem();

                operationTimeSecField.setValue(operation != null ? operation.getOperationTimeSec() : 0);
                partyTimeMinField.setValue(operation != null ? operation.getPartyTimeMin() : 0);
            }
        });

        laborIntensityDs.addItemPropertyChangeListener(e -> calculateTotalMin());

        Button btn = componentsFactory.createComponent(Button.class);
        btn.setAction(new BaseAction("calculate").withCaption("Посчитать итого").withHandler(e -> calculateTotalMin()));
        ((Container) windowActions.getOwnComponents().iterator().next()).add(btn);
    }

    protected void calculateTotalMin() {
        totalMinField.setValue(getItem().getTotalMin());
    }
}