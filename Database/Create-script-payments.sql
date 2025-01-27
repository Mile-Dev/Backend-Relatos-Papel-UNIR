CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de pedidos (relación con la base de libros por book_id)
CREATE TABLE Orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userid INT NOT NULL,
    totalAmount DECIMAL(10,2) NOT NULL,
    orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Pending', 'Completed', 'Cancelled') DEFAULT 'Pending',
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userid) REFERENCES Users(id) ON DELETE CASCADE
);

-- Tabla de detalles del pedido (relación con BooksDB mediante book_id)
CREATE TABLE OrderDetails (
    id INT AUTO_INCREMENT PRIMARY KEY,
    orderId INT NOT NULL,
    bookId INT NOT NULL, -- Se relaciona con BooksDB
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (orderId) REFERENCES Orders(id) ON DELETE CASCADE
);

-- Tabla de pagos
CREATE TABLE Payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    orderId INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    paymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    paymentMethod ENUM('Credit Card', 'PayPal', 'Bank Transfer') NOT NULL,
    status ENUM('Pending', 'Completed', 'Failed') DEFAULT 'Pending',
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (orderId) REFERENCES Orders(id) ON DELETE CASCADE
);