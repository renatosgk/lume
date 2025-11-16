package com.gs.global_solution.enums;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Níveis possíveis de estresse do usuário")
public enum NivelDeEstresse {
    @Schema(description = "Estado de estresse muito baixo, sem sinais preocupantes")
    BAIXO,
    @Schema(description = "Leve aumento do estresse, geralmente sem impacto significativo")
    LEVE,
    @Schema(description = "Estresse moderado, atenção recomendada")
    MEDIO,
    @Schema(description = "Nível alto de estresse, risco de impactos negativos no bem-estar")
    ALTO,
    @Schema(description = "Estresse extremamente elevado, requer intervenção imediata")
    MUITO_ALTO
}
