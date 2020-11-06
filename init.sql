-- public.formato definition

-- Drop table

-- DROP TABLE public.formato;

CREATE TABLE public.formato (
	id serial NOT NULL,
	nombre varchar NOT NULL,
	descripcion varchar NULL,
	fechahoracreacion timestamp(0) NOT NULL,
	fechahoramodificacion timestamp(0) NULL,
	CONSTRAINT formato_pk PRIMARY KEY (id)
);


-- public.documento definition

-- Drop table

-- DROP TABLE public.documento;

CREATE TABLE public.documento (
	id serial NOT NULL,
	ruta varchar NOT NULL,
	fechahoracreacion timestamp(0) NOT NULL,
	fechahoramodificacion timestamp(0) NULL,
	formato int4 NOT NULL,
	CONSTRAINT documento_pkey PRIMARY KEY (id)
);


-- public.documento foreign keys

ALTER TABLE public.documento ADD CONSTRAINT documento_fk FOREIGN KEY (formato) REFERENCES formato(id);


-- public.recorte definition

-- Drop table

-- DROP TABLE public.recorte;

CREATE TABLE public.recorte (
	id serial NOT NULL,
	ruta varchar NOT NULL,
	fechahoracreacion timestamp(0) NOT NULL,
	fechahoramodificacion timestamp(0) NULL,
	documento int4 NOT NULL,
	CONSTRAINT recorte_pk PRIMARY KEY (id)
);


-- public.recorte foreign keys

ALTER TABLE public.recorte ADD CONSTRAINT recorte_fk FOREIGN KEY (documento) REFERENCES documento(id);
