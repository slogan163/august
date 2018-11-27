update AUGUSTWOKSHOPS_OPERATION set OPERATION_TIME_MIN = 0 where OPERATION_TIME_MIN is null ;
alter table AUGUSTWOKSHOPS_OPERATION alter column OPERATION_TIME_MIN set not null ;
update AUGUSTWOKSHOPS_OPERATION set PARTY_TIME = 0 where PARTY_TIME is null ;
alter table AUGUSTWOKSHOPS_OPERATION alter column PARTY_TIME set not null ;
-- update AUGUSTWOKSHOPS_OPERATION set MODEL_ID = <default_value> where MODEL_ID is null ;
alter table AUGUSTWOKSHOPS_OPERATION alter column MODEL_ID set not null ;
