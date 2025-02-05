CREATE TABLE `books_dev` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `title` varchar(255) NOT NULL,
                             `author` varchar(255) NOT NULL,
                             `genre` varchar(100) DEFAULT NULL,
                             `isbn` varchar(20) DEFAULT NULL,
                             `price` decimal(10,2) NOT NULL,
                             `publication_year` varchar(255) DEFAULT NULL,
                             `publisher` varchar(255) DEFAULT NULL,
                             `rating` int NOT NULL,
                             `stock` int NOT NULL,
                             `created_at` datetime(6) DEFAULT NULL,
                             `updated_at` datetime(6) DEFAULT NULL,
                             `status` int DEFAULT '1',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `UKntvmng22ukbqr1bgow27ekr8o` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
SELECT * FROM ms_ps_books.books_dev;

INSERT INTO `books_dev` (`id`, `title`, `author`, `genre`, `isbn`, `price`, `publication_year`, `publisher`, `rating`, `stock`, `created_at`, `updated_at`, `status`)
VALUES
    (1, 'Los hombres me explican cosas', 'Rebecca Solnit', 'Ensayo', '978-84-942-8167-5', 22.99, '2014', 'Capitán Swing', 1, 15, '2025-01-31 09:37:44.805703', NULL, 1),
    (3, 'Sapiens: De animales a dioses', 'Yuval Noah Harari', 'Historia', '978-84-9992-404-6', 19.99, '2014', 'Debate', 5, 50, '2025-01-31 09:39:57.663819', '2025-01-31 10:05:29.214429', 0),
    (4, 'Cosas que los nietos deberían saber', 'Mark Oliver Everett', 'Autobiografía', '978-84-9373-764-3', 18.50, '2008', 'Blackie Books', 5, 10, '2025-01-31 09:57:15.191733', NULL, 1),
    (5, 'Cosas que los nietos deberían saber', 'Mark Oliver Everett', 'Autobiografía', '978-84-9373-764-36', 18.50, '2008', 'Blackie Books', 5, 10, '2025-02-02 20:02:10.099282', NULL, 1);
