update AUGUSTWOKSHOPS_EMPLOYEE set LAST_NAME = '' where LAST_NAME is null ;
alter table AUGUSTWOKSHOPS_EMPLOYEE alter column LAST_NAME set not null ;
