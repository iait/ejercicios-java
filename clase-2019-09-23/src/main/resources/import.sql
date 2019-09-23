create table provincias (
    id int not null,
    nombre varchar(200) not null,
    primary key(id)
);

create table localidades (
    id int not null,
    nombre varchar(200) not null,
    provincia_id int not null,
    primary key(id)
);

insert into provincias (id, nombre) values
(1, 'Santa Fe');

insert into localidades (id, nombre, provincia_id) values
(1, 'Rosario', 1);
