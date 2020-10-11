CREATE TABLE items (
	id uuid NOT NULL,
	name varchar(20) NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	CONSTRAINT items_pk PRIMARY KEY (id)
);

CREATE TABLE customers (
	id uuid NOT NULL,
	name varchar(20) NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	CONSTRAINT customers_pk PRIMARY KEY (id)
);
