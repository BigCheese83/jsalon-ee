--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2015-12-15 20:56:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 16394)
-- Name: security; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO postgres;

--
-- TOC entry 199 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2141 (class 0 OID 0)
-- Dependencies: 199
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 16395)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE address (
    id bigint NOT NULL,
    country character varying(30) NOT NULL,
    district character varying(30),
    city character varying(30) NOT NULL,
    street character varying(30) NOT NULL,
    house character varying(5) NOT NULL,
    section character varying(3),
    flat character varying(3) NOT NULL,
    zip character varying(10)
);


ALTER TABLE address OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16398)
-- Name: address_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE address_id_seq OWNER TO postgres;

--
-- TOC entry 2142 (class 0 OID 0)
-- Dependencies: 174
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE address_id_seq OWNED BY address.id;


--
-- TOC entry 175 (class 1259 OID 16400)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE clients (
    id bigint NOT NULL,
    surname character varying(30) NOT NULL,
    name character varying(20) NOT NULL,
    patronymic character varying(30),
    birth_date date NOT NULL,
    registration_date date NOT NULL,
    id_passport integer NOT NULL,
    id_reg_address integer NOT NULL,
    id_live_address integer,
    id_contact integer NOT NULL,
    id_discount integer,
    in_black boolean
);


ALTER TABLE clients OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16403)
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO postgres;

--
-- TOC entry 2143 (class 0 OID 0)
-- Dependencies: 176
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- TOC entry 177 (class 1259 OID 16405)
-- Name: contacts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contacts (
    id bigint NOT NULL,
    phone character varying(20) NOT NULL,
    email character varying(30),
    vk character varying(20),
    skype character varying(20),
    facebook character varying(20),
    twitter character varying(20),
    icq character varying(15)
);


ALTER TABLE contacts OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16408)
-- Name: contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contacts_id_seq OWNER TO postgres;

--
-- TOC entry 2144 (class 0 OID 0)
-- Dependencies: 178
-- Name: contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contacts_id_seq OWNED BY contacts.id;


--
-- TOC entry 179 (class 1259 OID 16410)
-- Name: discounts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE discounts (
    id bigint NOT NULL,
    name character varying(30) NOT NULL,
    value integer NOT NULL
);


ALTER TABLE discounts OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16413)
-- Name: discounts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE discounts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discounts_id_seq OWNER TO postgres;

--
-- TOC entry 2145 (class 0 OID 0)
-- Dependencies: 180
-- Name: discounts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE discounts_id_seq OWNED BY discounts.id;


--
-- TOC entry 181 (class 1259 OID 16415)
-- Name: masters; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE masters (
    id bigint NOT NULL,
    surname character varying(30) NOT NULL,
    name character varying(20) NOT NULL,
    patronymic character varying(30),
    birth_date date NOT NULL,
    hiring_date date NOT NULL,
    id_passport integer NOT NULL,
    id_post integer NOT NULL,
    id_reg_address integer NOT NULL,
    id_live_address integer,
    id_contact integer NOT NULL,
    busy boolean
);


ALTER TABLE masters OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16418)
-- Name: masters_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE masters_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE masters_id_seq OWNER TO postgres;

--
-- TOC entry 2146 (class 0 OID 0)
-- Dependencies: 182
-- Name: masters_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE masters_id_seq OWNED BY masters.id;


--
-- TOC entry 183 (class 1259 OID 16420)
-- Name: passport; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE passport (
    id bigint NOT NULL,
    series character varying(10) DEFAULT ''::character varying NOT NULL,
    num character varying(15) NOT NULL,
    issued_by character varying(50) NOT NULL,
    issue_date date NOT NULL,
    subdivision character varying(10),
    country character varying(30) NOT NULL
);


ALTER TABLE passport OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16424)
-- Name: passport_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE passport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE passport_id_seq OWNER TO postgres;

--
-- TOC entry 2147 (class 0 OID 0)
-- Dependencies: 184
-- Name: passport_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE passport_id_seq OWNED BY passport.id;


--
-- TOC entry 185 (class 1259 OID 16426)
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE posts (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE posts OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16429)
-- Name: posts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE posts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE posts_id_seq OWNER TO postgres;

--
-- TOC entry 2148 (class 0 OID 0)
-- Dependencies: 186
-- Name: posts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE posts_id_seq OWNED BY posts.id;


--
-- TOC entry 187 (class 1259 OID 16431)
-- Name: reception; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reception (
    id bigint NOT NULL,
    date_service date NOT NULL,
    id_master integer NOT NULL,
    id_client integer NOT NULL,
    amount numeric NOT NULL,
    doc bytea NOT NULL,
    xml_doc text,
    json_doc text
);


ALTER TABLE reception OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16437)
-- Name: reception_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE reception_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE reception_id_seq OWNER TO postgres;

