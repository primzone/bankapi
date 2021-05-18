-- Users
insert into usr (id, name) values (1, 'Narek');
insert into usr (id, name) values (2, 'Roman');

--Contractor
insert into contractor(id, name) values (1, 'Kate');
insert into contractor(id, name) values (2, 'Viktor');

--Users_contractors
insert into users_contractors(user_id, contractor_id) VALUES (1,1);
insert into users_contractors(user_id, contractor_id) VALUES (1,2);

--Accounts
insert into account (id, account_number, balance, user_id )
VALUES (1, '64756938751523098125', 100000, 1);
insert into account (id, account_number, balance, user_id )
VALUES (2, '91381939012482346177', 45000, 1);
insert into account (id, account_number, balance, user_id )
VALUES (3, '24682746248298534909', 34000, 2);
insert into account (id, account_number, balance, contractor_id )--счет для контрагента
VALUES (4, '34588534289090230111', 190000, 1);

--Cards
insert into card(id, card_number, confirmation, account_id)
VALUES (1, '6743 8237 9032 8734', true, 1);
insert into card(id, card_number, confirmation, account_id)
VALUES (2, '2341 9832 4367 7777', true, 1);
insert into card(id, card_number, confirmation, account_id)--карта для контрагента
VALUES (3, '4322 6543 6436 1309', true, 4);


