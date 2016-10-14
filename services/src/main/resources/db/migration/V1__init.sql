create table user (
    id bigint not null auto_increment,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    nick_name varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);
