package com.gs.global_solution.model;


import com.gs.global_solution.enums.NivelDeEstresse;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_checkin_emocional")
@Data
public class CheckinEmocional {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkinEmocional_seq_generator")
    @SequenceGenerator(
            name = "checkinEmocional_seq_generator",
            sequenceName = "checkinEmocional_seq",
            allocationSize = 1
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;
    @Column(name = "dt_hr",nullable = false)
    private LocalDateTime dataHora;
    @Enumerated(EnumType.STRING)
    @Column(name = "nvl_emocional", nullable = false)
    private NivelDeEstresse nivelEmocional;
}
