alter table AUGUSTWOKSHOPS_LABOR_INTENSITY add constraint FK_AUGUSTWOKSHOPS_LABOR_INTENSITY_ON_MODEL foreign key (MODEL_ID) references AUGUSTWOKSHOPS_MODEL(ID);
create index IDX_AUGUSTWOKSHOPS_LABOR_INTENSITY_ON_MODEL on AUGUSTWOKSHOPS_LABOR_INTENSITY (MODEL_ID);