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
    NUMBER_ integer not null,
    DESCRIPTION text,
    EXECUTOR varchar(255),
    FACILITIES_AND_PLACE_OF_PERFOMANCE varchar(255),
    OPERATION_TIME_MIN integer not null,
    PARTY_TIME integer not null,
    MODEL_ID uuid not null,
    --
    primary key (ID)
);
