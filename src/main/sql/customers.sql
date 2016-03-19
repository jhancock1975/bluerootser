-- Table: public.customers

-- DROP TABLE public.customers;

CREATE TABLE public.customers
(
  firstname text NOT NULL,
  lastname text NOT NULL,
  password text NOT NULL,
  dob timestamp without time zone NOT NULL,
  email text,
  userid text NOT NULL,
  CONSTRAINT customers_pkey PRIMARY KEY (userid, password)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.customers
  OWNER TO john;

-- Index: public.customers_dob_idx

-- DROP INDEX public.customers_dob_idx;

CREATE INDEX customers_dob_idx
  ON public.customers
  USING btree
  (dob);

