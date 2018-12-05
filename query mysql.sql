use dbcontrol

select * from users


create database dbcontrol

create table users (id bigint not null auto_increment, username varchar(45) not null, password varchar(60) not null, enabled tinyint not null default 1, unique key (username), primary key (id)) engine=InnoDB;
create table authorities (id bigint not null auto_increment, user_id bigint not null, authority varchar(45) not null, unique key unique_user_id_authority (user_id, authority), primary key (id)) engine=InnoDB;
alter table authorities add constraint fk_authorities_user foreign key (user_id) references users (id);


/*creacion de usuario admin/12345*/
INSERT INTO users (username, password, enabled) VALUES('admin', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 1);

INSERT INTO authorities (users_id, authority) VALUES(1, 'ROLE_USER');
INSERT INTO authorities (users_id, authority) VALUES(1, 'ROLE_ADMIN');


SELECT CURDATE();

select * from turno

select * from users

select * from authorities




