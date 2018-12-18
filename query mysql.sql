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

select * from animales


select animal0_.cod_animal as cod_anim1_0_0_, animal0_.estado as estado2_0_0_, animal0_.fecha_nacimiento as fecha_na3_0_0_, animal0_.foto as foto4_0_0_, animal0_.cod_madre as cod_madr9_0_0_, animal0_.metodo_concep as metodo_c5_0_0_, animal0_.nombre as nombre6_0_0_, animal0_.padrillo_cod_padrillo as padrill10_0_0_, animal0_.raza as raza7_0_0_, animal0_.sexo as sexo8_0_0_ from animales animal0_ 
where animal0_.cod_animal=?


select animal0_.cod_animal as cod_anim1_0_, animal0_.estado as estado2_0_, animal0_.fecha_nacimiento as fecha_na3_0_, animal0_.foto as foto4_0_, animal0_.cod_madre as cod_madr9_0_, animal0_.metodo_concep as metodo_c5_0_, animal0_.nombre as nombre6_0_, animal0_.padrillo_cod_padrillo as padrill10_0_, animal0_.raza as raza7_0_, animal0_.sexo as sexo8_0_ 
from animales animal0_ where animal0_.cod_animal=1

SELECT DATEDIFF('2017-1-15', '2016-12-31');



