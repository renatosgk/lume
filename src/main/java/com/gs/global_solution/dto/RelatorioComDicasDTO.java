package com.gs.global_solution.dto;


import com.gs.global_solution.enums.NivelDeEstresse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Relatório consolidado das métricas do usuário com orientações adicionais")
public class RelatorioComDicasDTO {
    @Schema(description = "ID do usuário ao qual o relatório pertence", example = "12")
    private Long userId;
    @Schema(description = "Média dos batimentos cardíacos do usuário", example = "78.4")
    private double mediaBpm;
    @Schema(description = "Média da temperatura corporal do usuário", example = "36.7")
    private double mediaTemperatura;
    @Schema(description = "Nível de estresse calculado com base nas métricas do usuário", example = "MEDIO")
    private NivelDeEstresse nivelDeEstresse;
    @Schema(description = "Total de métricas avaliadas no período", example = "20")
    private long totalMetricas;
    @Schema(description = "Lista de recomendações e orientações geradas com base nas métricas", example = "[\"Respire profundamente por 5 minutos\", \"Reduza o consumo de cafeína\"]")
    private List<String> dicas;
}
