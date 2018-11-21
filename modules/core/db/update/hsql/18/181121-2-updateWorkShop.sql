alter table AUGUSTWOKSHOPS_WORK_SHOP alter column NUMBER_ rename to NUMBER___U29711 ^
alter table AUGUSTWOKSHOPS_WORK_SHOP add column NUMBER_ varchar(255) ^
update AUGUSTWOKSHOPS_WORK_SHOP set NUMBER_ = '' where NUMBER_ is null ;
alter table AUGUSTWOKSHOPS_WORK_SHOP alter column NUMBER_ set not null ;
