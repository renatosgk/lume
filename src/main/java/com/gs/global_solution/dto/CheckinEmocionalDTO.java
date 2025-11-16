package com.gs.global_solution.dto;


import com.gs.global_solution.enums.NivelDeEstresse;
import com.gs.global_solution.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Registro de check-in emocional do usuário")
public class CheckinEmocionalDTO {
    @Schema(description = "Identificador único do check-in emocional", example = "45")
    private Long id;
    @NotNull(message = "Inserir o usuario")
    @NotNull(message = "Inserir o usuario")
    @Schema(description = "ID do usuário que realizou o check-in", example = "7")
    private Long userId;
    @NotNull(message = "Inserir o horario")
    @Schema(description = "Data e hora do registro do check-in emocional", example = "2025-11-15T18:45:00")
    private LocalDateTime dataHora;
    @NotNull(message = "Inserir o nivel emocional")
    @Schema(description = "Data e hora do registro do check-in emocional", example = "2025-11-15T18:45:00")
    private NivelDeEstresse nivelEmocional;

}
