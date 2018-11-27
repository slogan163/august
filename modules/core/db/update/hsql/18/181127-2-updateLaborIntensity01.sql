-- update AUGUSTWOKSHOPS_LABOR_INTENSITY set EMPLOYEE_ID = <default_value> where EMPLOYEE_ID is null ;
alter table AUGUSTWOKSHOPS_LABOR_INTENSITY alter column EMPLOYEE_ID set not null ;
-- update AUGUSTWOKSHOPS_LABOR_INTENSITY set OPERATION_ID = <default_value> where OPERATION_ID is null ;
alter table AUGUSTWOKSHOPS_LABOR_INTENSITY alter column OPERATION_ID set not null ;
update AUGUSTWOKSHOPS_LABOR_INTENSITY set PARTY_COUNT = 0 where PARTY_COUNT is null ;
alter table AUGUSTWOKSHOPS_LABOR_INTENSITY alter column PARTY_COUNT set not null ;
