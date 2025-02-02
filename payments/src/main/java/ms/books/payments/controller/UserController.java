package ms.books.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.model.Users;
import ms.books.payments.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServices servicesUser;

    @GetMapping("/user/{userId}")
    @Operation(
            operationId = "Obtener Usuario",
            description = "Operacion de lectura",
            summary = "Se devuelve una usuario especifico de la base de datos")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el usuario con el identificador indicado.")
    public ResponseEntity<Users> getUserid(
            @PathVariable Integer userId) {

        log.info("Request received for user {}", userId);
        Users user = servicesUser.getUser(userId);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users")
    @Operation(
            operationId = "Obtener Usuarios",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los Usuarios almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class)))
    public ResponseEntity<List<Users>> getUsers() {

        log.info("Request received for users");
        List<Users> users = servicesUser.getAllUsers();

        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    @Operation(
            operationId = "Insertar un usuaerio",
            description = "Operacion de escritura",
            summary = "Se crea un usuario a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del usuario a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el usuario con el identificador indicado.")
    public ResponseEntity<Users> addUsers(@RequestBody CreateUserRequest request) {

        Users createdProduct = servicesUser.createUser(request);

        if (createdProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
