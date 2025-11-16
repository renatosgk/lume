package com.gs.global_solution.service;


import com.gs.global_solution.enums.NivelDeEstresse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicasService {

    public List<String> gerarDicas(NivelDeEstresse nivel) {
        switch (nivel) {
            case LEVE -> {
                return List.of(
                        "Continue mantendo hábitos saudáveis",
                        "Mantenha atividades físicas leves"
                );
            }
            case MEDIO -> {
                return List.of(
                        "Faça pausas regulares durante o dia",
                        "Pratique respiração profunda por 5 minutos"
                );
            }
            case ALTO -> {
                return List.of(
                        "Pratique exercícios leves",
                        "Evite sobrecarga de trabalho",
                        "Dedique tempo para relaxamento"
                );
            }
            case MUITO_ALTO -> {
                return List.of(
                        "Considere meditação ou terapia",
                        "Priorize descanso e sono adequado",
                        "Evite atividades estressantes no momento"
                );
            }
            default -> {
                return List.of();
            }
        }
    }
}
