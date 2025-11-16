package com.gs.global_solution.service;


import com.gs.global_solution.dto.UserDTO;
import com.gs.global_solution.enums.NivelDeEstresse;
import com.gs.global_solution.model.User;
import com.gs.global_solution.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserDTO criar(UserDTO dto) {
        if (userRepository.findByEmailIgnoreCase(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um usuário com este e-mail.");
        }

        User user = toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setNivelDeEstresse(NivelDeEstresse.MEDIO);

        User salvo = userRepository.save(user);
        return toDTO(salvo);
    }


    public UserDTO buscarPorId(Long id) {
        return userRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }


    public List<UserDTO> listarTodos() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public UserDTO atualizar(Long id, UserDTO dto) {
        User existente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        existente.setNome(dto.getNome());
        existente.setEmail(dto.getEmail());
        existente.setIdade(dto.getIdade());

        if (dto.getNivelDeEstresse() != null) {
            existente.setNivelDeEstresse(dto.getNivelDeEstresse());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        User atualizado = userRepository.save(existente);
        return toDTO(atualizado);
    }


    public void deletar(Long id) {
        User user  = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario não encontrado com id: " + id));
        userRepository.delete(user);
    }


    public User buscarPorEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com email: " + email));
    }

    public void atualizarNivelDeEstresse(Long userId, NivelDeEstresse nivel) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + userId));

        user.setNivelDeEstresse(nivel);
        userRepository.save(user);
    }


    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        dto.setIdade(user.getIdade());
        dto.setNivelDeEstresse(user.getNivelDeEstresse());

        return dto;
    }

    private User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setIdade(dto.getIdade());
        user.setNivelDeEstresse(dto.getNivelDeEstresse());
        return user;
    }
}

