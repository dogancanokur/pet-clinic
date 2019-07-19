CREATE TABLE public.t_owner
(
    id         bigint not null,
    first_name varchar(255),
    last_name  varchar(255)
);
CREATE TABLE public.t_pet
(
    id         bigint not null,
    name       varchar(255),
    birth_date date,
    owner_id   bigint
);

ALTER TABLE public.t_owner
    add constraint pk_constraint_owner primary key (id);

ALTER TABLE public.t_pet
    add constraint pk_constraint_pet primary key (id);

ALTER TABLE public.t_pet
    add constraint fk_constraint_pet foreign key (owner_id) REFERENCES public.t_owner (id);

create sequence public.petclinic_sequence start with 100;