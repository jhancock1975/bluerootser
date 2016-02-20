CREATE TABLE public.articles
(
-- Inherited from table basetable:  updated timestamp with time zone DEFAULT now(),
-- Inherited from table basetable:  created timestamp with time zone,
  id integer NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
  text text NOT NULL,
  link text NOT NULL,
  CONSTRAINT articles_pkey PRIMARY KEY (id)
)
INHERITS (public.basetable)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.articles
  OWNER TO john;

-- Table: public.user_roles

-- DROP TABLE public.user_roles;

CREATE TABLE public.URLs
(
-- Inherited from table basetable:  updated timestamp with time zone DEFAULT now(),
-- Inherited from table basetable:  created timestamp with time zone,
  id integer NOT NULL DEFAULT nextval('user_roles_id_seq'::regclass),
  url text,
  CONSTRAINT urls_pkey PRIMARY KEY (id),
  CONSTRAINT uni_url UNIQUE (url)
)
INHERITS (public.basetable)
WITH (
  OIDS=FALSE
);