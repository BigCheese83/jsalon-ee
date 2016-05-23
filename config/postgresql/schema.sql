--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.0
-- Started on 2016-05-23 15:49:41 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 19180)
-- Name: security; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO postgres;

--
-- TOC entry 201 (class 3079 OID 12123)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2423 (class 0 OID 0)
-- Dependencies: 201
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 19181)
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
-- TOC entry 174 (class 1259 OID 19184)
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
-- TOC entry 2424 (class 0 OID 0)
-- Dependencies: 174
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE address_id_seq OWNED BY address.id;


--
-- TOC entry 175 (class 1259 OID 19186)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE clients (
    id bigint NOT NULL,
    surname character varying(30) NOT NULL,
    name character varying(20) NOT NULL,
    patronymic character varying(30),
    birth_date date,
    registration_date date,
    id_passport integer,
    id_reg_address integer,
    id_live_address integer,
    id_contact integer NOT NULL,
    id_discount integer,
    in_black boolean DEFAULT false NOT NULL
);


ALTER TABLE clients OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 19189)
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
-- TOC entry 2425 (class 0 OID 0)
-- Dependencies: 176
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- TOC entry 177 (class 1259 OID 19191)
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
    icq character varying(15),
    bind_by character varying(20)
);


ALTER TABLE contacts OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 19194)
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
-- TOC entry 2426 (class 0 OID 0)
-- Dependencies: 178
-- Name: contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contacts_id_seq OWNED BY contacts.id;


--
-- TOC entry 179 (class 1259 OID 19196)
-- Name: discounts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE discounts (
    id bigint NOT NULL,
    name character varying(30) NOT NULL,
    value integer NOT NULL
);


ALTER TABLE discounts OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 19199)
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
-- TOC entry 2427 (class 0 OID 0)
-- Dependencies: 180
-- Name: discounts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE discounts_id_seq OWNED BY discounts.id;


--
-- TOC entry 181 (class 1259 OID 19201)
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
    busy boolean DEFAULT false NOT NULL
);


ALTER TABLE masters OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 19204)
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
-- TOC entry 2428 (class 0 OID 0)
-- Dependencies: 182
-- Name: masters_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE masters_id_seq OWNED BY masters.id;


--
-- TOC entry 183 (class 1259 OID 19206)
-- Name: passport; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE passport (
    id bigint NOT NULL,
    series character varying(10) DEFAULT ''::character varying NOT NULL,
    num character varying(15) NOT NULL,
    issued_by character varying(50) NOT NULL,
    issue_date date NOT NULL,
    subdivision character varying(10),
    country character varying(30) NOT NULL,
    bind_by character varying(20)
);


ALTER TABLE passport OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 19210)
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
-- TOC entry 2429 (class 0 OID 0)
-- Dependencies: 184
-- Name: passport_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE passport_id_seq OWNED BY passport.id;


--
-- TOC entry 185 (class 1259 OID 19212)
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE posts (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE posts OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 19215)
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
-- TOC entry 2430 (class 0 OID 0)
-- Dependencies: 186
-- Name: posts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE posts_id_seq OWNED BY posts.id;


--
-- TOC entry 198 (class 1259 OID 28134)
-- Name: posts_services; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE posts_services (
    id bigint NOT NULL,
    post_id integer NOT NULL,
    service_id integer NOT NULL
);


ALTER TABLE posts_services OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 28132)
-- Name: posts_services_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE posts_services_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE posts_services_id_seq OWNER TO postgres;

--
-- TOC entry 2431 (class 0 OID 0)
-- Dependencies: 197
-- Name: posts_services_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE posts_services_id_seq OWNED BY posts_services.id;


--
-- TOC entry 187 (class 1259 OID 19217)
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
-- TOC entry 188 (class 1259 OID 19223)
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
-- TOC entry 2432 (class 0 OID 0)
-- Dependencies: 188
-- Name: reception_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE reception_id_seq OWNED BY reception.id;


--
-- TOC entry 199 (class 1259 OID 28251)
-- Name: schedule; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE schedule (
    id bigint NOT NULL,
    appoint_date date NOT NULL,
    appoint_time integer NOT NULL,
    master_id integer,
    client_id integer,
    service_id integer,
    note character varying(100),
    status character varying(20) NOT NULL
);


ALTER TABLE schedule OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 28271)
-- Name: schedule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE schedule_id_seq OWNER TO postgres;

--
-- TOC entry 2433 (class 0 OID 0)
-- Dependencies: 200
-- Name: schedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE schedule_id_seq OWNED BY schedule.id;


--
-- TOC entry 189 (class 1259 OID 19225)
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
-- TOC entry 190 (class 1259 OID 19231)
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
-- TOC entry 2434 (class 0 OID 0)
-- Dependencies: 190
-- Name: services_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE services_id_seq OWNED BY services.id;


