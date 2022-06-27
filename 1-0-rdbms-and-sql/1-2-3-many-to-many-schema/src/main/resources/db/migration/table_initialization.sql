CREATE TABLE broker(
                       id Bigint NOT NULL,
                       username varchar(255) NOT NULL,
                       first_name varchar(255) NOT NULL,
                       last_name varchar(255) NOT NULL,
                       CONSTRAINT PK_broker PRIMARY KEY (id),
                       CONSTRAINT UQ_broker_username UNIQUE (username)
);

CREATE TABLE sales_group(
                            id Bigint,
                            name varchar(255) NOT NULL,
                            transaction_type varchar(255) NOT NULL,
                            max_transaction_amount Bigint NOT NULL,
                            CONSTRAINT PK_sales_group PRIMARY KEY (id),
                            CONSTRAINT UQ_sales_group_name UNIQUE (name)
);

CREATE TABLE broker_sales_group(
                                   broker_id Bigint NOT NULL,
                                   sales_group_id Bigint NOT NULL,
                                   CONSTRAINT PK_broker_sales_group PRIMARY KEY (broker_id, sales_group_id),
                                   CONSTRAINT FK_broker_sales_group_broker FOREIGN KEY (broker_id) REFERENCES broker(id),
                                   CONSTRAINT FK_broker_sales_group_sales_group FOREIGN KEY (sales_group_id) REFERENCES sales_group(id)
);

