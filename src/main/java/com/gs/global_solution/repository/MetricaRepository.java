package com.gs.global_solution.repository;

import com.gs.global_solution.model.Metrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricaRepository extends JpaRepository<Metrica, Long> {
    List<Metrica> findByUserId(Long userId);
}
