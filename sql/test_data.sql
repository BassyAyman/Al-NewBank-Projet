--
-- PostgreSQL database dump
--

-- Dumped from database version 13.12 (Debian 13.12-1.pgdg120+1)
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
-- Name: public; Type: SCHEMA; Schema: -; Owner: user
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO "user";

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.account (
    id bigint NOT NULL,
    lastname character varying(255),
    name character varying(255)
);


ALTER TABLE public.account OWNER TO "user";

--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.account (id, lastname, name) FROM stdin;
1	Doe	John
\.


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: user
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

