create table provincias(
    id bigint not null,
    nombre varchar(200) not null,
    primary key(id)
);

insert into provincias values (1, 'Santa Fe');
insert into provincias values (2, 'Córdoba');
insert into provincias values (3, 'Buenos Aires');

create table localidades(
    id bigint not null,
    nombre varchar(200) not null,
    provincia_id bigint not null,
    primary key(id)
);

alter table localidades
add constraint FK_localidades_provincias FOREIGN KEY(provincia_id)
REFERENCES localidades (id);

insert into localidades values (1, 'Rosario', 1);
insert into localidades values (2, 'Granadero Baigorria', 1);
insert into localidades values (3, 'Córdoba', 2);
insert into localidades values (4, 'CABA', 3);