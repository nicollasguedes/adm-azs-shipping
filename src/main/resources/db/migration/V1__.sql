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
    landmark       VARCHAR(255) NULL,
    latitude       VARCHAR(255) NULL,
    longitude      VARCHAR(255) NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE consignor
(
    id                   BINARY(16)       NOT NULL,
    name                 VARCHAR(255)     NOT NULL,
    email                VARCHAR(255)     NOT NULL,
    id_number_type       ENUM ('CPF','CNPJ','SSN') NOT NULL,
    id_number            VARCHAR(255)     NOT NULL,
    active               BIT(1) DEFAULT 1 NOT NULL,
    phone                VARCHAR(255)     NOT NULL,
    consignor_address_id BINARY(16)       NULL,
    CONSTRAINT pk_consignor PRIMARY KEY (id)
);

CREATE TABLE item
(
    id            BINARY(16)   NOT NULL,
    name          VARCHAR(255) NOT NULL,
    value         DOUBLE       NOT NULL,
    amount        DOUBLE       NOT NULL,
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
    consignor_id         BINARY(16)   NOT NULL,
    consignee_name       VARCHAR(255) NOT NULL,
    consignee_phone      VARCHAR(255) NOT NULL,
    consignee_email      VARCHAR(255) NOT NULL,
    consignee_address_id BINARY(16)   NULL,
    CONSTRAINT pk_shipment PRIMARY KEY (id)
);

CREATE TABLE shipment_status_history
(
    id          BINARY(16)   NOT NULL,
    status      ENUM ('LABEL_CREATED','ORDER_PLACED','SHIPMENT_PENDING','PICKED_UP','IN_TRANSIT','AT_TRANSIT_HUB','DELIVERED','DELIVERY_ATTEMPTED','DELIVERED_HELD_AT_LOCATION','SHIPMENT_EXCEPTION','RETURN_TO_SENDER','LOST','MANIFEST_CREATED','AVAILABLE_FOR_PICK_UP','DELIVERED_INDIRECT_SIGNATURE') NOT NULL,
    message     VARCHAR(255) NOT NULL,
    shipment_id BINARY(16)   NOT NULL,
    CONSTRAINT pk_shipment_status_history PRIMARY KEY (id)
);

ALTER TABLE consignor
    ADD CONSTRAINT uc_consignor_consignor_address UNIQUE (consignor_address_id);

ALTER TABLE consignor
    ADD CONSTRAINT uc_consignor_email UNIQUE (email);

ALTER TABLE consignor
    ADD CONSTRAINT uc_consignor_id_number UNIQUE (id_number);

ALTER TABLE shipment
    ADD CONSTRAINT uc_shipment_consignee_address UNIQUE (consignee_address_id);

ALTER TABLE consignor
    ADD CONSTRAINT FK_CONSIGNOR_ON_CONSIGNOR_ADDRESS FOREIGN KEY (consignor_address_id) REFERENCES address (id);

ALTER TABLE item
    ADD CONSTRAINT FK_ITEM_ON_SHIPMENT FOREIGN KEY (shipment_id) REFERENCES shipment (id);

ALTER TABLE shipment
    ADD CONSTRAINT FK_SHIPMENT_ON_CONSIGNEE_ADDRESS FOREIGN KEY (consignee_address_id) REFERENCES address (id);

ALTER TABLE shipment
    ADD CONSTRAINT FK_SHIPMENT_ON_CONSIGNOR FOREIGN KEY (consignor_id) REFERENCES consignor (id);

ALTER TABLE shipment_status_history
    ADD CONSTRAINT FK_SHIPMENT_STATUS_HISTORY_ON_SHIPMENT FOREIGN KEY (shipment_id) REFERENCES shipment (id);