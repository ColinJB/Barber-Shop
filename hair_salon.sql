--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: colinjbloom
--

CREATE TABLE clients (
    id integer NOT NULL,
    name character varying,
    number character varying,
    stylist_id integer
);


ALTER TABLE clients OWNER TO colinjbloom;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: colinjbloom
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO colinjbloom;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: colinjbloom
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: colinjbloom
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying,
    number character varying
);


ALTER TABLE stylists OWNER TO colinjbloom;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: colinjbloom
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO colinjbloom;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: colinjbloom
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: colinjbloom
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner: colinjbloom
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: colinjbloom
--

COPY clients (id, name, number, stylist_id) FROM stdin;
1	Dude	23423	4
2	afadgd	43526246	4
3	shoes	346346	4
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: colinjbloom
--

SELECT pg_catalog.setval('clients_id_seq', 3, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: colinjbloom
--

COPY stylists (id, name, number) FROM stdin;
1	\N	\N
2	\N	\N
3	\N	\N
4	sdga	47467
5	poop	13431
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: colinjbloom
--

SELECT pg_catalog.setval('stylists_id_seq', 5, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: colinjbloom
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: colinjbloom
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

