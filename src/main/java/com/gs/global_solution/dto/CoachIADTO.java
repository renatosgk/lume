package com.gs.global_solution.dto;


import com.gs.global_solution.enums.NivelDeEstresse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Orientações personalizadas geradas pela IA com base nas métricas do usuário")
public class CoachIADTO {
    @Schema(description = "ID do usuário para o qual as orientações foram geradas", example = "7")
    private Long userId;
    @Schema(description = "Média dos batimentos cardíacos analisados", example = "80.5")
    private double mediaBpm;
    @Schema(description = "Média da temperatura corporal analisada", example = "36.8")
    private double mediaTemperatura;
    @Schema(description = "Nível atual de estresse do usuário baseado nas métricas", example = "ALTO")
    private NivelDeEstresse nivelAtual;
    @Schema(description = "Lista de recomendações personalizadas geradas pela IA", example = "[\"Pratique respiração diafragmática\", \"Faça uma pausa de 5 minutos\"]")
    private List<String> dicas;
}
