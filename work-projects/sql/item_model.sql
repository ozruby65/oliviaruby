-- Table: ying_rooms.item_model

-- DROP TABLE IF EXISTS ying_rooms.item_model;

CREATE TABLE IF NOT EXISTS ying_rooms.item_model
(
    model_id integer NOT NULL DEFAULT nextval('ying_rooms.item_model_model_id_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default",
    category character varying(50) COLLATE pg_catalog."default",
    style character varying(50) COLLATE pg_catalog."default",
    color character varying(50) COLLATE pg_catalog."default",
    default_price numeric(10,2),
    description text COLLATE pg_catalog."default",
    CONSTRAINT item_model_pkey PRIMARY KEY (model_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS ying_rooms.item_model
    OWNER to postgres;