SET search_path = security, pg_catalog;

--
-- TOC entry 191 (class 1259 OID 19238)
-- Name: groups; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE groups (
    id bigint NOT NULL,
    name character varying(20) NOT NULL,
    gesc character varying(200)
);


ALTER TABLE groups OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 19241)
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
-- TOC entry 2435 (class 0 OID 0)
-- Dependencies: 192
-- Name: groups_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE groups_id_seq OWNED BY groups.id;


--
-- TOC entry 193 (class 1259 OID 19243)
-- Name: user_groups; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE user_groups (
    user_id integer NOT NULL,
    group_id integer NOT NULL
);


ALTER TABLE user_groups OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 19246)
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
-- TOC entry 195 (class 1259 OID 19249)
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
-- TOC entry 2436 (class 0 OID 0)
-- Dependencies: 195
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 196 (class 1259 OID 19560)
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
-- TOC entry 2230 (class 2604 OID 19255)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY address ALTER COLUMN id SET DEFAULT nextval('address_id_seq'::regclass);


--
-- TOC entry 2231 (class 2604 OID 19256)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- TOC entry 2233 (class 2604 OID 19257)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contacts ALTER COLUMN id SET DEFAULT nextval('contacts_id_seq'::regclass);


--
-- TOC entry 2234 (class 2604 OID 19258)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY discounts ALTER COLUMN id SET DEFAULT nextval('discounts_id_seq'::regclass);


--
-- TOC entry 2235 (class 2604 OID 19259)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters ALTER COLUMN id SET DEFAULT nextval('masters_id_seq'::regclass);


--
-- TOC entry 2238 (class 2604 OID 19260)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY passport ALTER COLUMN id SET DEFAULT nextval('passport_id_seq'::regclass);


--
-- TOC entry 2239 (class 2604 OID 19261)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts ALTER COLUMN id SET DEFAULT nextval('posts_id_seq'::regclass);


--
-- TOC entry 2244 (class 2604 OID 28137)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts_services ALTER COLUMN id SET DEFAULT nextval('posts_services_id_seq'::regclass);


--
-- TOC entry 2240 (class 2604 OID 19262)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reception ALTER COLUMN id SET DEFAULT nextval('reception_id_seq'::regclass);


--
-- TOC entry 2241 (class 2604 OID 19263)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY services ALTER COLUMN id SET DEFAULT nextval('services_id_seq'::regclass);


SET search_path = security, pg_catalog;

--
-- TOC entry 2242 (class 2604 OID 19265)
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY groups ALTER COLUMN id SET DEFAULT nextval('groups_id_seq'::regclass);


--
-- TOC entry 2243 (class 2604 OID 19266)
-- Name: id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


SET search_path = public, pg_catalog;

--
-- TOC entry 2246 (class 2606 OID 19268)
-- Name: address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 2248 (class 2606 OID 19270)
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 2250 (class 2606 OID 28018)
-- Name: contacts_phone_bind_by_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT contacts_phone_bind_by_key UNIQUE (phone, bind_by);


--
-- TOC entry 2252 (class 2606 OID 19274)
-- Name: contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (id);


--
-- TOC entry 2254 (class 2606 OID 19276)
-- Name: discounts_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY discounts
    ADD CONSTRAINT discounts_name_key UNIQUE (name);


--
-- TOC entry 2256 (class 2606 OID 19278)
-- Name: discounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY discounts
    ADD CONSTRAINT discounts_pkey PRIMARY KEY (id);


--
-- TOC entry 2258 (class 2606 OID 19280)
-- Name: masters_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_pkey PRIMARY KEY (id);


--
-- TOC entry 2260 (class 2606 OID 19282)
-- Name: passport_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY passport
    ADD CONSTRAINT passport_pkey PRIMARY KEY (id);


--
-- TOC entry 2262 (class 2606 OID 28016)
-- Name: passport_series_num_bind_by_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY passport
    ADD CONSTRAINT passport_series_num_bind_by_key UNIQUE (series, num, bind_by);


--
-- TOC entry 2264 (class 2606 OID 19286)
-- Name: post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);


--
-- TOC entry 2266 (class 2606 OID 19288)
-- Name: posts_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT posts_name_key UNIQUE (name);


--
-- TOC entry 2284 (class 2606 OID 28139)
-- Name: posts_services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY posts_services
    ADD CONSTRAINT posts_services_pkey PRIMARY KEY (id);


--
-- TOC entry 2286 (class 2606 OID 28151)
-- Name: posts_services_post_id_service_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY posts_services
    ADD CONSTRAINT posts_services_post_id_service_id_key UNIQUE (post_id, service_id);


