create sequence hibernate_sequence start 1 increment 1;

create table account (
    id int8 not null,
     account_number varchar(255),
     balance float8 not null,
     contractor_id int8,
     user_id int8,
     primary key (id)
);

create table card (
    id int8 not null,
    card_number varchar(255),
    confirmation boolean not null,
    account_id int8,
    primary key (id)
);

create table contractor (
    id int8 not null,
     name varchar(255),
     primary key (id)
);

create table payment (
    id int8 not null,
     sender_account_number varchar(255),
    recipient_account_number varchar(255),
    account_id int8,
    transaction_id int8,
    primary key (id)
);

create table transaction (
    id int8 not null,
    amount float8 not null,
    confirmation boolean not null,
    recipient_account_number_id int8 not null,
    recipient_card_number varchar(255),
    sender_account_number_id int8 not null,
    sender_card_number varchar(255),
    transaction_number int8 not null,
    primary key (id)
);

create table users_contractors (
    user_id int8 not null,
    contractor_id int8 not null,
    primary key (contractor_id, user_id)
);

create table usr (
    id int8 not null,
    name varchar(255),
    primary key (id)
);


alter table if exists account
    add constraint UK_66gkcp94endmotfwb8r4ocxm9 unique (account_number);

alter table if exists account
    add constraint FK19gpofm6a1ys3iflxf6sx3b17
    foreign key (contractor_id) references contractor;

alter table if exists account
    add constraint FK28fcmglr2tde6x16oxmjor8ux
    foreign key (user_id) references usr;

alter table if exists card
    add constraint FK8v67eys6tqflsm6hrdgru2phu
    foreign key (account_id) references account;

alter table if exists payment
    add constraint FKqg9hvuj0jh5jkd3ejm3ibfn10
    foreign key (account_id) references account;

alter table if exists payment
    add constraint FK53qo12unt0o5flr83axs6v2i7
    foreign key (transaction_id) references transaction;

alter table if exists users_contractors
    add constraint FKebq30piqkfsg2eu4jf1jtv3dx
    foreign key (contractor_id) references contractor;

alter table if exists users_contractors
    add constraint FKpra6v64nv90thyey4sun5ycg7
    foreign key (user_id) references usr;