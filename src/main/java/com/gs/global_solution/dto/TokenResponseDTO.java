package com.gs.global_solution.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Resposta contendo token de acesso para usuário autenticado")
public class TokenResponseDTO {
    @Schema(
            description = "Token JWT usado para autenticação nas próximas requisições",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    private String tokenAcesso;
    @Schema(description = "Nome do Usuário",example = "Renato")
    private String nome;
    @Schema(description = "Email do Usuário",example = "Renato@gmail.com")
    private String email;
}
