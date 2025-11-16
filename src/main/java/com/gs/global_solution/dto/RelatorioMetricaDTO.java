package com.gs.global_solution.dto;


import com.gs.global_solution.enums.NivelDeEstresse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Schema(description = "Relatório consolidado das métricas de um usuário")
public class RelatorioMetricaDTO {
    @Schema(description = "ID do usuário ao qual o relatório pertence", example = "15")
    private Long userId;
    @Schema(description = "Média dos batimentos cardíacos registrados", example = "82.5")
    private Double mediaBatimentos;
    @Schema(description = "Média da temperatura corporal registrada", example = "36.8")
    private Double mediaTemperatura;
    @Schema(description = "Nível médio de estresse calculado com base nas métricas do usuário", example = "MEDIO")
    private NivelDeEstresse nivelDeEstresseMedio;
    @Schema(description = "Quantidade total de métricas consideradas no cálculo", example = "12")
    private Long quantidadeMetricas;
}
