package com.gs.global_solution.controller;


import com.gs.global_solution.dto.CheckinEmocionalDTO;
import com.gs.global_solution.service.CheckinEmocionalService;
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
@RequestMapping("/checkin-emocional")
@AllArgsConstructor
@Tag(name = "Check-in do usuário")
public class CheckinEmocionalController {
    private final CheckinEmocionalService checkinEmocionalService;

    @PostMapping
    @Operation(summary = "Criar checkin emocional")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Check-in criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<CheckinEmocionalDTO> criar(@RequestBody @Valid CheckinEmocionalDTO dto) {
        CheckinEmocionalDTO criado = checkinEmocionalService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um checkin especifico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Check-in encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Check-in não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<CheckinEmocionalDTO> buscarPorId(@PathVariable Long id) {
        CheckinEmocionalDTO dto = checkinEmocionalService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/usuario/{userId}")
    @Operation(summary = "Listar o checkin do usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<CheckinEmocionalDTO>> listarPorUsuario(@PathVariable Long userId) {
        List<CheckinEmocionalDTO> lista = checkinEmocionalService.listarPorUsuario(userId);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar checkin do usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Check-in atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Check-in não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<CheckinEmocionalDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid CheckinEmocionalDTO dto) {
        CheckinEmocionalDTO atualizado = checkinEmocionalService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar o checkin")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Check-in não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        checkinEmocionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
