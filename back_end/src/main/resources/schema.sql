CREATE TABLE IF NOT EXISTS ORDERS(
    id INT NOT NULL,
    order_time TIMESTAMP NOT NULL,
    customer_name VARCHAR(50) NOT NULL,
    customer_number VARCHAR(20) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS MENU (
    id INT NOT NULL,
    item_name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS ORDER_ITEMS (
    id INT NOT NULL,
    order_id INT NOT NULL,
    menu_item_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES ORDERS(id),
    FOREIGN KEY (menu_item_id) REFERENCES MENU(id)
);
