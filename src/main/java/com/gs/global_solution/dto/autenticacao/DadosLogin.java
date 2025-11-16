package com.gs.global_solution.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(@NotBlank(message = "Inserir o email") String email, @NotBlank(message = "Inserir a senha") String password) {


}
