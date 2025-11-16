package com.gs.global_solution.controller;


import com.gs.global_solution.dto.CoachIADTO;
import com.gs.global_solution.service.CoachIAService;
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
@RequestMapping("/coach")
@AllArgsConstructor
@Tag(name = "Gerar orientacoes para os usuários")
public class CoachIAController {
    private final CoachIAService coachService;

    @GetMapping("/usuario/{userId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orientações geradas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<CoachIADTO> gerarCoach(@PathVariable Long userId) {
        return ResponseEntity.ok(coachService.gerarOrientacoes(userId));
    }

}
