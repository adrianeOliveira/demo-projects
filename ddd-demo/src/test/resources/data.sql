insert into customer_order(address, total_price, payment_method)
    values ('Address here', 100.00, 'credit card'),
            ('Another address', 200.00, 'cash');

insert into order_item(product_id, quantity, unit_price, customer_order_id)
    values (236, 2, 50.00, 1),
        (579, 1, 50.00, 2),
        (12, 3, 50.00, 2)
