CREATE TABLE IF NOT EXISTS service_order (
	id BIGINT NOT NULL AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    open_date DATETIME NOT NULL,
    close_date DATETIME,

    PRIMARY KEY (id)
);

ALTER TABLE service_order ADD CONSTRAINT fk_service_order_customer
FOREIGN KEY (customer_id) REFERENCES customer(id);
