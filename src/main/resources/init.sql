create database wallet;
# drop database wallet;
use wallet;

create table user
(
    id       int auto_increment primary key,
    username varchar(255) not null,
    password varchar(255) not null
);
alter table user add constraint username_constraint unique (username);

create table deposit
(
    id          int auto_increment primary key,
    user_id     int      not null,
    category_id int      not null,
    amount      double   not null,
    date        datetime not null,
    description varchar(3000),
    foreign key (user_id)
        references user (id),
    foreign key (category_id)
        references category (id)
);

create table withdraw
(
    id          int auto_increment primary key,
    user_id     int      not null,
    category_id int      not null,
    amount      double   not null,
    date        datetime not null,
    description varchar(3000),
    foreign key (user_id)
        references user (id),
    foreign key (category_id)
        references category (id)
);

create table category
(
    id   int auto_increment primary key,
    name nvarchar(255) not null
);
# drop table category;
# alter table category modify column name nvarchar(255) not null;

create table user_category
(
    id          int auto_increment primary key,
    user_id     int not null,
    category_id int not null,
    foreign key (user_id)
        references user (id),
    foreign key (category_id)
        references category (id)
);


