create table basetable
( 
	updated timestamp with time zone default current_timestamp, 
	created timestamp with time zone
);

-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
-- Inherited from table basetable:  updated timestamp with time zone DEFAULT now(),
-- Inherited from table basetable:  created timestamp with time zone,
  username text NOT NULL,
  firstname text,
  lastname text,
  password text,
  enabled boolean,
  email text NOT NULL,
  dob date,
  CONSTRAINT users_pkey PRIMARY KEY (username)
)
INHERITS (public.basetable)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO john;

-- Index: public.dob_idx

-- DROP INDEX public.dob_idx;

CREATE INDEX dob_idx
  ON public.users
  USING btree
  (dob);



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
