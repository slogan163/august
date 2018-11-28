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
    ELABORATION varchar(255),
    --
    primary key (ID)
);
