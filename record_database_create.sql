create table material (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), record_id bigint, uom_id bigint, primary key (id)) engine=MyISAM
create table notes (id bigint not null auto_increment, record_notes longtext, record_id bigint, primary key (id)) engine=MyISAM
create table project_type (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=MyISAM
create table record (id bigint not null auto_increment, building_type varchar(255), date_start varchar(255), date_today varchar(255), duration varchar(255), estimator varchar(255), image longblob, job_name varchar(255), labor varchar(255), work_scope varchar(255), notes_id bigint, primary key (id)) engine=MyISAM
create table record_project_type (record_id bigint not null, project_type_id bigint not null, primary key (record_id, project_type_id)) engine=MyISAM
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=MyISAM
alter table material add constraint FKak4vhsylr7kblioocgeogkfnw foreign key (record_id) references record (id)
alter table material add constraint FKb4qe1ei4x6qpau9iad2jsvrmw foreign key (uom_id) references unit_of_measure (id)
alter table notes add constraint FK2pa1s1y9tvrc3u6lqoen22pat foreign key (record_id) references record (id)
alter table record add constraint FK7jmu07752v178h3l22c47fimt foreign key (notes_id) references notes (id)
alter table record_project_type add constraint FKmbxnl0grnwwt38a3ir1q28kpy foreign key (project_type_id) references project_type (id)
alter table record_project_type add constraint FKkpr78vaj56ksrv66ux96h5f3m foreign key (record_id) references record (id)
