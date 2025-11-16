package com.gs.global_solution.controller;


import com.gs.global_solution.dto.UserDTO;
import com.gs.global_solution.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
@Tag(name = "Usuários",description = "Gerenciamento de usuários")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Criar Usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "409", description = "Usuário com email já cadastrado")
    })
    public ResponseEntity<UserDTO> criar(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.criar(dto));
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca usuário pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UserDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários do sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<UserDTO>> listarTodos() {
        return ResponseEntity.ok(userService.listarTodos());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário especifico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UserDTO> atualizar(@PathVariable Long id, @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário especifico")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
