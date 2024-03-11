ALTER TABLE shipment_status_history
    ADD created_at datetime NULL;

ALTER TABLE shipment_status_history
    MODIFY created_at datetime NOT NULL;