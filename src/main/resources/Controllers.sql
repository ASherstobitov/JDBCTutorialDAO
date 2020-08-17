--delete from roles where id ='3'
CREATE TABLE IF NOT EXISTS roles (
id serial primary key,
role varchar (5) not null
);
--
--insert into roles (id, role) values (default, 'admin')
--insert into roles (id, role) values (default, 'user')
--
--

CREATE TABLE IF NOT EXISTS users (
id bigserial  PRIMARY KEY,
login varchar(10) unique not null,
password varchar(10) unique not null,
role integer not null,
foreign key (role) references roles (id)
);

--insert into users (id, login, password, role) values (default, 'admin', '123', 1);
--insert into users (id, login, password, role) values (default, 'user', '1234', 2);
--
--update users set "password"='1234'where id=2;
--update users set "login"='user'where id=2;
--delete from users

insert into phone_models (id, "name") values (default, 'samsung')
returning id;
insert into phone_models (id, "name") values (default, 'iphone')
returning id;
insert into phone_models (id, "name") values (default, 'xaomi')
returning id;
insert into phone_models (id, "name") values (default, 'digma')
returning id;
