ALTER TABLE client
    DROP COLUMN id_number_type;

ALTER TABLE client
    ADD id_number_type ENUM ('CPF','CNPJ','SSN') NOT NULL;

ALTER TABLE address
    MODIFY landmark VARCHAR(255) NULL;

ALTER TABLE item
    ADD COLUMN amount FLOAT(53) NOT NULL;

ALTER TABLE shipment_status_history
    DROP COLUMN status;

ALTER TABLE shipment_status_history
    ADD status  ENUM ('LABEL_CREATED','ORDER_PLACED','SHIPMENT_PENDING','PICKED_UP','IN_TRANSIT','AT_TRANSIT_HUB','DELIVERED','DELIVERY_ATTEMPTED','DELIVERED_HELD_AT_LOCATION','SHIPMENT_EXCEPTION','RETURN_TO_SENDER','LOST','MANIFEST_CREATED','AVAILABLE_FOR_PICK_UP','DELIVERED_INDIRECT_SIGNATURE') NOT NULL