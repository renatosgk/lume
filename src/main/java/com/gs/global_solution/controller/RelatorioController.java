package com.gs.global_solution.controller;


import com.gs.global_solution.dto.RelatorioComDicasDTO;
import com.gs.global_solution.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorio")
@AllArgsConstructor
@Tag(name = "Gerar relatorios do usuário")
public class RelatorioController {
    private final RelatorioService relatorioService;

    @GetMapping("/usuario/{userId}")
    @Operation(summary = "Gera um relatorio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou sem métricas"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<RelatorioComDicasDTO> gerarRelatorio(@PathVariable Long userId) {
        RelatorioComDicasDTO relatorio = relatorioService.gerarRelatorioUsuario(userId);
        return ResponseEntity.ok(relatorio);
    }
}
