package com.gs.global_solution.service;


import com.gs.global_solution.dto.MetricaDTO;
import com.gs.global_solution.dto.RelatorioMetricaDTO;
import com.gs.global_solution.enums.NivelDeEstresse;
import com.gs.global_solution.model.Metrica;
import com.gs.global_solution.model.User;
import com.gs.global_solution.repository.MetricaRepository;
import com.gs.global_solution.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MetricaService {
    private final MetricaRepository metricaRepository;
    private final UserRepository userRepository;

    public MetricaDTO criar(MetricaDTO dto) {
        Metrica metrica = toEntity(dto);
        NivelDeEstresse nivel = calcularNivelDeEstresse(
                metrica.getBatimentoCardiaco(),
                metrica.getTemperatura()
        );
        User user = metrica.getUser();
        user.setNivelDeEstresse(nivel);
        userRepository.save(user);

        Metrica salva = metricaRepository.save(metrica);
        return toDTO(salva);
    }

    private MetricaDTO toDTO(Metrica metrica) {
        MetricaDTO dto = new MetricaDTO();
        dto.setId(metrica.getId());
        dto.setDataHora(metrica.getDataHora());
        dto.setBatimentoCardiaco(metrica.getBatimentoCardiaco());
        dto.setTemperatura(metrica.getTemperatura());
        dto.setUserId(metrica.getUser().getId());
        return dto;
    }

    private Metrica toEntity(MetricaDTO dto) {
        Metrica metrica = new Metrica();
        metrica.setId(dto.getId());
        metrica.setDataHora(dto.getDataHora());
        metrica.setBatimentoCardiaco(dto.getBatimentoCardiaco());
        metrica.setTemperatura(dto.getTemperatura());
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        metrica.setUser(user);
        return metrica;
    }

    private NivelDeEstresse calcularNivelDeEstresse(Double bpm, Double temperatura) {
        if (bpm == null || temperatura == null) return NivelDeEstresse.MEDIO;
        if (bpm < 80 && temperatura < 37.0) return NivelDeEstresse.LEVE;
        if (bpm < 100 && temperatura < 37.5) return NivelDeEstresse.MEDIO;
        if (bpm < 120 && temperatura < 38.0) return NivelDeEstresse.ALTO;
        return NivelDeEstresse.MUITO_ALTO;
    }

    public MetricaDTO buscarPorId(Long id) {
        Metrica metrica = metricaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Métrica não encontrada com essa id: " + id));
        return toDTO(metrica);
    }

    public List<MetricaDTO> listarTodos() {
        return metricaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MetricaDTO atualizar(Long id, MetricaDTO dto) {
        Metrica existente = metricaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Métrica não encontrada com essa id: " + id));

        existente.setBatimentoCardiaco(dto.getBatimentoCardiaco());
        existente.setTemperatura(dto.getTemperatura());
        existente.setDataHora(dto.getDataHora());

        NivelDeEstresse nivel = calcularNivelDeEstresse(
                existente.getBatimentoCardiaco(),
                existente.getTemperatura()
        );

        User user = existente.getUser();
        user.setNivelDeEstresse(nivel);
        userRepository.save(user);
        Metrica atualizada = metricaRepository.save(existente);
        return toDTO(atualizada);
    }

    public void deletar(Long id) {
        Metrica metrica = metricaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Métrica não econtrada com essa id: " + id));
        metricaRepository.delete(metrica);
    }

    public RelatorioMetricaDTO gerarRelatorioUsuario(Long userId) {
        List<Metrica> metricas = metricaRepository.findByUserId(userId);

        if (metricas.isEmpty()) {
            throw new RuntimeException("Nenhuma métrica encontrada para o usuário com ID " + userId);
        }

        double mediaBpm = metricas.stream()
                .mapToDouble(Metrica::getBatimentoCardiaco)
                .average()
                .orElse(0.0);

        double mediaTemp = metricas.stream()
                .mapToDouble(Metrica::getTemperatura)
                .average()
                .orElse(0.0);

        NivelDeEstresse nivelMedio = calcularNivelDeEstresse(mediaBpm, mediaTemp);

        return new RelatorioMetricaDTO(
                userId,
                mediaBpm,
                mediaTemp,
                nivelMedio,
                (long) metricas.size()
        );
    }
}
