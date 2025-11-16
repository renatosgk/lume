package com.gs.global_solution.service;


import com.gs.global_solution.dto.RelatorioComDicasDTO;
import com.gs.global_solution.enums.NivelDeEstresse;
import com.gs.global_solution.model.Metrica;
import com.gs.global_solution.repository.MetricaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RelatorioService {
    private final MetricaRepository metricaRepository;
    private final DicasService dicasService;

    public RelatorioComDicasDTO gerarRelatorioUsuario(Long userId) {
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

        List<String> dicas = dicasService.gerarDicas(nivelMedio);

        RelatorioComDicasDTO dto = new RelatorioComDicasDTO();
        dto.setUserId(userId);
        dto.setMediaBpm(mediaBpm);
        dto.setMediaTemperatura(mediaTemp);
        dto.setNivelDeEstresse(nivelMedio);
        dto.setTotalMetricas(metricas.size());
        dto.setDicas(dicas);

        return dto;
    }

    private NivelDeEstresse calcularNivelDeEstresse(Double bpm, Double temperatura) {
        if (bpm == null || temperatura == null) return NivelDeEstresse.MEDIO;
        if (bpm < 80 && temperatura < 37.0) return NivelDeEstresse.LEVE;
        else if (bpm < 100 && temperatura < 37.5) return NivelDeEstresse.MEDIO;
        else if (bpm < 120 || temperatura < 38.0) return NivelDeEstresse.ALTO;
        else return NivelDeEstresse.MUITO_ALTO;
    }

}
