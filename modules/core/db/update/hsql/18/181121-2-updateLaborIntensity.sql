update AUGUSTWOKSHOPS_LABOR_INTENSITY set DATE_ = current_date where DATE_ is null ;
alter table AUGUSTWOKSHOPS_LABOR_INTENSITY alter column DATE_ set not null ;
