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
    DATE_ date,
    EMPLOYEE_ID varchar(36),
    MODEL_ID varchar(36),
    OPERATION_ID varchar(36),
    PARTY_COUNT integer,
    ELABORATION varchar(255),
    --
    primary key (ID)
);
