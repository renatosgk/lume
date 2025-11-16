package com.gs.global_solution.service;


import com.gs.global_solution.dto.CheckinEmocionalDTO;
import com.gs.global_solution.model.CheckinEmocional;
import com.gs.global_solution.model.User;
import com.gs.global_solution.repository.CheckinEmocionalRepository;
import com.gs.global_solution.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CheckinEmocionalService {
    private final CheckinEmocionalRepository checkinEmocionalRepository;
    private final UserRepository userRepository;

    public CheckinEmocionalDTO criar(CheckinEmocionalDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        CheckinEmocional checkin = new CheckinEmocional();
        checkin.setUser(user);
        checkin.setDataHora(dto.getDataHora());
        checkin.setNivelEmocional(dto.getNivelEmocional());

        CheckinEmocional salvo = checkinEmocionalRepository.save(checkin);
        return toDTO(salvo);
    }

    public List<CheckinEmocionalDTO> listarPorUsuario(Long userId) {
        return checkinEmocionalRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CheckinEmocionalDTO buscarPorId(Long id) {
        return checkinEmocionalRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Check-in não encontrado com id: " + id));
    }

    public CheckinEmocionalDTO atualizar(Long id, CheckinEmocionalDTO dto) {
        CheckinEmocional existente = checkinEmocionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Check-in não encontrado com id: " + id));

        existente.setDataHora(dto.getDataHora());
        if (dto.getNivelEmocional() != null) {
            existente.setNivelEmocional(dto.getNivelEmocional());
        }

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + dto.getUserId()));
            existente.setUser(user);
        }

        CheckinEmocional atualizado = checkinEmocionalRepository.save(existente);
        return toDTO(atualizado);
    }

    public void deletar(Long id) {
        CheckinEmocional checkin  = checkinEmocionalRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Check-in não encontrado com id: " + id));
        checkinEmocionalRepository.delete(checkin);
    }



    private CheckinEmocionalDTO toDTO(CheckinEmocional checkin) {
        CheckinEmocionalDTO dto = new CheckinEmocionalDTO();
        dto.setId(checkin.getId());
        dto.setUserId(checkin.getUser().getId());
        dto.setDataHora(checkin.getDataHora());
        dto.setNivelEmocional(checkin.getNivelEmocional());
        return dto;
    }

    private CheckinEmocional toEntity(CheckinEmocionalDTO dto, User user) {
        CheckinEmocional checkin = new CheckinEmocional();
        checkin.setId(dto.getId());
        checkin.setUser(user);
        checkin.setDataHora(dto.getDataHora());
        checkin.setNivelEmocional(dto.getNivelEmocional());
        return checkin;
    }


}
