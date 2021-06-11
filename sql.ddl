/* table account */
create table account
(
    id integer not null
        constraint account_pk
            primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

alter table account owner to testuser;

insert into account values (1, 'keesun', 'pass');

select * from account;