package com.company.augustwokshops.web.laborintensity;

import com.company.augustwokshops.entity.*;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.web.gui.components.WebButton;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;
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
    @Inject
    protected LookupField employeeLookup;
    @Inject
    protected LookupField modelLookup;
    @Inject
    protected LookupField operationLookup;
    @Inject
    protected FieldGroup fieldGroup;
    @Inject
    protected TextField operationTimeSecField;
    @Inject
    protected TextField partyTimeMinField;
    @Inject
    protected TextField partyCountField;
    @Inject
    protected TextField totalMinField;
    @Inject
    protected Frame windowActions;

    @Override
    public void ready() {
        LaborIntensity item = getItem();
        calculateTotalMin();

        if (!PersistenceHelper.isNew(item)) {
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

        operationTimeSecField.addValueChangeListener(e -> calculateTotalMin());
        partyTimeMinField.addValueChangeListener(e -> calculateTotalMin());
        partyCountField.addValueChangeListener(e -> calculateTotalMin());

        WebButton btn = new WebButton();
        btn.setAction(new BaseAction("calculate").withCaption("Посчитать итого").withHandler(e -> calculateTotalMin()));
        ((Container) windowActions.getOwnComponents().iterator().next()).add(btn);
    }

    protected void calculateTotalMin() {
        int operationTimeSec = Optional.ofNullable((Integer) operationTimeSecField.getValue()).orElse(0);
        int partyTimeMin = Optional.ofNullable((Integer) partyTimeMinField.getValue()).orElse(0);
        int partyCount = Optional.ofNullable((Integer) partyCountField.getValue()).orElse(0);

        double partyAllTimeMin = (double) operationTimeSec * partyCount / 60;
        double totalMin = partyAllTimeMin == 0d ? 0d : partyAllTimeMin + partyTimeMin;

        totalMinField.setValue(totalMin);
    }
}