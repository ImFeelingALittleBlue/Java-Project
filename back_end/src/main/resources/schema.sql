CREATE TABLE IF NOT EXISTS ORDERS (
    id SERIAL PRIMARY KEY,
    order_time TIMESTAMP NOT NULL,
    customer_name VARCHAR(50) NOT NULL,
    customer_number VARCHAR(20) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS MENU (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS ORDER_ITEMS (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    menu_item_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES ORDERS(id),
    FOREIGN KEY (menu_item_id) REFERENCES MENU(id)
);
