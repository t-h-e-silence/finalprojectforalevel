create table if not exists address
(
    id     integer not null
        constraint address_pkey
            primary key,
    city   varchar(255),
    number integer,
    street varchar(255)
);

alter table address
    owner to postgres;

create table if not exists contact
(
    id     integer not null
        constraint contact_pkey
            primary key,
    name   varchar(255),
    number numeric(19, 2),
    site   varchar(255)
);

alter table contact
    owner to postgres;

create table if not exists group_categories
(
    id   integer      not null
        constraint group_categories_pkey
            primary key,
    name varchar(255) not null
        constraint uk_8kts18sp6k56v8rhql3rwf0t6
            unique
);

alter table group_categories
    owner to postgres;

create table if not exists places
(
    id            integer      not null
        constraint places_pkey
            primary key,
    description   varchar(255),
    name          varchar(255) not null,
    close         time,
    open          time,
    address_id    integer
        constraint fkrdgni5qf89m7uqrbq6sylgp8j
            references address,
    categories_id integer
        constraint fkgfnd2n73h8hpnn9jie9x1wrwh
            references group_categories,
    contact_id    integer
        constraint fk8785sfnvoin1n9m18s8hrlo5j
            references contact
);

alter table places
    owner to postgres;

create table if not exists group_categories_categories
(
    place_category_id integer not null
        constraint fklwbvwjptn3nwqhshojxtylq20
            references group_categories,
    categories_id     integer not null
        constraint uk_3warnxggrwc10hg2eowdsee0n
            unique
        constraint fk8ukxc3oyd4nxcsshy6h4xtsg3
            references places,
    constraint group_categories_categories_pkey
        primary key (place_category_id, categories_id)
);

alter table group_categories_categories
    owner to postgres;

create table if not exists users
(
    chat_id bigint not null
        constraint users_pkey
            primary key
);

alter table users
    owner to postgres;

create table if not exists users_favorite
(
    user_profile_data_chat_id bigint  not null
        constraint fk5ysv0adc9qfy7oe0naw43hoqg
            references users,
    favorite_id               integer not null
        constraint fk93aoilujn6e9h5s7fj2rdro3c
            references places,
    constraint users_favorite_pkey
        primary key (user_profile_data_chat_id, favorite_id)
);

alter table users_favorite
    owner to postgres;

