-- Table: ying_rooms.inventory_item

-- DROP TABLE IF EXISTS ying_rooms.inventory_item;

CREATE TABLE IF NOT EXISTS ying_rooms.inventory_item
(
    item_id integer NOT NULL DEFAULT nextval('ying_rooms.inventory_item_item_id_seq'::regclass),
    model_id integer,
    serial_number character varying(100) COLLATE pg_catalog."default",
    status character varying(50) COLLATE pg_catalog."default",
    condition character varying(50) COLLATE pg_catalog."default",
    location character varying(100) COLLATE pg_catalog."default",
    purchase_date date,
    purchase_price numeric(10,2),
    notes text COLLATE pg_catalog."default",
    CONSTRAINT inventory_item_pkey PRIMARY KEY (item_id),
    CONSTRAINT inventory_item_model_id_fkey FOREIGN KEY (model_id)
        REFERENCES ying_rooms.item_model (model_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS ying_rooms.inventory_item
    OWNER to postgres;