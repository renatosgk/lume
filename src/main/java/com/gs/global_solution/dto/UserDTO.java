package com.gs.global_solution.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.global_solution.enums.NivelDeEstresse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.boot.jaxb.hbm.internal.RepresentationModeConverter;

@Data
@Schema(description = "DTO de usuário")
public class UserDTO  {
    @Schema(description = "ID do usuário", example = "10")
    private Long id;
    @NotBlank(message = "Inserir o nome")
    @Schema(description = "Nome completo do usuário", example = "Carlos Oliveira", required = true)
    private String nome;
    @NotBlank(message = "Inserir o email")
    @Email
    @Schema(description = "Email do usuário", example = "carlos@gmail.com", required = true)
    private String email;
    @NotBlank(message = "Inserir a senha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "Senha do usuário (não aparece nas respostas da API)", example = "senha123", required = true)
    private String password;
    @NotNull(message = "Inserir a idade")
    @Schema(description = "Idade do usuário", example = "28", required = true)
    private Integer idade;
    @Schema(description = "Nível atual de estresse do usuário",example = "BAIXO")
    private NivelDeEstresse nivelDeEstresse;
}
