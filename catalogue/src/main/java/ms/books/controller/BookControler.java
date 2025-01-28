package ms.books.controller;


@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Books Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre libros tomados de una base de datos mysql.")
public class BookController {

    private final BookService service;

    @GetMapping("/books")
    @Operation(
            operationId = "Obtener libros",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los libros almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    public ResponseEntity<List<Book>> getBooks(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "title", description = "Titulo del libro. No tiene por que ser exacto", example = "El Quijote", required = false)
            @RequestParam(required = false) String title,
            @Parameter(name = "author", description = "Autor del libro. Debe ser exacto", example = "Cervantes", required = false)
            @RequestParam(required = false) String author,
            @Parameter(name = "genre", description = "Genero del libro. No tiene por que ser exacto", example = "Novela", required = false)
            @RequestParam(required = false) String genre,
            @Parameter(name = "publisher", description = "Editorial del libro. No tiene por que ser exacta", example = "Anaya", required = false)
            @RequestParam(required = false) String publisher,
            @Parameter(name = "publicationYear", description = "Año de publicacion del libro. Debe ser exacto", example = "1605", required = false)
            @RequestParam(required = false) Integer publicationYear,
            @Parameter(name = "isbn", description = "ISBN del libro. Debe ser exacto", example = "978-84-206-6177-3", required = false)
            @RequestParam(required = false) String isbn,
            @Parameter(name = "price", description = "Precio del libro. Debe ser exacto", example = "20.00", required = false)
            @RequestParam(required = false) BigDecimal price,
            @Parameter(name = "stock", description = "Stock del libro. Debe ser exacto", example = "10", required = false)
            @RequestParam(required = false) Integer stock,
            @Parameter(name = "createdAt", description = "Fecha de creacion del libro. Debe ser exacta", example = "2021-06-18", required = false)
        {
        log.info("headers: {}", headers);
        List<Book> books = service.getBooks(title, author, genre, publisher, publicationYear, isbn, price, stock, createdAt);
        if (books != null) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
        }

        @GetMapping("/books/{bookId}")
        @Operation(
                operationId = "Obtener un libro",
                description = "Operacion de lectura",
                summary = "Se devuelve un libro a partir de su identificador.")
        @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
        @ApiResponse(
                responseCode = "404",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
                description = "No se ha encontrado el libro con el identificador indicado.")
        public ResponseEntity<Book> getBook(@PathVariable String bookId) {

                log.info("Request received for book {}", bookId);
                Book book = service.getBook(bookId);

                if (book != null) {
                    return ResponseEntity.ok(book);
                } else {
                    return ResponseEntity.notFound().build();
                }
        }

        @DeleteMapping("/books/{bookId}")
        @Operation(
                operationId = "Eliminar un libro",
                description = "Operacion de escritura",
                summary = "Se elimina un libro a partir de su identificador.")
        @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
        @ApiResponse(
                responseCode = "404",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
                description = "No se ha encontrado el libro con el identificador indicado.")
        public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {

                log.info("Request received for book {}", bookId);
                boolean deleted = service.deleteBook(bookId);

                if (deleted) {
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
        }

        @PostMapping("/books")
        @Operation(
                operationId = "Crear un libro",
                description = "Operacion de escritura",
                summary = "Se crea un libro a partir de los datos proporcionados.")
        @ApiResponse(
                responseCode = "201",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
        @ApiResponse(
                responseCode = "400",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
                description = "No se ha podido crear el libro.")
        @ApiResponse(
                responseCode = "404",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
                description = "No se ha encontrado el libro con el identificador indicado.")
        public ResponseEntity<Book> createBook(@RequestBody Book book) {

                    log.info("Request received for book {}", book);
                    Book createdBook = service.createBook(book);

                    if (createdBook != null) {
                        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
        }

        @PatchMapping("/books/{bookId}")
        @Operation(
                operationId = "Actualizar un libro",
                description = "Operacion de escritura",
                summary = "Se actualiza un libro a partir de los datos proporcionados.")
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del producto a crear.",
            required = true,
            content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = String.class))))
        @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
        @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el libro con el identificador indicado.")
        public ResponseEntity<Book> patchBook(@PathVariable String bookId, @RequestBody Book book) {

                    Book patched = service.updateBook(bookId, patchBody);
                    if (patched != null) {
                        return ResponseEntity.ok(patched);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
        }


        @PutMapping("/books/{bookId}")
        @Operation(
                operationId = "Actualizar un libro",
                description = "Operacion de escritura",
                summary = "Se actualiza un libro a partir de los datos proporcionados.")
        @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
        @ApiResponse(
                responseCode = "404",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
                description = "No se ha encontrado el libro con el identificador indicado.")
        public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody Book book) {

                    log.info("Request received for book {}", bookId);
                    Book updatedBook = service.updateBook(bookId, book);

                    if (updatedBook != null) {
                        return ResponseEntity.ok(updatedBook);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
        }