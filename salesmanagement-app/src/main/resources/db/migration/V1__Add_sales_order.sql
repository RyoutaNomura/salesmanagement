CREATE TABLE sales_orders (
	id uuid NOT NULL,
	slip_number varchar(20) NULL,
	order_date date NOT NULL,
	customer_id uuid NOT NULL,
	amount numeric(15, 5) NOT NULL DEFAULT 0,
	amount_with_tax numeric(15, 5) NOT NULL DEFAULT 0,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	CONSTRAINT sales_orders_pk PRIMARY KEY (id)
);
CREATE INDEX sales_orders_order_date_idx ON sales_orders USING btree (order_date);
CREATE INDEX sales_orders_slip_number_idx ON sales_orders USING btree (slip_number);

CREATE TABLE sales_order_details (
	id uuid NOT NULL,
	item_id uuid NOT NULL,
	price numeric(15, 5) NOT NULL DEFAULT 0,
	quantity numeric(15, 5) NOT NULL DEFAULT 0,
	amount numeric(15, 5) NOT NULL DEFAULT 0,
	amount_with_tax numeric(15, 5) NOT NULL DEFAULT 0,
	sales_order_id uuid NOT NULL,
	created_at timestamptz NOT NULL,
	updated_at timestamptz NOT NULL,
	CONSTRAINT sales_order_details_pk PRIMARY KEY (id),
	CONSTRAINT sales_order_details_fk FOREIGN KEY (sales_order_id) REFERENCES sales_orders(id) ON DELETE CASCADE
);