--
-- TOC entry 2149 (class 0 OID 0)
-- Dependencies: 188
-- Name: reception_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE reception_id_seq OWNED BY reception.id;


--
-- TOC entry 189 (class 1259 OID 16439)
-- Name: services; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE services (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    cost numeric NOT NULL,
    duration integer NOT NULL,
    description character varying(80)
);


ALTER TABLE services OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16445)
-- Name: services_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE services_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE services_id_seq OWNER TO postgres;

--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 190
-- Name: services_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE services_id_seq OWNED BY services.id;


--
-- TOC entry 191 (class 1259 OID 16447)
-- Name: shedule; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE shedule (
    id bigint NOT NULL,
    record_date date NOT NULL,
    id_master integer,
    id_client integer,
    id_service integer,
    note character varying(50),
    record_time character varying(5) NOT NULL
);


ALTER TABLE shedule OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16450)
-- Name: shedule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE shedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE shedule_id_seq OWNER TO postgres;

--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 192
-- Name: shedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE shedule_id_seq OWNED BY shedule.id;


SET search_path = security, pg_catalog;

--
-- TOC entry 193 (class 1259 OID 16452)
-- Name: groups; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE groups (
    id bigint NOT NULL,
    name character varying(20) NOT NULL,
    gesc character varying(200)
);


ALTER TABLE groups OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 16455)
-- Name: groups_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE groups_id_seq OWNER TO postgres;

--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 194
-- Name: groups_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE groups_id_seq OWNED BY groups.id;


--
-- TOC entry 195 (class 1259 OID 16457)
-- Name: user_groups; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE user_groups (
    user_id integer NOT NULL,
    group_id integer NOT NULL
);


ALTER TABLE user_groups OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16460)
-- Name: users; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    id bigint NOT NULL,
    username character varying(10) NOT NULL,
    firstname character varying(15) NOT NULL,
    lastname character varying(15) NOT NULL,
    middlename character varying(15),
    password character(32) NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16463)
-- Name: users_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 197
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 198 (class 1259 OID 16465)
-- Name: v_user_role; Type: VIEW; Schema: security; Owner: postgres
--

CREATE VIEW v_user_role AS
 SELECT u.id,
    u.username,
    u.lastname,
    u.firstname,
    u.middlename,
    u.password,
    g.name AS role
   FROM users u,
    groups g,
    user_groups ug
  WHERE ((ug.user_id = u.id) AND (ug.group_id = g.id));


ALTER TABLE v_user_role OWNER TO postgres;

SET search_path = public, pg_catalog;

--
-- TOC entry 1958 (class 2604 OID 16469)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY address ALTER COLUMN id SET DEFAULT nextval('address_id_seq'::regclass);


--
-- TOC entry 1959 (class 2604 OID 16470)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- TOC entry 1960 (class 2604 OID 16471)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts ALTER COLUMN id SET DEFAULT nextval('contacts_id_seq'::regclass);


--
-- TOC entry 1961 (class 2604 OID 16472)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY discounts ALTER COLUMN id SET DEFAULT nextval('discounts_id_seq'::regclass);


--
-- TOC entry 1962 (class 2604 OID 16473)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters ALTER COLUMN id SET DEFAULT nextval('masters_id_seq'::regclass);


--
-- TOC entry 1964 (class 2604 OID 16474)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY passport ALTER COLUMN id SET DEFAULT nextval('passport_id_seq'::regclass);


--
-- TOC entry 1965 (class 2604 OID 16475)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts ALTER COLUMN id SET DEFAULT nextval('posts_id_seq'::regclass);


--
-- TOC entry 1966 (class 2604 OID 16476)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reception ALTER COLUMN id SET DEFAULT nextval('reception_id_seq'::regclass);


--
-- TOC entry 1967 (class 2604 OID 16477)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY services ALTER COLUMN id SET DEFAULT nextval('services_id_seq'::regclass);


--
-- TOC entry 1968 (class 2604 OID 16478)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY shedule ALTER COLUMN id SET DEFAULT nextval('shedule_id_seq'::regclass);


SET search_path = security, pg_catalog;

--
-- TOC entry 1969 (class 2604 OID 16479)
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY groups ALTER COLUMN id SET DEFAULT nextval('groups_id_seq'::regclass);


--
-- TOC entry 1970 (class 2604 OID 16480)
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


SET search_path = public, pg_catalog;

--
-- TOC entry 1972 (class 2606 OID 16482)
-- Name: address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 1974 (class 2606 OID 16484)
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 1976 (class 2606 OID 16486)
-- Name: contacts_phone_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT contacts_phone_key UNIQUE (phone);


--
-- TOC entry 1978 (class 2606 OID 16488)
-- Name: contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (id);


--
-- TOC entry 1980 (class 2606 OID 16490)
-- Name: discounts_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY discounts
    ADD CONSTRAINT discounts_name_key UNIQUE (name);


