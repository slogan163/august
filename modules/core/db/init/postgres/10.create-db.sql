-- begin AUGUSTWOKSHOPS_MODEL
create table AUGUSTWOKSHOPS_MODEL (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    WORK_SHOP_ID uuid,
    --
    primary key (ID)
)^
-- end AUGUSTWOKSHOPS_MODEL
-- begin AUGUSTWOKSHOPS_OPERATION
create table AUGUSTWOKSHOPS_OPERATION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255) not null,
    DESCRIPTION text,
    EXECUTOR varchar(255),
    OPERATION_TIME_MIN integer not null,
    PARTY_TIME integer not null,
    MODEL_ID uuid not null,
    --
    primary key (ID)
)^
-- end AUGUSTWOKSHOPS_OPERATION
-- begin AUGUSTWOKSHOPS_EMPLOYEE
create table AUGUSTWOKSHOPS_EMPLOYEE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(255) not null,
    LAST_NAME varchar(255) not null,
    WORK_SHOP_ID uuid,
    --
    primary key (ID)
)^
-- end AUGUSTWOKSHOPS_EMPLOYEE
-- begin AUGUSTWOKSHOPS_LABOR_INTENSITY
create table AUGUSTWOKSHOPS_LABOR_INTENSITY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DATE_ date not null,
    EMPLOYEE_ID uuid not null,
    OPERATION_ID uuid not null,
    PARTY_COUNT integer not null,
    ELABORATION double precision,
    COMMENTS text,
    --
    primary key (ID)
)^
-- end AUGUSTWOKSHOPS_LABOR_INTENSITY
-- begin AUGUSTWOKSHOPS_WORK_SHOP
create table AUGUSTWOKSHOPS_WORK_SHOP (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255) not null,
    --
    primary key (ID)
)^
-- end AUGUSTWOKSHOPS_WORK_SHOP
