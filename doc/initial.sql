create table Students
(
	id BIGINT not null auto_increment,
	name VARCHAR(1024) not null,
	constraint Students_pk
		primary key (id)
);

