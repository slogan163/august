alter table AUGUSTWOKSHOPS_MODEL add constraint FK_AUGUSTWOKSHOPS_MODEL_ON_WORK_SHOP foreign key (WORK_SHOP_ID) references AUGUSTWOKSHOPS_WORK_SHOP(ID);
create index IDX_AUGUSTWOKSHOPS_MODEL_ON_WORK_SHOP on AUGUSTWOKSHOPS_MODEL (WORK_SHOP_ID);
