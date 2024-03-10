ALTER TABLE client
    DROP COLUMN id_number_type;

ALTER TABLE client
    ADD id_number_type VARCHAR(255) NOT NULL;

ALTER TABLE shipment_status_history
    DROP COLUMN status;

ALTER TABLE shipment_status_history
    ADD status VARCHAR(255) NOT NULL;