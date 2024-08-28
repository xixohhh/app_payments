
-- CREAR BASE DE DATOS

CREATE SCHEMA IF NOT EXISTS "payments-db"
    AUTHORIZATION postgres;


-- CREAR TABLA USUARIOS
-- Table: payments-db.users
-- DROP TABLE IF EXISTS "payments-db".users;

CREATE TABLE IF NOT EXISTS "payments-db".users
(
    id_user integer NOT NULL,
    user_name character varying(45) COLLATE pg_catalog."default" NOT NULL,
    name character varying(45) COLLATE pg_catalog."default",
    first_surname character varying(45) COLLATE pg_catalog."default",
    second_surname character varying(45) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id_user)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "payments-db".users
    OWNER to postgres;

COMMENT ON TABLE "payments-db".users
    IS 'Contiene los usuarios de la aplicación';

-- CREAR TABLA PAYMENTS
-- Table: payments-db.users_payments
-- DROP TABLE IF EXISTS "payments-db".users_payments;

CREATE TABLE IF NOT EXISTS "payments-db".users_payments
(
    id_payment bigint NOT NULL,
    id_user integer NOT NULL,
    ammount numeric(12,2) NOT NULL,
    payment_date timestamp without time zone NOT NULL,
    card_number character varying(25) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT user_payments_pkey PRIMARY KEY (id_payment),
    CONSTRAINT fk_user_payments FOREIGN KEY (id_user)
        REFERENCES "payments-db".users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "payments-db".users_payments
    OWNER to postgres;

COMMENT ON TABLE "payments-db".users_payments
    IS 'Pagos de los usuarios';




-- SECUENCIA PARA PAYMENTS 

CREATE SEQUENCE "payments-db".users_payments_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9999999999;

ALTER SEQUENCE "payments-db".users_payments_seq
    OWNED BY "payments-db".users_payments.id_payment;

ALTER SEQUENCE "payments-db".users_payments_seq
    OWNER TO postgres;

-- SECUENCIA PARA USERS 

CREATE SEQUENCE "payments-db".users_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9999999;

ALTER SEQUENCE "payments-db".users_seq
    OWNED BY "payments-db".users.id_user;

ALTER SEQUENCE "payments-db".users_seq
    OWNER TO postgres;
    
    
-- INSERTAR USUARIO PRUEBAS

INSERT INTO "payments-db".users(id_user, user_name, name, first_surname, second_surname) VALUES (nextval('payments-db.users_seq'), 'test', 'test', 'test', 'test');
INSERT INTO "payments-db".users_payments(id_payment, id_user, ammount, payment_date, card_number) VALUES (nextval('payments-db.users_payments_seq'),currval('payments-db.users_seq') , 150.00, '2024-05-12T13:00' , '213131231312');
INSERT INTO "payments-db".users_payments(id_payment, id_user, ammount, payment_date, card_number) VALUES (nextval('payments-db.users_payments_seq'),currval('payments-db.users_seq') , 350.00, '2024-05-12T14:00' , '213131231312');

commit;