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
ALTER TABLE public.users
  OWNER TO john;
