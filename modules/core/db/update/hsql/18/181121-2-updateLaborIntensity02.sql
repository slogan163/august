alter table AUGUSTWOKSHOPS_LABOR_INTENSITY add constraint FK_AUGUSTWOKSHOPS_LABOR_INTENSITY_ON_WORKSHOP foreign key (WORKSHOP_ID) references AUGUSTWOKSHOPS_WORK_SHOP(ID);
create index IDX_AUGUSTWOKSHOPS_LABOR_INTENSITY_ON_WORKSHOP on AUGUSTWOKSHOPS_LABOR_INTENSITY (WORKSHOP_ID);
