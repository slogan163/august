update AUGUSTWOKSHOPS_EMPLOYEE set FIRST_NAME = '' where FIRST_NAME is null ;
alter table AUGUSTWOKSHOPS_EMPLOYEE alter column FIRST_NAME set not null ;
