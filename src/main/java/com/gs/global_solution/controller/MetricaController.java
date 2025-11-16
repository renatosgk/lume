package com.gs.global_solution.controller;


import com.gs.global_solution.dto.MetricaDTO;
import com.gs.global_solution.dto.RelatorioMetricaDTO;
import com.gs.global_solution.service.MetricaService;
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
@RequestMapping("/metricas")
@AllArgsConstructor
@Tag(name = "Métricas do usuário")
public class MetricaController {
    private final MetricaService metricaService;

    @PostMapping
    @Operation(summary= "Cria uma métrica do usuário ")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Métrica criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<MetricaDTO> criar(@Valid @RequestBody MetricaDTO dto) {
        return ResponseEntity.ok(metricaService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Lista todas as métricas do sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<MetricaDTO>> listarTodos() {
        return ResponseEntity.ok(metricaService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma métrica especifica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Métrica encontrada"),
            @ApiResponse(responseCode = "404", description = "Métrica não encontrada"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<MetricaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(metricaService.buscarPorId(id));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma métrica especifica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Métrica atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Métrica não encontrada"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<MetricaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody MetricaDTO dto) {
        return ResponseEntity.ok(metricaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma métrica")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Métrica deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Métrica não encontrada"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        metricaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/relatorio/{userId}")
    @Operation(summary = "Gera um relatorio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou sem métricas"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<RelatorioMetricaDTO> gerarRelatorio(@PathVariable Long userId) {
        return ResponseEntity.ok(metricaService.gerarRelatorioUsuario(userId));
    }
}
