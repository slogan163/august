-- begin AUGUSTWOKSHOPS_MODEL
create table AUGUSTWOKSHOPS_MODEL (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    WORK_SHOP_ID varchar(36),
    --
    primary key (ID)
)^
-- end AUGUSTWOKSHOPS_MODEL
-- begin AUGUSTWOKSHOPS_OPERATION
create table AUGUSTWOKSHOPS_OPERATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ integer not null,
    DESCRIPTION longvarchar,
    EXECUTOR varchar(255),
    FACILITIES_AND_PLACE_OF_PERFOMANCE varchar(255),
    OPERATION_TIME_MIN integer,
    PARTY_TIME integer,
    MODEL_ID varchar(36),
    --
    primary key (ID)
)^
-- end AUGUSTWOKSHOPS_OPERATION
-- begin AUGUSTWOKSHOPS_EMPLOYEE
create table AUGUSTWOKSHOPS_EMPLOYEE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255) not null,
    WORK_SHOP_ID varchar(36),
    --
    primary key (ID)
)^
-- end AUGUSTWOKSHOPS_EMPLOYEE

-- begin AUGUSTWOKSHOPS_WORK_SHOP
create table AUGUSTWOKSHOPS_WORK_SHOP (
    ID varchar(36) not null,
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
-- begin AUGUSTWOKSHOPS_LABOR_INTENSITY
create table AUGUSTWOKSHOPS_LABOR_INTENSITY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DATE_ date not null,
    WORKSHOP_ID varchar(36),
    EMPLOYEE_ID varchar(36),
    MODEL_ID varchar(36),
    OPERATION_ID varchar(36),
    PARTY_COUNT integer,
    ELABORATION varchar(255),
    --
    primary key (ID)
)^
-- end AUGUSTWOKSHOPS_LABOR_INTENSITY
