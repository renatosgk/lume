package com.gs.global_solution.enums;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Níveis emocionais possíveis registrados pelo usuário")
public enum NivelEmocional {
    @Schema(description = "Estado de felicidade intensa, humor extremamente positivo")
    MUITO_FELIZ,
    @Schema(description = "Humor positivo e estável, sensação de bem-estar")
    FELIZ,
    @Schema(description = "Estado emocional neutro, sem predominância positiva ou negativa")
    NEUTRO,
    @Schema(description = "Estado de estresse emocional leve a moderado")
    ESTRESSADO,
    @Schema(description = "Alto nível de estresse emocional, possível sobrecarga")
    MUITO_ESTRESSADO
}
