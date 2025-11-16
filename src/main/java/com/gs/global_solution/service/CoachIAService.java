package com.gs.global_solution.service;


import com.gs.global_solution.dto.CoachIADTO;
import com.gs.global_solution.enums.NivelDeEstresse;
import com.gs.global_solution.model.CheckinEmocional;
import com.gs.global_solution.model.Metrica;
import com.gs.global_solution.repository.CheckinEmocionalRepository;
import com.gs.global_solution.repository.MetricaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoachIAService {
    private final MetricaRepository metricaRepository;
    private final CheckinEmocionalRepository checkinRepository;
    private final DicasService dicasService;

    public CoachIADTO gerarOrientacoes(Long userId) {
        List<Metrica> metricas = metricaRepository.findByUserId(userId);
        List<CheckinEmocional> checkins = checkinRepository.findByUserId(userId);

        double mediaBpm = metricas.stream().mapToDouble(Metrica::getBatimentoCardiaco).average().orElse(0);
        double mediaTemp = metricas.stream().mapToDouble(Metrica::getTemperatura).average().orElse(0);

        NivelDeEstresse nivelAtual = calcularNivelDeEstresse(mediaBpm, mediaTemp);

        List<String> dicas = dicasService.gerarDicas(nivelAtual);


        if (!checkins.isEmpty() && nivelAtual == NivelDeEstresse.MUITO_ALTO) {
            dicas.add("Alerta: procure ajuda profissional se o estresse continuar alto.");
        }

        return new CoachIADTO(userId, mediaBpm, mediaTemp, nivelAtual, dicas);
    }

    private NivelDeEstresse calcularNivelDeEstresse(double bpm, double temp) {
        if (bpm < 80 && temp < 37.0) return NivelDeEstresse.LEVE;
        if (bpm < 100 && temp < 37.5) return NivelDeEstresse.MEDIO;
        if (bpm < 120 || temp < 38.0) return NivelDeEstresse.ALTO;
        return NivelDeEstresse.MUITO_ALTO;
    }
}
