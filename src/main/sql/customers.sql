-- Table: public.customers

-- DROP TABLE public.customers;

CREATE TABLE public.customers
(
  firstname text NOT NULL,
  lastname text NOT NULL,
  password text,
  dob timestamp without time zone NOT NULL,
  email text,
  CONSTRAINT customers_pkey PRIMARY KEY (firstname, lastname, dob)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.customers
  OWNER TO john;
