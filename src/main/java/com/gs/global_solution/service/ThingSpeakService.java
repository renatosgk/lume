package com.gs.global_solution.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.global_solution.enums.NivelDeEstresse;
import com.gs.global_solution.model.Metrica;
import com.gs.global_solution.model.User;
import com.gs.global_solution.repository.MetricaRepository;
import com.gs.global_solution.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ThingSpeakService {
    private final MetricaRepository metricaRepository;
    private final UserRepository userRepository;

    @Value("${thingspeak.channel.id}")
    private String channelId;

    @Value("${thingspeak.api.readkey}")
    private String readApiKey;

    @Value("${thingspeak.user.id}")
    private Long userId;

    private final ObjectMapper mapper = new ObjectMapper();

    @Scheduled(fixedRate = 60000)
    public void lerDadosDoThingSpeak() {
        try {
            String urlString = String.format(
                    "https://api.thingspeak.com/channels/%s/feeds.json?api_key=%s&results=1",
                    channelId, readApiKey
            );

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            JsonNode json = mapper.readTree(response.toString());
            JsonNode feed = json.get("feeds").get(0);

            if (feed != null) {
                Double bpm = feed.get("field1").asDouble();
                Double temp = feed.get("field2").asDouble();

                salvarMetrica(bpm, temp);
            }

        } catch (Exception e) {
            System.err.println("Erro ao ler dados do ThingSpeak: " + e.getMessage());
        }
    }

    private void salvarMetrica(Double bpm, Double temperatura) {
        if (bpm == null || temperatura == null) return;

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            System.err.println("Usuário padrão não encontrado. Pulando gravação da métrica.");
            return;
        }

        NivelDeEstresse nivel = calcularNivelDeEstresse(bpm, temperatura);

        Metrica metrica = new Metrica();
        metrica.setUser(user);
        metrica.setBatimentoCardiaco(bpm);
        metrica.setTemperatura(temperatura);
        metrica.setDataHora(LocalDateTime.now());
        metricaRepository.save(metrica);

        user.setNivelDeEstresse(nivel);
        userRepository.save(user);
    }

    private NivelDeEstresse calcularNivelDeEstresse(Double bpm, Double temperatura) {
        if (bpm < 80 && temperatura < 37.0) return NivelDeEstresse.LEVE;
        else if (bpm < 100 && temperatura < 37.5) return NivelDeEstresse.MEDIO;
        else if (bpm < 120 || temperatura < 38.0) return NivelDeEstresse.ALTO;
        else return NivelDeEstresse.MUITO_ALTO;
    }
}