--
-- TOC entry 1982 (class 2606 OID 16492)
-- Name: discounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY discounts
    ADD CONSTRAINT discounts_pkey PRIMARY KEY (id);


--
-- TOC entry 1984 (class 2606 OID 16494)
-- Name: masters_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_pkey PRIMARY KEY (id);


--
-- TOC entry 1986 (class 2606 OID 16496)
-- Name: passport_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY passport
    ADD CONSTRAINT passport_pkey PRIMARY KEY (id);


--
-- TOC entry 1988 (class 2606 OID 16498)
-- Name: passport_series_num_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY passport
    ADD CONSTRAINT passport_series_num_key UNIQUE (series, num);


--
-- TOC entry 1990 (class 2606 OID 16500)
-- Name: post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);


--
-- TOC entry 1992 (class 2606 OID 16502)
-- Name: posts_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT posts_name_key UNIQUE (name);


--
-- TOC entry 1994 (class 2606 OID 16504)
-- Name: reception_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reception
    ADD CONSTRAINT reception_pkey PRIMARY KEY (id);


--
-- TOC entry 1996 (class 2606 OID 16506)
-- Name: service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY services
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- TOC entry 1998 (class 2606 OID 16508)
-- Name: services_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY services
    ADD CONSTRAINT services_name_key UNIQUE (name);


--
-- TOC entry 2000 (class 2606 OID 16510)
-- Name: shedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY shedule
    ADD CONSTRAINT shedule_pkey PRIMARY KEY (id);


SET search_path = security, pg_catalog;

--
-- TOC entry 2002 (class 2606 OID 16512)
-- Name: groups_name_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT groups_name_key UNIQUE (name);


--
-- TOC entry 2004 (class 2606 OID 16514)
-- Name: groups_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);


--
-- TOC entry 2006 (class 2606 OID 16516)
-- Name: user_groups_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_groups
    ADD CONSTRAINT user_groups_pkey PRIMARY KEY (user_id, group_id);


--
-- TOC entry 2008 (class 2606 OID 16518)
-- Name: users_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2010 (class 2606 OID 16520)
-- Name: users_username_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_username_key UNIQUE (username);


SET search_path = public, pg_catalog;

--
-- TOC entry 2011 (class 2606 OID 16521)
-- Name: clients_id_contact_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_contact_fkey FOREIGN KEY (id_contact) REFERENCES contacts(id);


--
-- TOC entry 2012 (class 2606 OID 16526)
-- Name: clients_id_discount_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_discount_fkey FOREIGN KEY (id_discount) REFERENCES discounts(id);


--
-- TOC entry 2013 (class 2606 OID 16531)
-- Name: clients_id_live_address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_live_address_fkey FOREIGN KEY (id_live_address) REFERENCES address(id);


--
-- TOC entry 2014 (class 2606 OID 16536)
-- Name: clients_id_passport_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_passport_fkey FOREIGN KEY (id_passport) REFERENCES passport(id);


--
-- TOC entry 2015 (class 2606 OID 16541)
-- Name: clients_id_reg_address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_reg_address_fkey FOREIGN KEY (id_reg_address) REFERENCES address(id);


--
-- TOC entry 2016 (class 2606 OID 16546)
-- Name: masters_id_contact_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_contact_fkey FOREIGN KEY (id_contact) REFERENCES contacts(id);


--
-- TOC entry 2017 (class 2606 OID 16551)
-- Name: masters_id_live_address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_live_address_fkey FOREIGN KEY (id_live_address) REFERENCES address(id);


--
-- TOC entry 2018 (class 2606 OID 16556)
-- Name: masters_id_passport_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_passport_fkey FOREIGN KEY (id_passport) REFERENCES passport(id);


--
-- TOC entry 2019 (class 2606 OID 16561)
-- Name: masters_id_post_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_post_fkey FOREIGN KEY (id_post) REFERENCES posts(id);


--
-- TOC entry 2020 (class 2606 OID 16566)
-- Name: masters_id_reg_address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_reg_address_fkey FOREIGN KEY (id_reg_address) REFERENCES address(id);


--
-- TOC entry 2021 (class 2606 OID 16571)
-- Name: shedule_id_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY shedule
    ADD CONSTRAINT shedule_id_service_fkey FOREIGN KEY (id_service) REFERENCES services(id);


SET search_path = security, pg_catalog;

--
-- TOC entry 2022 (class 2606 OID 16576)
-- Name: user_groups_group_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_groups
    ADD CONSTRAINT user_groups_group_id_fkey FOREIGN KEY (group_id) REFERENCES groups(id);


--
-- TOC entry 2023 (class 2606 OID 16581)
-- Name: user_groups_user_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_groups
    ADD CONSTRAINT user_groups_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2140 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-12-15 20:56:06

--
-- PostgreSQL database dump complete
--

