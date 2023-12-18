CREATE DATABASE address;

DROP TABLE IF EXISTS country, region, city, address

CREATE TABLE IF NOT EXISTS country (
    id serial NOT NULL,
    fullName character varying(30),
    shortName character varying(30),
    CONSTRAINT country_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS region (
    id serial NOT NULL,
    nameRegion character varying(30),
    countryId integer NOT NULL,
    CONSTRAINT region_pkey PRIMARY KEY (id),
    CONSTRAINT region_countryid_fkey FOREIGN KEY (countryId) REFERENCES public.country (id)
);

CREATE TABLE IF NOT EXISTS city (
    id serial NOT NULL,
    nameCity character varying(30),
    regionId integer NOT NULL,
    CONSTRAINT city_pkey PRIMARY KEY (id),
    CONSTRAINT city_regionId_fkey FOREIGN KEY (regionId) REFERENCES public.region (id)
);

CREATE TABLE IF NOT EXISTS address (
    id serial NOT NULL,
    person character varying(30),
    street character varying(30),
    building integer,
    office integer,
    cityId integer NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id),
    CONSTRAINT address_cityId_fkey FOREIGN KEY (cityId) REFERENCES public.city (id)
);

