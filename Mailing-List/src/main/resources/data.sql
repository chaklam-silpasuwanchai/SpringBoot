insert into user (username, password, active, role, email) values ('chaklam', '$2y$12$2LA4/IzwsfoF.SFtdxwJIus48N6JwFzTdMwlrc9lXRHnA9EOBU7AS', true, 'ROLE_ADMIN', 'chaklam@ait.asia');
insert into user (username, password,  active, role, email) values ('john', '$2y$10$uwslZFS.czXR6VE7XzyBTuH.xGtdDmBBnioj2J/KZqJr0cHDEr3fa', true, 'ROLE_USER', 'john@ait.asia');

insert into mailing_list (title, type) values ('Public Data Science Thailand', 0);
insert into mailing_list (title, type) values ('Public Brain-Computer Interface Thailand', 0);
insert into mailing_list (title, type) values ('Some Private List', 1);

insert into subscribing_list (lid, uid) values (1, 1);
insert into subscribing_list (lid, uid) values (2, 2);
