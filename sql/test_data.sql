--
-- PostgreSQL database dump
--

-- Dumped from database version 11.16
-- Dumped by pg_dump version 16.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO "user";

SET default_tablespace = '';

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    account_limit integer,
    amount_money integer,
    in_debit_amount integer,
    client_account_customer_identifier bigint,
    id bigint NOT NULL
);


ALTER TABLE public.account OWNER TO "user";

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    customer_identifier bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255)
);


ALTER TABLE public.client OWNER TO "user";

--
-- Name: credit_card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.credit_card (
    client_information_customer_identifier bigint,
    id bigint NOT NULL,
    credit_card_date_expiration character varying(255),
    credit_card_number character varying(255),
    cvv character varying(255)
);


ALTER TABLE public.credit_card OWNER TO "user";

--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account (account_limit, amount_money, in_debit_amount, client_account_customer_identifier, id) FROM stdin;
1000	1000	0	1	1
\.


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.client (customer_identifier, first_name, last_name) FROM stdin;
1	John	Doe
\.


--
-- Data for Name: credit_card; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.credit_card (client_information_customer_identifier, id, credit_card_date_expiration, credit_card_number, cvv) FROM stdin;
1	1	12/25	1234567891234567	123
\.


--
-- Name: account account_client_account_customer_identifier_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_client_account_customer_identifier_key UNIQUE (client_account_customer_identifier);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (customer_identifier);


--
-- Name: credit_card credit_card_client_information_customer_identifier_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_card
    ADD CONSTRAINT credit_card_client_information_customer_identifier_key UNIQUE (client_information_customer_identifier);


--
-- Name: credit_card credit_card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_card
    ADD CONSTRAINT credit_card_pkey PRIMARY KEY (id);


--
-- Name: credit_card fkauta3uvae3y0r9c1q1snolmno; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_card
    ADD CONSTRAINT fkauta3uvae3y0r9c1q1snolmno FOREIGN KEY (client_information_customer_identifier) REFERENCES public.client(customer_identifier);


--
-- Name: account fklpl7tnk6vtef9m8ulno0hc6lf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fklpl7tnk6vtef9m8ulno0hc6lf FOREIGN KEY (client_account_customer_identifier) REFERENCES public.client(customer_identifier);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

