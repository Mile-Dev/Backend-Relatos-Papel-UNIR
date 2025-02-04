INSERT INTO `ms_ps_payments`.`users` (`name`, `email`, `phone`)
VALUES
    ( 'Milena', 'milenis.2102@gmail.com', '3164942933'),
    ( 'Olga', 'milena.123@gmail.com', '3165423669'),
    ( 'Mafe', 'mafe.123@gmail.com', '3165476890');

INSERT INTO `ms_ps_payments`.`orders` (`userid`, `totalAmount`, `status`)
VALUES
    (1, 100.30, 'Pending'),
    (1, 100.30, 'Pending'),
    (1, 200.30, 'Pending'),
    (1, 200.30, 'Pending'),
    (1, 300.30, 'Pending'),
    (1, 300.30, 'Pending'),
    (1, 300.90, 'Pending'),
    (1, 112.40, 'Pending');

INSERT INTO `ms_ps_payments`.`orderDetails` (`orderId`, `bookId`, `quantity`, `price`)
VALUES
    (1, 1, 12, 234.50),
    (1, 2, 1, 36.40),
    (3, 2, 2, 322.40);
