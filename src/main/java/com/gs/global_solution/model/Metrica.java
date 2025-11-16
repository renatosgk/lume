package com.gs.global_solution.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_metricas")
@Data
public class Metrica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "metrica_seq_generator")
    @SequenceGenerator(name = "metrica_seq_generator",sequenceName = "metrica_seq",allocationSize=1)
    private Long id;
    @Column(name = "btm_cardiaco",nullable = false)
    private Double batimentoCardiaco;
    @Column(name = "temp",nullable = false)
    private Double temperatura;
    @Column(name = "dt_hr",nullable = false)
    private LocalDateTime dataHora;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User user;
}
