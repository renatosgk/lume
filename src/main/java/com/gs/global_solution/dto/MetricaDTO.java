package com.gs.global_solution.dto;

import com.gs.global_solution.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Schema(description = " métrica registrada pelo usuário")
public class MetricaDTO {
    @Schema(description = "Identificador único da métrica", example = "101")
    private Long id;
    @NotNull(message = "Inserir os batimentos Cardiaco")
    @Schema(description = "Número de batimentos cardíacos do usuário (BPM)", example = "85.0")
    private Double batimentoCardiaco;
    @NotNull(message = "Inserir a temperatura")
    @Schema(description = "Temperatura corporal registrada", example = "36.9")
    private Double temperatura;
    @NotNull(message = "Inserir a data e a hora")
    @Schema(description = "Data e hora da coleta da métrica no formato ISO 8601", example = "2025-11-15T14:30:00")
    private LocalDateTime dataHora;
    @NotNull(message = "Inserir o ID do usuario")
    @Schema(description = "ID do usuário que realizou o registro", example = "5")
    private Long userId;
}