--
-- TOC entry 2268 (class 2606 OID 19290)
-- Name: reception_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reception
    ADD CONSTRAINT reception_pkey PRIMARY KEY (id);


--
-- TOC entry 2288 (class 2606 OID 28255)
-- Name: schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (id);


--
-- TOC entry 2270 (class 2606 OID 19292)
-- Name: service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY services
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- TOC entry 2272 (class 2606 OID 19294)
-- Name: services_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY services
    ADD CONSTRAINT services_name_key UNIQUE (name);


SET search_path = security, pg_catalog;

--
-- TOC entry 2274 (class 2606 OID 19298)
-- Name: groups_name_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT groups_name_key UNIQUE (name);


--
-- TOC entry 2276 (class 2606 OID 19300)
-- Name: groups_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);


--
-- TOC entry 2278 (class 2606 OID 19302)
-- Name: user_groups_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_groups
    ADD CONSTRAINT user_groups_pkey PRIMARY KEY (user_id, group_id);


--
-- TOC entry 2280 (class 2606 OID 19304)
-- Name: users_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2282 (class 2606 OID 19306)
-- Name: users_username_key; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_username_key UNIQUE (username);


SET search_path = public, pg_catalog;

--
-- TOC entry 2289 (class 2606 OID 19307)
-- Name: clients_id_contact_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_contact_fkey FOREIGN KEY (id_contact) REFERENCES contacts(id);


--
-- TOC entry 2290 (class 2606 OID 19312)
-- Name: clients_id_discount_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_discount_fkey FOREIGN KEY (id_discount) REFERENCES discounts(id);


--
-- TOC entry 2291 (class 2606 OID 19317)
-- Name: clients_id_live_address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_live_address_fkey FOREIGN KEY (id_live_address) REFERENCES address(id);


--
-- TOC entry 2292 (class 2606 OID 19322)
-- Name: clients_id_passport_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_passport_fkey FOREIGN KEY (id_passport) REFERENCES passport(id);


--
-- TOC entry 2293 (class 2606 OID 19327)
-- Name: clients_id_reg_address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_id_reg_address_fkey FOREIGN KEY (id_reg_address) REFERENCES address(id);


--
-- TOC entry 2295 (class 2606 OID 19803)
-- Name: masters_id_contact_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_contact_fkey FOREIGN KEY (id_contact) REFERENCES contacts(id);


--
-- TOC entry 2297 (class 2606 OID 19813)
-- Name: masters_id_live_address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_live_address_fkey FOREIGN KEY (id_live_address) REFERENCES address(id);


--
-- TOC entry 2298 (class 2606 OID 19818)
-- Name: masters_id_passport_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_passport_fkey FOREIGN KEY (id_passport) REFERENCES passport(id);


--
-- TOC entry 2294 (class 2606 OID 19347)
-- Name: masters_id_post_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_post_fkey FOREIGN KEY (id_post) REFERENCES posts(id);


--
-- TOC entry 2296 (class 2606 OID 19808)
-- Name: masters_id_reg_address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY masters
    ADD CONSTRAINT masters_id_reg_address_fkey FOREIGN KEY (id_reg_address) REFERENCES address(id);


--
-- TOC entry 2301 (class 2606 OID 28140)
-- Name: posts_services_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts_services
    ADD CONSTRAINT posts_services_post_id_fkey FOREIGN KEY (post_id) REFERENCES posts(id);


--
-- TOC entry 2302 (class 2606 OID 28145)
-- Name: posts_services_service_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY posts_services
    ADD CONSTRAINT posts_services_service_id_fkey FOREIGN KEY (service_id) REFERENCES services(id);


--
-- TOC entry 2303 (class 2606 OID 28256)
-- Name: schedule_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_client_id_fkey FOREIGN KEY (client_id) REFERENCES clients(id);


--
-- TOC entry 2304 (class 2606 OID 28261)
-- Name: schedule_master_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_master_id_fkey FOREIGN KEY (master_id) REFERENCES masters(id);


--
-- TOC entry 2305 (class 2606 OID 28266)
-- Name: schedule_service_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_service_id_fkey FOREIGN KEY (service_id) REFERENCES services(id);


SET search_path = security, pg_catalog;

--
-- TOC entry 2299 (class 2606 OID 19362)
-- Name: user_groups_group_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_groups
    ADD CONSTRAINT user_groups_group_id_fkey FOREIGN KEY (group_id) REFERENCES groups(id);


--
-- TOC entry 2300 (class 2606 OID 19367)
-- Name: user_groups_user_id_fkey; Type: FK CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY user_groups
    ADD CONSTRAINT user_groups_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2422 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: aleksey.shulchenkov
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-05-23 15:49:41 MSK

--
-- PostgreSQL database dump complete
--

