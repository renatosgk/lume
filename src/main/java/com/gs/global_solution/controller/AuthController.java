package com.gs.global_solution.controller;


import com.gs.global_solution.dto.TokenResponseDTO;
import com.gs.global_solution.dto.autenticacao.DadosLogin;
import com.gs.global_solution.dto.autenticacao.RegisterDTO;
import com.gs.global_solution.enums.NivelDeEstresse;
import com.gs.global_solution.model.User;
import com.gs.global_solution.repository.UserRepository;
import com.gs.global_solution.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Autenticação",description = "Endpoints de login e registro de usuários")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Efetuar login",description = "efetuar login e gerar token jwt")
    @ApiResponse(responseCode = "200",description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401",description = "Credencias incorretas")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> efetuarLogin(@Valid @RequestBody DadosLogin dados){
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var authentication = authenticationManager.authenticate(autenticationToken);

        User user = (User) authentication.getPrincipal();
        String tokenAcesso = tokenService.gerarToken((User) authentication.getPrincipal());

        TokenResponseDTO response = new TokenResponseDTO();
        response.setTokenAcesso(tokenAcesso);
        response.setNome(user.getNome());
        response.setEmail(user.getEmail());
        return ResponseEntity.ok( response);
    }

    @Operation(summary = "Realizar o cadastro(Registro)",description = "Criar um usuario no sistema ")
    @ApiResponse(responseCode = "200",description = "registro gerado com sucesso")
    @ApiResponse(responseCode = "400",description = "Email já cadastrado")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.findByEmailIgnoreCase(registerDTO.email()).isPresent()){
            return ResponseEntity.badRequest().body("Já possui um usuario cadastrado com esse e-mail");
        }
        String senhaCriptografada = passwordEncoder.encode(registerDTO.password());
        User user = new User();
        user.setNome(registerDTO.nome());
        user.setEmail(registerDTO.email());
        user.setPassword(senhaCriptografada);
        user.setIdade(registerDTO.idade());
        user.setNivelDeEstresse(NivelDeEstresse.MEDIO);

        userRepository.save(user);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    public UserDetails saveCliente(String email, String senhaCriptografada) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(senhaCriptografada);
        return userRepository.save(user);
    }
}
