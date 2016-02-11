create table basetable
( 
	updated timestamp with time zone default current_timestamp, 
	created timestamp with time zone
);

CREATE TABLE public.users
(
	username text NOT NULL,
	firstname text,
	lastname text,
	password text,
	email text, 
	enabled boolean,
	CONSTRAINT users_pkey PRIMARY KEY (username)
)
inherits (basetable);

create table roles (
	id serial, 
	role text unique, 
primary key(id)
)
inherits (basetable);

create table user_roles(id serial, 
	username text    constraint fk_username references users(username), 
	role text constraint fk_role references roles(role),
	constraint uni_username_role unique (role,username),
	primary key(id)
)
inherits(basetable);

/* population data*/

insert into roles (role, created) values ('users', current_timestamp);

insert into users (firstname, lastname, username, email, password, enabled, created) values ('john', 'hancock', 'jhancock', 'jhancock1975@gmail.com', 'password', true, current_timestamp);

insert into user_roles (username, role) values ('jhancock', 'users');
