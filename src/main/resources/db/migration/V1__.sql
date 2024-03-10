CREATE TABLE address
(
    id             BINARY(16)   NOT NULL,
    zip_code       VARCHAR(255) NOT NULL,
    street_address VARCHAR(255) NOT NULL,
    street_number  VARCHAR(255) NOT NULL,
    country        VARCHAR(255) NOT NULL,
    city           VARCHAR(255) NOT NULL,
    state          VARCHAR(255) NOT NULL,
    unit_number    VARCHAR(255) NULL,
    landmark       VARCHAR(255) NOT NULL,
    latitude       VARCHAR(255) NULL,
    longitude      VARCHAR(255) NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE client
(
    id                   BINARY(16)       NOT NULL,
    name                 VARCHAR(255)     NOT NULL,
    email                VARCHAR(255)     NOT NULL,
    id_number_type       VARCHAR(255)     NOT NULL,
    id_number            VARCHAR(255)     NOT NULL,
    active               BIT(1) DEFAULT 1 NOT NULL,
    phone                VARCHAR(255)     NOT NULL,
    consignor_address_id BINARY(16)       NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE item
(
    id            BINARY(16)   NOT NULL,
    name          VARCHAR(255) NOT NULL,
    value         DOUBLE       NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    shipment_id   BINARY(16)   NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (id)
);

CREATE TABLE shipment
(
    id                   BINARY(16)   NOT NULL,
    price                DOUBLE       NOT NULL,
    width                DOUBLE       NOT NULL,
    height               DOUBLE       NOT NULL,
    length               DOUBLE       NOT NULL,
    weight               DOUBLE       NOT NULL,
    client_id            BINARY(16)   NOT NULL,
    consignee_name       VARCHAR(255) NOT NULL,
    consignee_phone      VARCHAR(255) NOT NULL,
    consignee_email      VARCHAR(255) NOT NULL,
    consignee_address_id BINARY(16)   NULL,
    CONSTRAINT pk_shipment PRIMARY KEY (id)
);

CREATE TABLE shipment_status_history
(
    id        BINARY(16)   NOT NULL,
    status    VARCHAR(255) NOT NULL,
    message   VARCHAR(255) NOT NULL,
    client_id BINARY(16)   NOT NULL,
    CONSTRAINT pk_shipment_status_history PRIMARY KEY (id)
);

ALTER TABLE client
    ADD CONSTRAINT uc_client_consignor_address UNIQUE (consignor_address_id);

ALTER TABLE client
    ADD CONSTRAINT uc_client_email UNIQUE (email);

ALTER TABLE client
    ADD CONSTRAINT uc_client_id_number UNIQUE (id_number);

ALTER TABLE shipment
    ADD CONSTRAINT uc_shipment_consignee_address UNIQUE (consignee_address_id);

ALTER TABLE client
    ADD CONSTRAINT FK_CLIENT_ON_CONSIGNOR_ADDRESS FOREIGN KEY (consignor_address_id) REFERENCES address (id);

ALTER TABLE item
    ADD CONSTRAINT FK_ITEM_ON_SHIPMENT FOREIGN KEY (shipment_id) REFERENCES shipment (id);

ALTER TABLE shipment
    ADD CONSTRAINT FK_SHIPMENT_ON_CLIENT FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE shipment
    ADD CONSTRAINT FK_SHIPMENT_ON_CONSIGNEE_ADDRESS FOREIGN KEY (consignee_address_id) REFERENCES address (id);

ALTER TABLE shipment_status_history
    ADD CONSTRAINT FK_SHIPMENT_STATUS_HISTORY_ON_CLIENT FOREIGN KEY (client_id) REFERENCES shipment (id);